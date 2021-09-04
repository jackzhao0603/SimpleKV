package com.jackzhao.simple_kv

object SimpleKvMgr {
    var type: StorageType = StorageType.SHARE_PERF
    fun init(type: StorageType) {
        this.type = type
    }
}