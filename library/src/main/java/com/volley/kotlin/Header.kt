package com.volley.kotlin

import android.text.TextUtils

open class Header(name: String, value: String) {
    private var mName: String? = name
    private var mValue: String? = value

    fun getName(): String {
        return mName!!
    }

    fun getValue(): String {
        return mValue!!
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val header = other as Header
        return TextUtils.equals(mName, header.mName) && TextUtils.equals(mValue, header.mValue)
    }

    override fun hashCode(): Int {
        var result = mName.hashCode()
        result = 31 * result + mValue.hashCode()
        return result
    }

    override fun toString(): String {
        return "Header[name=" + mName + "value= " + mValue + "]"
    }
}
