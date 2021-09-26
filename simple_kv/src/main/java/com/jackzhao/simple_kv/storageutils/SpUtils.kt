package com.jackzhao.simple_kv.storageutils

import android.content.Context
import com.jackzhao.simple_kv.IKV
import com.jackzhao.simple_kv.SimpleKvMgr

class SpUtils(fileName: String) : IKV {
    private var mFileName = "SpDefault"
    private val mContext = SimpleKvMgr.context!!


    override fun setParam(key: String, value: Any) {
        val type = value.javaClass.simpleName
        val sp = mContext.getSharedPreferences(mFileName, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when (type) {
            "String" -> {
                editor.putString(key, value as String)
            }
            "Integer" -> {
                editor.putInt(key, (value as Int))
            }
            "Boolean" -> {
                editor.putBoolean(key, (value as Boolean))
            }
            "Float" -> {
                editor.putFloat(key, (value as Float))
            }
            "Long" -> {
                editor.putLong(key, (value as Long))
            }
            "HashSet" -> {
                editor.putStringSet(key, value as Set<String?>)
            }
        }
        editor.commit()
    }

    override fun getParam(key: String, defaultObject: Any?): Any? {
        val type = defaultObject?.javaClass?.simpleName ?: null
        val sp = mContext.getSharedPreferences(mFileName, Context.MODE_MULTI_PROCESS)
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


    override fun clearAll() {
        val sp = mContext.getSharedPreferences(
            mFileName,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.clear().commit()
    }

    override fun clear(key: String) {
        val sp = mContext.getSharedPreferences(
            mFileName,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.remove(key)
        editor.commit()
    }

    override fun getAllKeys(): Set<String> {
        val sp = mContext.getSharedPreferences(mFileName, Context.MODE_MULTI_PROCESS)
        return sp.all.keys
    }

    init {
        this.mFileName = fileName
    }
}