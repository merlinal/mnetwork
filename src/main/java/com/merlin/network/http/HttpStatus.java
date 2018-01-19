package com.merlin.network.http;

/**
 * Created by ncm on 16/12/12.
 */

public class HttpStatus {

    //http_statusCode
    public final static int OK = 0;  //成功
    public final static int NOT_FOUND = 404;  //请求不存在
    public final static int SERVER_ERROR = 500;  //服务器报错
    public final static int SERVICE_AVAILABLE = 503;  //服务不可用

    //自定义
    public final static int EMPTY = 0x1101;  //response为空
    public final static int UNPARSE = 0x1102;  //response不能转化
    public final static int EXCEPTION = 0x1103;  //response报错


    /**
     HTTP: Status 1xx  (临时响应)
     ->表示临时响应并需要请求者继续执行操作的状态代码。

     详细代码及说明:
     HTTP: Status 100 (继续)
     -> 请求者应当继续提出请求。 服务器返回此代码表示已收到请求的第一部分，正在等待其余部分。
     HTTP: Status 101 (切换协议)
     -> 请求者已要求服务器切换协议，服务器已确认并准备切换。
     */

    /**
     HTTP Status 2xx  (成功)
     ->表示成功处理了请求的状态代码;

     详细代码及说明:
     HTTP Status 200 (成功)
     -> 服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。
     HTTP Status 201 (已创建)
     -> 请求成功并且服务器创建了新的资源。
     HTTP Status 202 (已接受)
     -> 服务器已接受请求，但尚未处理。
     HTTP Status 203 (非授权信息)
     -> 服务器已成功处理了请求，但返回的信息可能来自另一来源。
     HTTP Status 204 (无内容)
     -> 服务器成功处理了请求，但没有返回任何内容。
     HTTP Status 205 (重置内容)
     -> 服务器成功处理了请求，但没有返回任何内容。
     HTTP Status 206 (部分内容)
     -> 服务器成功处理了部分 GET 请求。
     */

    /**
     HTTP Status 4xx (请求错误)
     ->这些状态代码表示请求可能出错，妨碍了服务器的处理。

     详细代码说明:
     HTTP Status 400 （错误请求）
     ->服务器不理解请求的语法。
     HTTP Status 401 （未授权）
     ->请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
     HTTP Status 403 （禁止）
     -> 服务器拒绝请求。
     HTTP Status 404 （未找到）
     ->服务器找不到请求的网页。
     HTTP Status 405 （方法禁用）
     ->禁用请求中指定的方法。
     HTTP Status 406 （不接受）
     ->无法使用请求的内容特性响应请求的网页。
     HTTP Status 407 （需要代理授权）
     ->此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。
     HTTP Status 408 （请求超时）
     ->服务器等候请求时发生超时。
     HTTP Status 409 （冲突）
     ->服务器在完成请求时发生冲突。 服务器必须在响应中包含有关冲突的信息。
     HTTP Status 410 （已删除）
     -> 如果请求的资源已永久删除，服务器就会返回此响应。
     HTTP Status 411 （需要有效长度）
     ->服务器不接受不含有效内容长度标头字段的请求。
     HTTP Status 412 （未满足前提条件）
     ->服务器未满足请求者在请求中设置的其中一个前提条件。
     HTTP Status 413 （请求实体过大）
     ->服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。
     HTTP Status 414 （请求的 URI 过长） 请求的 URI（通常为网址）过长，服务器无法处理。
     HTTP Status 415 （不支持的媒体类型）
     ->请求的格式不受请求页面的支持。
     HTTP Status 416 （请求范围不符合要求）
     ->如果页面无法提供请求的范围，则服务器会返回此状态代码。
     HTTP Status 417 （未满足期望值）
     ->服务器未满足”期望”请求标头字段的要求。
     */

    /**
     HTTP Status 5xx （服务器错误）
     ->这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。

     代码详细及说明:
     HTTP Status 500 （服务器内部错误）
     ->服务器遇到错误，无法完成请求。
     HTTP Status 501 （尚未实施）
     ->服务器不具备完成请求的功能。 例如，服务器无法识别请求方法时可能会返回此代码。
     HTTP Status 502 （错误网关）
     ->服务器作为网关或代理，从上游服务器收到无效响应。
     HTTP Status 503 （服务不可用）
     -> 服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态。
     HTTP Status 504 （网关超时）
     ->服务器作为网关或代理，但是没有及时从上游服务器收到请求。
     HTTP Status 505 （HTTP 版本不受支持）
     -> 服务器不支持请求中所用的 HTTP 协议版本。
     */

}
