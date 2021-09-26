package com.jackzhao.simple_kv

import android.content.Context

object SimpleKvMgr {
    var type: StorageType = StorageType.MMKV
    var context: Context? = null

    fun init(context: Context, type: StorageType = StorageType.MMKV) {
        this.type = type
        this.context = context
    }
}