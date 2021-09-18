package com.jackzhao.simple_kv

import com.jackzhao.simple_kv.ReflectionUtils.getField
import com.jackzhao.simple_kv.ReflectionUtils.invoke
import com.jackzhao.simple_kv.ReflectionUtils.setField
import com.jackzhao.simple_kv.storageutils.MmkvUtils
import com.jackzhao.simple_kv.storageutils.SpUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

class IBaseKvProxy : InvocationHandler {
    private val TAG = "IBaseKvProxy"
    private lateinit var mRealObject: IBaseKv
    private var mIkv: IKV? = null

    constructor()


    @Throws(Throwable::class)
    override fun invoke(o: Any, method: Method, args: Array<Any>?): Any? {
        if (mIkv == null) {
            mIkv = if (SimpleKvMgr.type == StorageType.MMKV) {
                MmkvUtils(mRealObject.fileName)
            } else {
                SpUtils(mRealObject.fileName)
            }
        }
        if ("getReal" == method.name) {
            return method.invoke(mRealObject, null)
        }
        if ("reset" == method.name) {
            val defaultValue = defaultValue
            setField(
                mRealObject.javaClass,
                "key",
                mRealObject,
                defaultValue
            )
            return null
        }
        if ("get" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam(key!!, defaultValue!!)
        }
        if ("getBoolean" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam(key!!, defaultValue!!) as Boolean
        }
        if ("getInt" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam(key!!, defaultValue!!) as Int
        }
        if ("getLong" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam(key!!, defaultValue!!) as Long
        }
        if ("getString" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam(key!!, defaultValue!!) as String
        }
        if ("getHashSet" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam(key!!, defaultValue!!) as HashSet<*>
        }
        if ("set" == method.name) {
            val key = name
            args?.get(0)?.let { mIkv!!.setParam(key!!, it) }
            return null
        }
        if ("increase" == method.name) {
            val key = name
            val defaultValue = defaultValue
            var value = mIkv!!.getParam(key!!, defaultValue!!) as Int
            value += 1
            mIkv!!.setParam(key, value)
            return value
        }
        return null
    }

    private val name: String?
        private get() = invoke(mRealObject.javaClass, "name", mRealObject) as String?
    private val defaultValue: Any?
        private get() {
            try {
                return getField(
                    mRealObject.javaClass,
                    "defaultValue",
                    mRealObject
                )
            } catch (e: Exception) {
            }
            return null
        }

    fun newProxyInstance(realObject: IBaseKv): Any {
        this.mRealObject = realObject
        return Proxy.newProxyInstance(
            realObject.javaClass.classLoader,
            realObject.javaClass.interfaces, this
        )
    }

    val proxyInstance: Any
        get() = Proxy.newProxyInstance(
            mRealObject.javaClass.classLoader,
            mRealObject.javaClass.interfaces, this
        )
}