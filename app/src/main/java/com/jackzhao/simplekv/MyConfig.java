package com.jackzhao.simplekv;

import com.jackzhao.simple_kv.IBaseKv;

public enum MyConfig implements IBaseKv {
    IS_FIRST("is_first", true),
    ;
    String key;
    Object defaultValue;

    MyConfig(String key, Object defValue) {
        this.key = key;
        this.defaultValue = defValue;
    }

}
