package com.volley.kotlin.toolbox

import android.os.Handler
import android.os.Looper
import com.volley.kotlin.Cache
import com.volley.kotlin.NetworkResponse
import com.volley.kotlin.Request
import com.volley.kotlin.Request.Method.GET
import com.volley.kotlin.Response

open class ClearCacheRequest(cache: Cache, callback: Runnable) : Request<Any>(GET, null, null) {
    private var mCache: Cache? = cache
    private var mCallback: Runnable? = callback

    override fun isCanceled(): Boolean {
        mCache!!.clear()
        if (mCallback != null) {
            val handler = Handler(Looper.getMainLooper())
            handler.postAtFrontOfQueue(mCallback)
        }
        return true
    }

    override fun getPriority(): Priority? {
        return Priority.IMMEDIATE
    }

    override fun parseNetworkResponse(response: NetworkResponse?): Response<Any?>? {
        return null
    }

    override fun deliverResponse(response: Any?) {}


}