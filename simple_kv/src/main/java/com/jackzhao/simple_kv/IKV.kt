package com.jackzhao.simple_kv

import android.content.Context

interface IKV {
    fun setParam(context: Context, key: String, value: Any)
    fun getParam(context: Context, key: String, defaultObject: Any?): Any?
    fun clearAll(context: Context)
    fun clear(context: Context, key: String)
    fun getAllKeys(context: Context): Set<String>
}