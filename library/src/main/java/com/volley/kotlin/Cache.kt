package com.volley.kotlin

interface Cache {
    fun get(key: String?): Entry?
    fun put(key: String?, entry: Entry?)
    fun initialize()
    fun invalidate(key: String?, fullExpire: Boolean?)
    fun remove(key: String?)
    fun clear()

    open class Entry {
        var data : ByteArray? = null
        var etag : String? = null
        var serverDate : Long? = 0
        var lastModified : Long? = 0
        var ttl : Long? = null
        var softTtl : Long? = 0

        var responseHeaders : Map<String, String>? = emptyMap()
        var allResponseHeaders: List<Header>? = null

        fun isExpired(): Boolean {
            return ttl!! < System.currentTimeMillis()
        }

        fun refreshNeeded(): Boolean {
            return softTtl!! < System.currentTimeMillis()
        }
    }

}