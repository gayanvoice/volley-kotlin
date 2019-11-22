package com.volley.kotlin

open class NoConnectionError:NetworkError {

    constructor() : super()

    constructor(reason : Throwable?) : super(reason)


}