package com.volley.kotlin

open class ParseError:VolleyError {

    constructor()

    constructor(networkResponse : NetworkResponse?) : super(networkResponse)

    constructor(cause : Throwable?) : super(cause)

}