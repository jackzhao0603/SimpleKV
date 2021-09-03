package com.jackzhao.simple_kv

import android.content.Context
import com.jackzhao.simple_kv.ReflectionUtils.getField
import com.jackzhao.simple_kv.ReflectionUtils.invoke
import com.jackzhao.simple_kv.ReflectionUtils.setField
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

class IBaseKvProxy : InvocationHandler {
    private lateinit var realObject: IBaseKv
    var spUtils: SpUtils? = null

    @Throws(Throwable::class)
    override fun invoke(o: Any, method: Method, args: Array<Any>): Any? {
        if (spUtils == null) {
            spUtils = SpUtils(realObject.fileName)
        }
        if ("getReal" == method.name) {
            return method.invoke(realObject, *args)
        }
        if ("reset" == method.name) {
            val defaultValue = defaultValue
            setField(
                realObject.javaClass,
                "key",
                realObject,
                defaultValue
            )
            return null
        }
        if ("get" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return spUtils!!.getParam((args[0] as Context), key!!, defaultValue!!)
        }
        if ("getBoolean" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return spUtils!!.getParam((args[0] as Context), key!!, defaultValue!!) as Boolean
        }
        if ("getInt" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return spUtils!!.getParam((args[0] as Context), key!!, defaultValue!!) as Int
        }
        if ("getLong" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return spUtils!!.getParam((args[0] as Context), key!!, defaultValue!!) as Long
        }
        if ("getString" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return spUtils!!.getParam((args[0] as Context), key!!, defaultValue!!) as String
        }
        if ("getHashSet" == method.name) {
            val key = name
            val defaultValue = defaultValue
            return spUtils!!.getParam((args[0] as Context), key!!, defaultValue!!) as HashSet<*>
        }
        if ("set" == method.name) {
            val key = name
            spUtils!!.setParam((args[0] as Context), key!!, args[1])
            return null
        }
        if ("increase" == method.name) {
            val key = name
            val defaultValue = defaultValue
            var value = spUtils!!.getParam((args[0] as Context), key!!, defaultValue!!) as Int
            value += 1
            spUtils!!.setParam((args[0] as Context), key, value)
            return value
        }
        return null
    }

    private val name: String?
        private get() = invoke(realObject.javaClass, "name", realObject) as String?
    private val defaultValue: Any?
        private get() {
            try {
                return getField(
                    realObject.javaClass,
                    "defaultValue",
                    realObject
                )
            } catch (e: Exception) {
            }
            return null
        }

    fun newProxyInstance(realObject: IBaseKv): Any {
        this.realObject = realObject
        return Proxy.newProxyInstance(
            realObject.javaClass.classLoader,
            realObject.javaClass.interfaces, this
        )
    }

    val proxyInstance: Any
        get() = Proxy.newProxyInstance(
            realObject.javaClass.classLoader,
            realObject.javaClass.interfaces, this
        )
}