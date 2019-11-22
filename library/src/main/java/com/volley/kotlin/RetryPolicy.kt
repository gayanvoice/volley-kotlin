package com.volley.kotlin

interface RetryPolicy {
    fun getCurrentTimeout(): Int
    fun getCurrentRetryCount(): Int
    @Throws(VolleyError::class) fun retry(error: VolleyError?)
}