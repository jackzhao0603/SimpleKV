package com.jackzhao.simple_kv

import android.content.Context

interface IKV {
    fun setParam(key: String, value: Any)
    fun getParam(key: String, defaultObject: Any?): Any?
    fun clearAll()
    fun clear(key: String)
    fun getAllKeys(): Set<String>
}