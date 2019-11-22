package com.volley.kotlin

open class VolleyError : Exception {
    var networkResponse: NetworkResponse? = null
    private var networkTimeMs: Long? = 0

    constructor(){
        networkResponse = null
    }

    constructor(response: NetworkResponse?) {
        networkResponse = response
    }

    constructor(exceptionMessage : String?) : super(exceptionMessage) {
        networkResponse = null
    }

    constructor (exceptionMessage: String?, reason: Throwable?) :  super(exceptionMessage, reason){
        networkResponse = null
    }

    constructor(cause: Throwable?) : super(cause) {
        networkResponse = null
    }

    constructor(networkTimeMs: Long?) {
        this.networkTimeMs = networkTimeMs
    }

    fun setNetworkTimeMs(networkTimeMs: Long?) {
        this.networkTimeMs = networkTimeMs
    }

    fun getNetworkTimeMs(): Long {
        return networkTimeMs!!
    }

}