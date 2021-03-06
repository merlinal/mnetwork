package com.merlin.network.retrofit;

import com.merlin.core.worker.FileWorker;
import com.merlin.network.http.IResponse;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * @author merlin
 */
public class UploadRequestBody extends RequestBody {

    private RequestBody mRequestBody;
    private BufferedSink bufferedSink;
    private IResponse iResponse;

    public UploadRequestBody(File file, IResponse iResponse) {
        this.mRequestBody = RequestBody.create(MediaType.parse(FileWorker.inst().getMimeType(file)), file);
        this.iResponse = iResponse;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    /**
     * 返回了本RequestBody的长度，也就是上传的totalLength
     *
     * @return
     * @throws IOException 异常
     */
    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            //包装
            bufferedSink = Okio.buffer(sink(sink));
        }
        //写入
        mRequestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long bytesWritten = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    //获得contentLength的值，后续不再调用
                    contentLength = contentLength();
                }
                //增加当前写入的字节数
                bytesWritten += byteCount;
                if (bytesWritten < contentLength) {
                    iResponse.onProgress(contentLength, bytesWritten);
                }
            }
        };
    }
}
