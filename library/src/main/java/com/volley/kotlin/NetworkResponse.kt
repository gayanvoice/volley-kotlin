package com.volley.kotlin

import java.net.HttpURLConnection
import java.util.*

open class NetworkResponse {

        var statusCode: Int = 0
        var data: ByteArray
        var headers: Map<String, String>? = null
        var allHeaders: List<Header>? = null
        var notModified: Boolean = false
        var networkTimeMs: Long = 0

    @Deprecated("")
    constructor(statusCode: Int, data: ByteArray, headers: Map<String, String>?, notModified: Boolean, networkTimeMs: Long) {
        this.statusCode = statusCode
        this.data = data
        this.headers = headers
        this.allHeaders = toAllHeaderList(headers)
        this.notModified = notModified
        this.networkTimeMs = networkTimeMs
    }

    constructor(statusCode: Int, data: ByteArray, notModified: Boolean, networkTimeMs: Long, allHeaders: List<Header>?) {
        this.statusCode = statusCode
        this.data = data
        this.headers = toHeaderMap(allHeaders)
        this.allHeaders = allHeaders
        this.notModified = notModified
        this.networkTimeMs = networkTimeMs
    }

    @Deprecated("")
    constructor(statusCode: Int, data: ByteArray, headers: Map<String, String>?, notModified: Boolean) {
        this.statusCode  = statusCode
        this.data = data
        this.headers = headers
        this.notModified = notModified
        this.networkTimeMs = 0
    }

    constructor(data: ByteArray) {
        this.statusCode = HttpURLConnection.HTTP_OK
        this.data = data
        this.notModified = false
        this.networkTimeMs = 0
        this.allHeaders = emptyList<Header>()
    }

    @Deprecated("")
    constructor(data: ByteArray, headers: Map<String, String>?) {
        this.statusCode = HttpURLConnection.HTTP_OK
        this.data = data
        this.headers = headers
        this.notModified = false
        this.networkTimeMs = 0
    }

    constructor(statusCode: Int, data: ByteArray, headers: Map<String, String>, allHeaders: List<Header>?, notModified: Boolean, networkTimeMs: Long) {
        this.statusCode = statusCode
        this.data = data
        this.headers = headers
        if (allHeaders == null) {
            this.allHeaders = null
        } else {
            this.allHeaders = Collections.unmodifiableList(allHeaders)
        }
        this.notModified = notModified
        this.networkTimeMs = networkTimeMs
    }

    private fun toHeaderMap(allHeaders: List<Header>?): Map<String, String>? {
        if (allHeaders == null) {
            return null
        }
        if (allHeaders.isEmpty()) {
            return emptyMap()
        }
        val headers: MutableMap<String, String> =
            TreeMap(java.lang.String.CASE_INSENSITIVE_ORDER)

        for (header in allHeaders) {
            headers[header.getName()] = header.getValue()
        }
        return headers
    }


    /*

     private static List<Header> toAllHeaderList(Map<String, String> headers) {
        if (headers == null) {
            return null;
        }
        if (headers.isEmpty()) {
            return Collections.emptyList();
        }
        List<Header> allHeaders = new ArrayList<>(headers.size());
        for (Map.Entry<String, String> header : headers.entrySet()) {
            allHeaders.add(new Header(header.getKey(), header.getValue()));
        }
        return allHeaders;
    }
     */

    private fun toAllHeaderList(headers: Map<String, String>?): List<Header>? {
        if (headers == null) {
            return null
        }
        if (headers.isEmpty()) {
            return emptyList()
        }
        val allHeaders: MutableList<Header> = ArrayList(headers.size)
        for ((key, value) in headers) {
            allHeaders.add(Header(key, value))
        }
        return allHeaders
    }
}