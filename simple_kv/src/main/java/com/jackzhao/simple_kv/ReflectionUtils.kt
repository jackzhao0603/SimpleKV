package com.jackzhao.simple_kv

import android.util.Log
import java.util.*

/**
 * Created by jack_zhao on 2018/8/15.
 */
object ReflectionUtils {
    private val TAG = ReflectionUtils::class.java.simpleName
    fun getField(clazz: Class<*>, fieldName: String?, instance: Any?): Any? {
        return try {
            val field = clazz.getDeclaredField(fieldName)
            field.isAccessible = true
            field[instance]
        } catch (e: NoSuchFieldException) {
            val superClass = clazz.superclass
            if (superClass != null) {
                return getField(superClass, fieldName, instance)
            }
            Log.e(TAG, "getField(clazz) reached super Object but still can't find field", e)
            null
        } catch (e: Exception) {
            Log.e(TAG, "getField(clazz) error", e)
            null
        }
    }

    fun getField(className: String?, fieldName: String?, instance: Any?): Any? {
        return try {
            val clazz = Class.forName(className)
            getField(clazz, fieldName, instance)
        } catch (e: Exception) {
            Log.e(TAG, "getField(className) error", e)
            null
        }
    }

    fun newInstance(
        clazz: Class<*>,
        parameters: Array<Any?>,
        parameterTypes: Array<Class<*>?>
    ): Any? {
        return try {
            val constructor = clazz.getDeclaredConstructor(*parameterTypes)
            constructor.isAccessible = true
            constructor.newInstance(*parameters)
        } catch (e: Exception) {
            Log.e(TAG, "newInstance(clazz) error", e)
            null
        }
    }

    fun newInstance(clazz: Class<*>): Any? {
        return try {
            val constructor = clazz.getDeclaredConstructor()
            constructor.isAccessible = true
            constructor.newInstance()
        } catch (e: Exception) {
            Log.e(TAG, "newInstance(clazz) error", e)
            null
        }
    }

    fun newInstance(className: String?): Any? {
        return try {
            val clazz = Class.forName(className)
            newInstance(clazz)
        } catch (e: Exception) {
            Log.e(TAG, "newInstance(className) error", e)
            null
        }
    }

    operator fun invoke(clazz: Class<*>, methodName: String?, instance: Any?): Any? {
        return try {
            val method = clazz.getMethod(methodName)
            method.isAccessible = true
            method.invoke(instance)
        } catch (e: Exception) {
            Log.e(TAG, "invoke(clazz) error", e)
            null
        }
    }

    operator fun invoke(className: String?, methodName: String?, instance: Any?): Any? {
        return try {
            val clazz = Class.forName(className)
            invoke(clazz, methodName, instance)
        } catch (e: Exception) {
            Log.e(TAG, "invoke(className) error", e)
            null
        }
    }

    operator fun invoke(
        clazz: Class<*>,
        methodName: String?,
        instance: Any?,
        parameters: Array<Any?>,
        parameterTypes: Array<Class<*>?>
    ): Any? {
        return try {
            val method = clazz.getMethod(methodName, *parameterTypes)
            method.isAccessible = true
            method.invoke(instance, *parameters)
        } catch (e: Exception) {
            Log.e(TAG, "invoke(clazz=$clazz) error", e)
            null
        }
    }

    operator fun invoke(
        className: String,
        methodName: String?,
        instance: Any?,
        parameters: Array<Any?>,
        parameterTypes: Array<Class<*>?>
    ): Any? {
        return try {
            val clazz = Class.forName(className)
            val method = clazz.getMethod(methodName, *parameterTypes)
            method.isAccessible = true
            method.invoke(instance, *parameters)
        } catch (e: Exception) {
            Log.e(TAG, "invoke(clazz=$className) error", e)
            null
        }
    }

    fun invokePublicStatic(
        clazz: Class<*>,
        methodName: String?,
        parameters: Array<Any?>,
        parameterTypes: Array<Class<*>?>
    ): Any? {
        return try {
            val method = clazz.getMethod(methodName, *parameterTypes)
            method.invoke(null, *parameters)
        } catch (e: Exception) {
            Log.e(TAG, "invoke(clazz=$clazz) error", e)
            null
        }
    }

    fun invokePublic(
        clazz: Class<*>,
        methodName: String?,
        instance: Any?,
        parameters: Array<Any?>,
        parameterTypes: Array<Class<*>?>
    ): Any? {
        return try {
            val method = clazz.getMethod(methodName, *parameterTypes)
            method.invoke(instance, *parameters)
        } catch (e: Exception) {
            Log.e(TAG, "invoke(clazz=$clazz) error", e)
            null
        }
    }

    fun invokePublic(
        className: String,
        methodName: String?,
        instance: Any?,
        parameters: Array<Any?>,
        parameterTypes: Array<Class<*>?>
    ): Any? {
        return try {
            val clazz = Class.forName(className)
            val method = clazz.getMethod(methodName, *parameterTypes)
            method.invoke(instance, *parameters)
        } catch (e: Exception) {
            Log.e(TAG, "invoke(clazz=$className) error", e)
            null
        }
    }

    fun setField(clazz: Class<*>, fieldName: String, instance: Any?, newValue: Any?) {
        try {
            val field = clazz.getDeclaredField(fieldName)
            field.isAccessible = true
            field[instance] = newValue
        } catch (e: NoSuchFieldException) {
            val superClass = clazz.superclass
            if (superClass != null) {
                setField(superClass, fieldName, instance, newValue)
                return
            }
            Log.e(TAG, "setField(clazz) reached super Object but still can't find field", e)
        } catch (e: Exception) {
            Log.e(TAG, "setField(clazz) error", e)
        }
    }

    fun setField(className: String?, fieldName: String, instance: Any?, newValue: Any?) {
        try {
            val clazz = Class.forName(className)
            setField(clazz, fieldName, instance, newValue)
        } catch (e: Exception) {
            Log.e(TAG, "setField(className) error", e)
        }
    }

    fun getAllInterfaces(clazz: Class<*>?): List<Class<*>?> {
        var clazz: Class<*>? = clazz ?: return ArrayList()
        val interfaces: MutableSet<Class<*>?> = HashSet()
        while (clazz != null && clazz != Any::class.java) {
            for (interf in clazz.interfaces) {
                interfaces.add(interf)
                interfaces.addAll(getAllInterfaces(interf))
            }
            clazz = clazz.superclass
        }
        return ArrayList(interfaces)
    }
}