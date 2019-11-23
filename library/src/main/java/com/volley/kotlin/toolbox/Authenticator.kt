package com.volley.kotlin.toolbox

import com.volley.kotlin.AuthFailureError

interface Authenticator {
    @Throws(AuthFailureError::class)
    fun getAuthToken(): String?

    fun invalidateAuthToken(authToken: String?)
}