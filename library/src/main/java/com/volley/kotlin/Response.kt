package com.volley.kotlin


open class Response<T> {

    interface Listener<T> {
        fun onResponse(response: T)
    }

    interface ErrorListener {
        fun onErrorResponse(error: VolleyError?)
    }

    val result: T?
    val cacheEntry: Cache.Entry?
    val error: VolleyError?
    var intermediate = false
    val isSuccess: Boolean
        get() = error == null

    private constructor(result: T, cacheEntry: Cache.Entry) {
        this.result = result
        this.cacheEntry = cacheEntry
        error = null
    }

    private constructor(error: VolleyError) {
        result = null
        cacheEntry = null
        this.error = error
    }

    companion object {
        fun <T> success(result: T, cacheEntry: Cache.Entry): Response<T> {
            return Response(result, cacheEntry)
        }

        fun <Any> error(error: VolleyError): Response<Any> {
            return Response<Any>(error)
        }
    }
}