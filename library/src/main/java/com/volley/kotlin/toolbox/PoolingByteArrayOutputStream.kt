package com.volley.kotlin.toolbox

import java.io.ByteArrayOutputStream
import java.io.IOException

class PoolingByteArrayOutputStream: ByteArrayOutputStream {

    private var DEFAULT_SIZE:Int = 256

    private var mPool: ByteArrayPool? = null

    constructor(pool: ByteArrayPool?) {
        this.mPool = pool
        DEFAULT_SIZE
    }

    constructor(pool: ByteArrayPool, size: Int) {
        this.mPool = pool
        buf = mPool!!.getBuf(Math.max(size, DEFAULT_SIZE))
    }

    @Throws(IOException::class)
    override fun close() {
        this.mPool!!.returnBuf(buf)
        buf = null
        super.close()
    }

    fun finalize() {
        this.mPool!!.returnBuf(buf)
    }

    private fun expand(i: Int) { /* Can the buffer handle @i more bytes, if not expand it */
        if (count + i <= buf.size) {
            return
        }
        val newbuf = mPool!!.getBuf((count + i) * 2)
        System.arraycopy(buf, 0, newbuf, 0, count)
        mPool!!.returnBuf(buf)
        buf = newbuf
    }

    @Synchronized
    override fun write(buffer: ByteArray?, offset: Int, len: Int) {
        expand(len)
        super.write(buffer, offset, len)
    }

    @Synchronized
    override fun write(oneByte: Int) {
        expand(1)
        super.write(oneByte)
    }

}