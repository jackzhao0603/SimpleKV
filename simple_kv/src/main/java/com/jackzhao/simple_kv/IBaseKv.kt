package com.jackzhao.simple_kv

interface IBaseKv {
    val fileName: String
        get() = this.javaClass.simpleName

    fun get(): Any? {
        return proxy.get()
    }

    fun getBoolean(): Boolean {
        return proxy.getBoolean()
    }

    fun getInt(): Int {
        return proxy.getInt()
    }

    fun getLong(): Long {
        return proxy.getLong()
    }

    fun getString(): String {
        return proxy.getString()
    }

    fun getHashSet(): HashSet<Any?> {
        return proxy.getHashSet()
    }

    fun set(v: Any?) {
        proxy.set(v)
    }

    fun increase(): Int {
        return proxy.increase()
    }

    fun reset() {
        proxy.reset()
    }

    val proxy: IBaseKv
        get() {
            val proxy = IBaseKvProxy()
            proxy.newProxyInstance(this)
            return proxy.proxyInstance as IBaseKv
        }
}