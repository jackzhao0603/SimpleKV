package com.jackzhao.simplekv;

import com.jackzhao.simple_kv.IBaseKv;

import java.util.HashSet;

public enum MyConfig implements IBaseKv {
    IS_FIRST(true),
    LUNCH_TIMES(0),
    NAME("no name"),
    TMP_HASH(new HashSet<String>());
    Object defaultValue;

    MyConfig(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

}
