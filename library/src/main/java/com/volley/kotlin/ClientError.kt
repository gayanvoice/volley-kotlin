package com.volley.kotlin

class ClientError : ServerError {

    constructor() : super()

    constructor(networkResponse : NetworkResponse?) : super(networkResponse)


}