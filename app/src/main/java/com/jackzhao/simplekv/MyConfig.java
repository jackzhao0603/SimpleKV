package com.jackzhao.simplekv;

import com.jackzhao.simple_kv.IBaseKv;

public enum MyConfig implements IBaseKv {
    IS_FIRST(true),
    ;
    Object defaultValue;

    MyConfig(Object defValue) {
        this.defaultValue = defValue;
    }

}
