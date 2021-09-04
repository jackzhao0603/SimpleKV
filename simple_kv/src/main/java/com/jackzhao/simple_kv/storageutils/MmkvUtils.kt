package com.jackzhao.simple_kv.storageutils

import android.content.Context
import com.jackzhao.simple_kv.IKV
import com.tencent.mmkv.MMKV


class MmkvUtils(context: Context, fileName: String) : IKV {
    private var mFileName = "MmkvDefault"
    var mKv: MMKV

    init {
        MMKV.initialize(context)
        this.mFileName = fileName
        mKv = MMKV.mmkvWithID(mFileName)
    }


    override fun setParam(context: Context, key: String, value: Any) {
        val type = value.javaClass.simpleName
        when (type) {
            "String" -> {
                mKv.encode(key, (value as String))
            }
            "Integer" -> {
                mKv.encode(key, (value as Int))
            }
            "Boolean" -> {
                mKv.encode(key, (value as Boolean))
            }
            "Float" -> {
                mKv.encode(key, (value as Float))
            }
            "Long" -> {
                mKv.encode(key, (value as Long))
            }
            "HashSet" -> {
                mKv.encode(key, value as Set<String?>)
            }
        }
        mKv.sync()
    }

    override fun getParam(context: Context, key: String, defaultObject: Any?): Any? {
        when (defaultObject?.javaClass?.simpleName ?: null) {
            "String" -> {
                return mKv.getString(key, defaultObject as String)
            }
            "Integer" -> {
                return mKv.getInt(key, (defaultObject as Int))
            }
            "Boolean" -> {
                return mKv.getBoolean(key, (defaultObject as Boolean))
            }
            "Float" -> {
                return mKv.getFloat(key, (defaultObject as Float))
            }
            "Long" -> {
                return mKv.getLong(key, (defaultObject as Long))
            }
            "HashSet" -> {
                return mKv.getStringSet(key, defaultObject as Set<String?>)
            }
            else -> return mKv.all[key]
        }
    }

    override fun clearAll(context: Context) {
        mKv.clearAll()
    }

    override fun clear(context: Context, key: String) {
        mKv.remove(key)
    }

    override fun getAllKeys(context: Context): Set<String> {
        return mKv.allKeys().toSet()
    }
}