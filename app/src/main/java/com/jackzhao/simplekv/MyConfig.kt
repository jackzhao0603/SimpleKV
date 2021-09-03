package com.jackzhao.simplekv

import com.jackzhao.simple_kv.IBaseKv
import java.util.HashSet

enum class MyConfig(
    var defaultValue: Any
) : IBaseKv {
    IS_FIRST(true),
    LUNCH_TIMES(0),
    NAME("no name"),
    TMP_HASH(HashSet<String>()),
    ;
}