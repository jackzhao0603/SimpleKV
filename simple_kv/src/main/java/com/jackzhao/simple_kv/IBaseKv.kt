package com.jackzhao.simple_kv

import android.content.Context

interface IBaseKv {
    val fileName: String
        get() = this.javaClass.simpleName

    operator fun get(context: Context?): Any? {
        return proxy[context]
    }

    fun getBoolean(context: Context?): Any? {
        return proxy.getBoolean(context)
    }

    fun getInt(context: Context?): Any? {
        return proxy.getInt(context)
    }

    fun getLong(context: Context?): Any? {
        return proxy.getLong(context)
    }

    fun getString(context: Context?): Any? {
        return proxy.getString(context)
    }

    fun getHashSet(context: Context?): Any? {
        return proxy.getHashSet(context)
    }

    operator fun set(context: Context?, v: Any?) {
        proxy[context] = v
    }

    fun increase(context: Context?): Int {
        return proxy.increase(context)
    }

    fun reset(context: Context?) {
        proxy.reset(context)
    }

    val proxy: IBaseKv
        get() {
            val proxy = IBaseKvProxy()
            proxy.newProxyInstance(this)
            return proxy.proxyInstance as IBaseKv
        }

    companion object {
        const val TAG = "IBaseKv"
    }
}