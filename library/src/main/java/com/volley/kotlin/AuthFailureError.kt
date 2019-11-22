package com.volley.kotlin

import android.content.Intent

open class AuthFailureError:VolleyError {
    var mResolutionIntent : Intent? = null

    constructor()

    constructor(intent : Intent?) {
        this.mResolutionIntent = intent
    }

    constructor(response : NetworkResponse?) : super(response)

    constructor(message : String?) : super(message)

    constructor(message : String?, reason : Exception) : super(message, reason)

    fun getResolutionIntent(): Intent? {
        return mResolutionIntent
    }

    /*
     @Override
    public String getMessage() {
        if (mResolutionIntent != null) {
            return "User needs to (re)enter credentials.";
        }
        return super.getMessage();
    }
     */

    open fun getErrorMessage(): String? {
        return if (mResolutionIntent != null) {
            "User needs to (re)enter credentials."
        } else super.message
    }

}