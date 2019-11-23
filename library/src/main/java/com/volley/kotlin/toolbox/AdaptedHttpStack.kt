package com.volley.kotlin.toolbox

import com.volley.kotlin.AuthFailureError
import com.volley.kotlin.Header
import com.volley.kotlin.Request
import org.apache.http.conn.ConnectTimeoutException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.*

@SuppressWarnings("deprecation")
open class AdaptedHttpStack:BaseHttpStack {

    var mHttpStack: HttpStack? = null

    constructor(httpStack: HttpStack?) {
        mHttpStack = httpStack
    }

    @Throws(IOException::class, AuthFailureError::class)
    override fun executeRequest(request: Request<*>?, additionalHeaders: Map<String?, String?>?): HttpResponse? {
        val apacheResp: org.apache.http.HttpResponse
        try {
            apacheResp = mHttpStack!!.performRequest(request, additionalHeaders)
        } catch (e: ConnectTimeoutException) {
            throw SocketTimeoutException(e.message)
        }

        val statusCode = apacheResp.statusLine.statusCode

        val headers = apacheResp.allHeaders
        val headerList: MutableList<Header> = ArrayList(headers.size)

        for (header in headers) {
            headerList.add(Header(header.name, header.value))
        }

        if (apacheResp.entity == null) {
            return HttpResponse(statusCode, headerList)
        }

        val contentLength = apacheResp.entity.contentLength

        if ((contentLength).toLong () != contentLength){
            throw IOException("Response too large: $contentLength")
        }

        return HttpResponse(
            statusCode,
            headerList,
            apacheResp.entity.contentLength.toInt(),
            apacheResp.entity.content
        )
    }

}
