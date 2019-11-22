package com.volley.kotlin

interface ResponseDelivery {
    fun postResponse(request: Request<*>?, response: Response<*>?)
    fun postResponse(request: Request<*>?, response: Response<*>?, runnable: Runnable?)
    fun postError(request: Request<*>?, error: VolleyError?)
}