package com.volley.kotlin

interface Network {
    @Throws(VolleyError::class) fun performRequest(request: Request<*>?): NetworkResponse?
}