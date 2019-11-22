package com.volley.kotlin

import android.os.SystemClock
import android.util.Log
import java.util.*

open class VolleyLog {
    companion object{
        var TAG: String? = "Volley"
        var DEBUG: Boolean = Log.isLoggable(TAG, Log.VERBOSE)
        var CLASS_NAME : String = VolleyLog::class.java.name
        fun setTag(tag : String?) {
            Log.d("Changing log tag to %s", tag)
            TAG = tag

            DEBUG = Log.isLoggable(TAG, Log.VERBOSE)
        }

        fun v(format: String?, vararg args: Any?) {
            if (DEBUG) {
                Log.v(TAG, buildMessage(format, *args))
            }
        }
        fun d(format: String?, vararg args: Any?) {
            Log.d(TAG, buildMessage(format, *args))
        }

        fun e(format: String?, vararg args: Any?) {
            Log.e(TAG, buildMessage(format, *args))
        }

        fun e(tr: Throwable?, format: String?, vararg args: Any?) {
            Log.e(TAG, buildMessage(format, *args), tr)
        }

        fun wtf(format: String?, vararg args: Any?) {
            Log.wtf(TAG, buildMessage(format, *args))
        }

        private fun buildMessage(format: String?, vararg args: Any?): String? {
            val msg:String = String.format(Locale.US, format!!, *args)
            val trace = Throwable().fillInStackTrace().stackTrace


            var caller = "<unknown>"

            for (i in 2 until trace.size) {
                val clazz = trace[i].className
                if (clazz != CLASS_NAME) {
                    var callingClass = trace[i].className
                    callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1)
                    callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1)
                    caller = callingClass + "." + trace[i].methodName
                    break
                }
            }
            return String.format(Locale.US,"[%d] %s: %s", Thread.currentThread().id, caller, msg)
        }

    }

    class MarkerLog {
        companion object {
            val ENABLED: Boolean = DEBUG
        }

        private val MIN_DURATION_FOR_LOGGING_MS: Long = 0

        inner class Marker(var name: String?, var thread: Long?, var time: Long?)

        private val mMarkers = ArrayList<Marker>()
        private var mFinished = false

        @Synchronized
        fun add(name: String?, threadId: Long) {
            check(!mFinished) { "Marker added to finished log" }
            mMarkers.add(
                Marker(
                    name,
                    threadId,
                    SystemClock.elapsedRealtime()
                )
            )
        }

        @Synchronized
        fun finish(header: String?) {
            mFinished = true
            val duration: Long = getTotalDuration()
            if (duration <= MIN_DURATION_FOR_LOGGING_MS) {
                return
            }
            var prevTime = mMarkers[0].time!!
            d("(%-4d ms) %s", duration, header)
            for (marker in mMarkers) {
                val thisTime = marker.time!!
                d(
                    "(+%-4d) [%2d] %s",
                    thisTime - prevTime,
                    marker.thread,
                    marker.name
                )
                prevTime = thisTime
            }
        }

        @Throws(Throwable::class)
        protected fun finalize() {
            if (!mFinished) {
                finish("Request on the loose")
                e("Marker log finalized without finish() - uncaught exit point for request")
            }
        }

        private fun getTotalDuration(): Long {
            if (mMarkers.size == 0) {
                return 0
            }
            val first:Long = mMarkers[0].time!!
            val last:Long = mMarkers[mMarkers.size - 1].time!!
            return last - first
        }


    }


}