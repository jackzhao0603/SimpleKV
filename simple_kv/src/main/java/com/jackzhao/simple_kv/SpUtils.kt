package com.jackzhao.simple_kv

import android.content.Context

class SpUtils(mFileName: String) : IKV {
    private var mFileName = "SpDefault"


    override fun setParam(context: Context, key: String, value: Any) {
        val type = value.javaClass.simpleName
        val sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE)
        val editor = sp.edit()
        if ("String" == type) {
            editor.putString(key, value as String)
        } else if ("Integer" == type) {
            editor.putInt(key, (value as Int))
        } else if ("Boolean" == type) {
            editor.putBoolean(key, (value as Boolean))
        } else if ("Float" == type) {
            editor.putFloat(key, (value as Float))
        } else if ("Long" == type) {
            editor.putLong(key, (value as Long))
        } else if ("HashSet" == type) {
            editor.putStringSet(key, value as Set<String?>)
        }
        editor.commit()
    }

    override fun getParam(context: Context, key: String, defaultObject: Any?): Any? {
        val type = defaultObject?.javaClass?.simpleName ?: null
        val sp = context.getSharedPreferences(mFileName, Context.MODE_MULTI_PROCESS)
        when (type) {
            "String" -> {
                return sp.getString(key, defaultObject as String)!!
            }
            "Integer" -> {
                return sp.getInt(key, (defaultObject as Int))
            }
            "Boolean" -> {
                return sp.getBoolean(key, (defaultObject as Boolean))
            }
            "Float" -> {
                return sp.getFloat(key, (defaultObject as Float))
            }
            "Long" -> {
                return sp.getLong(key, (defaultObject as Long))
            }
            "HashSet" -> {
                return sp.getStringSet(key, defaultObject as Set<String?>)!!
            }
            else -> return sp.all[key]
        }
    }


    override fun clearAll(context: Context) {
        val sp = context.getSharedPreferences(
            mFileName,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.clear().commit()
    }

    override fun clear(context: Context, key: String) {
        val sp = context.getSharedPreferences(
            mFileName,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.remove(key)
        editor.commit()
    }

    override fun getAllKeys(context: Context): Set<String> {
        val sp = context.getSharedPreferences(mFileName, Context.MODE_MULTI_PROCESS)
        return sp.all.keys
    }

    init {
        this.mFileName = mFileName
    }
}