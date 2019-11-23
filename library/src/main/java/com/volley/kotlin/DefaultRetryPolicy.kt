package com.volley.kotlin

open class DefaultRetryPolicy : RetryPolicy {
    private var mCurrentTimeoutMs : Int = 0
    private var mCurrentRetryCount : Int = 0
    private var mMaxNumRetries : Int = 0
    private var mBackoffMultiplier : Float = 0f

    var DEFAULT_TIMEOUT_MS : Int = 2500
    var DEFAULT_MAX_RETRIES : Int = 1
    var DEFAULT_BACKOFF_MULT : Float = 1f

    constructor(){
        this.DEFAULT_TIMEOUT_MS
        this.DEFAULT_MAX_RETRIES
        this.DEFAULT_BACKOFF_MULT
    }

    constructor(initialTimeoutMs: Int, maxNumRetries: Int, backoffMultiplier: Float) {
        this.mCurrentTimeoutMs = initialTimeoutMs
        this.mMaxNumRetries = maxNumRetries
        this.mBackoffMultiplier = backoffMultiplier
    }

    override fun getCurrentTimeout(): Int {
        return this.mCurrentTimeoutMs
    }

    override fun getCurrentRetryCount(): Int {
        return this.mCurrentRetryCount
    }

    open fun getBackoffMultiplier(): Float {
        return this.mBackoffMultiplier
    }

    @Throws(VolleyError::class)
    override fun retry(error: VolleyError?) {
        mCurrentRetryCount++
        mCurrentTimeoutMs += (mCurrentTimeoutMs * mBackoffMultiplier).toInt()
        if (!hasAttemptRemaining()) {
            throw error!!
        }
    }

    protected open fun hasAttemptRemaining(): Boolean {
        return mCurrentRetryCount <= mMaxNumRetries
    }


}