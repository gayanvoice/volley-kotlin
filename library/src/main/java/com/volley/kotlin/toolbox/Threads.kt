package com.volley.kotlin.toolbox

import android.os.Looper

open class Threads {
    private fun Threads() {}

    companion object {
        open fun throwIfNotOnMainThread() {
            check(Looper.myLooper() == Looper.getMainLooper()) { "Must be invoked from the main thread." }
        }
    }

}