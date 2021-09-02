package com.jackzhao.simple_kv;

import android.content.Context;

import java.util.Set;

public interface IKV {

    void setParam(Context context, String key, Object object);

    public Object getParam(Context context, String key, Object defaultObject);

    void clearAll(Context context);

    void clear(Context context, String key);

    Set<String> getAllKeys(Context context);
}
