package com.volley.kotlin

open class NetworkError : VolleyError {

    constructor() : super()

    constructor(cause : Throwable?) : super(cause)

    constructor(networkResponse : NetworkResponse?) : super(networkResponse)

}