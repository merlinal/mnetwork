package com.merlin.network.retrofit;

import android.os.AsyncTask;

import com.merlin.core.util.MLog;
import com.merlin.core.util.MVerify;
import com.merlin.network.http.HttpConfig;
import com.merlin.network.http.HttpMethod;
import com.merlin.network.http.HttpStatus;
import com.merlin.network.http.IClient;
import com.merlin.network.http.IRequest;
import com.merlin.network.http.MRequest;
import com.merlin.network.http.NetTool;
import com.merlin.network.http.NetWorker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author merlin
 */

public class RetrofitClient extends IClient<Call<ResponseBody>> {

    private ApiService apiService;

    public static RetrofitClient inst() {
        return InstHolder.client;
    }

    private static class InstHolder {
        private final static RetrofitClient client = new RetrofitClient();
    }

    private RetrofitClient() {
    }

    @Override
    public void buildClient() {
        initHttp();
    }

    private void initHttp() {
        final HttpConfig config = NetWorker.inst().config();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS)
                //设置超时
                .writeTimeout(config.getWriteTimeout(), TimeUnit.MILLISECONDS)
                //重试
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        HashMap<String, String> headers = config.getHeaders();
                        for (String key : headers.keySet()) {
                            builder.addHeader(key, headers.get(key));
                        }
                        return chain.proceed(builder.build());
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.getBaseUrl())
                .client(client)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public IRequest switchHandle(Call<ResponseBody> call, final MRequest request) {
        switch (request.getMethod()) {
            case HttpMethod.DOWNLOAD:
                download(call, request);
                break;
            case HttpMethod.UPLOAD:
                upload(call, request);
                break;
            default:
                commonHttp(call, request);
                break;
        }
        return new RequestHandle(call);
    }

    private void commonHttp(Call<ResponseBody> call, final MRequest request) {
        if (request.getListener() != null) {
            request.getListener().onProgress(30L, 100L);
        }
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                request.getListener().onProgress(100L, 100L);
                try {
                    ResponseBody body = response.body();
                    if (body == null) {
                        body = response.errorBody();
                    }
                    if (body == null) {
                        fail(request, response.code() + "", "获取数据失败", null);
                    } else {
                        MLog.i("【HTTP】success for http ...");
                        String bodyStr = body.string();
                        NetTool.parseJson(bodyStr, request.getResponseType(), request.getListener());
                        if (request.getMethod() == HttpMethod.GET) {
                            MLog.i("【HTTP】add cache ...");
                            NetWorker.inst().cache().add(request.getKey(), bodyStr, request.getCacheTime());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                MLog.e("【HTTP】fail for http ...");
                fail(request, HttpStatus.EMPTY, "操作失败", t);
            }
        });
    }

    private void upload(Call<ResponseBody> call, final MRequest request) {
        commonHttp(call, request);
    }

    private void download(Call<ResponseBody> call, final MRequest request) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final retrofit2.Response<ResponseBody> response) {
                final ResponseBody body = response.body();
                if (body == null) {
                    fail(request, HttpStatus.EMPTY, "下载文件失败", null);
                } else {
                    //保存文件
                    new AsyncTask<Object, Void, Boolean>() {
                        @Override
                        protected Boolean doInBackground(Object... objects) {
                            return saveFile(request, body.byteStream(), body.contentLength());
                        }

                        @Override
                        protected void onPostExecute(Boolean aBoolean) {
                            if (aBoolean) {
                                if (request.getListener() != null) {
                                    request.getListener().onSuccess(null);
                                }
                            } else {
                                fail(request, HttpStatus.EMPTY, "下载文件失败", null);
                            }
                        }
                    }.execute();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, final Throwable t) {
                MLog.e("【HTTP】fail for file download ...");
                fail(request, HttpStatus.EMPTY, "下载文件失败", t);
            }
        });
    }

    private void fail(MRequest request, String code, String msg, Throwable t) {
        if (request.getListener() != null) {
            request.getListener().onProgress(100L, 100L);
            request.getListener().onFailure(code, msg);
            t.printStackTrace();
        }
    }

    @Override
    public Call<ResponseBody> get(String url, HashMap map) {
        return apiService.get(url, map);
    }

    @Override
    public Call<ResponseBody> post(String url, HashMap map) {
        return apiService.post(url, map);
    }

    @Override
    public Call<ResponseBody> patch(String url, HashMap map) {
        return apiService.patch(url, map);
    }

    @Override
    public Call<ResponseBody> put(String url, HashMap map) {
        return apiService.put(url, map);
    }

    @Override
    public Call<ResponseBody> delete(String url, HashMap map) {
        return apiService.delete(url, map);
    }

    @Override
    public Call<ResponseBody> upload(MRequest request) {
        if (MVerify.isEmpty(request.getFiles())) {
            return null;
        }
        HashMap<String, RequestBody> requestBodyMap = new HashMap<>();
        HashMap<String, String> files = request.getFiles();
        for (Map.Entry<String, String> entry : files.entrySet()) {
            File file = new File(entry.getValue());
            requestBodyMap.put(entry.getKey() + "\"; filename=\"" + file.getName(), new UploadRequestBody(file, request.getListener()));
        }
        return apiService.upload(request.getUrl(), request.getParams(), requestBodyMap);
    }

    @Override
    public Call<ResponseBody> download(String url, HashMap map) {
        return apiService.download(url, map);
    }

}
