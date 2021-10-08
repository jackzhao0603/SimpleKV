package com.jackzhao.simple_kv

import android.content.Context

object SimpleKvMgr {
    var type: StorageType = StorageType.MMKV
    var context: Context? = null

    @Synchronized
    fun init(_context: Context, type: StorageType = StorageType.MMKV) {
        if (context != null) {
            return
        }
        this.type = type
        this.context = _context
    }
}