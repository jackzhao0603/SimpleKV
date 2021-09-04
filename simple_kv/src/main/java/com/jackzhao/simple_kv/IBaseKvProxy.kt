package com.jackzhao.simple_kv

import android.content.Context
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
    private lateinit var mRealObject: IBaseKv
    private var mIkv: IKV? = null

    constructor()


    @Throws(Throwable::class)
    override fun invoke(o: Any, method: Method, args: Array<Any>): Any? {
        if (mIkv == null) {
            if (SimpleKvMgr.type == StorageType.MMKV) {
                mIkv = MmkvUtils((args[0] as Context),mRealObject.fileName)
            } else {
                mIkv = SpUtils(mRealObject.fileName)
            }
        }
        if ("getReal" == method.name) {
            return method.invoke(mRealObject, *args)
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
            return mIkv!!.getParam((args[0] as Context), key!!, defaultValue!!)
        }
        if ("getBoolean" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam((args[0] as Context), key!!, defaultValue!!) as Boolean
        }
        if ("getInt" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam((args[0] as Context), key!!, defaultValue!!) as Int
        }
        if ("getLong" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam((args[0] as Context), key!!, defaultValue!!) as Long
        }
        if ("getString" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam((args[0] as Context), key!!, defaultValue!!) as String
        }
        if ("getHashSet" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return mIkv!!.getParam((args[0] as Context), key!!, defaultValue!!) as HashSet<*>
        }
        if ("set" == method.name) {
            val key = name
            mIkv!!.setParam((args[0] as Context), key!!, args[1])
            return null
        }
        if ("increase" == method.name) {
            val key = name
            val defaultValue = defaultValue
            var value = mIkv!!.getParam((args[0] as Context), key!!, defaultValue!!) as Int
            value += 1
            mIkv!!.setParam((args[0] as Context), key, value)
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