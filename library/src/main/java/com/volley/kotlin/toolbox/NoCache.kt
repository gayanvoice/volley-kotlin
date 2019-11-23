package com.volley.kotlin.toolbox

import com.volley.kotlin.Cache

class NoCache: Cache {
    override fun clear() {}
    override operator fun get(key: String?): Cache.Entry? {
        return null
    }
    override fun put(key: String?, entry: Cache.Entry?) {}

    override fun invalidate(key: String?, fullExpire: Boolean?) {}

    override fun remove(key: String?) {}

    override fun initialize() {}

}