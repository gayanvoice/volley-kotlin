package com.volley.kotlin

open class ServerError : VolleyError {

    constructor(networkResponse : NetworkResponse?) : super(networkResponse)

    constructor() : super()

}