package com.jackzhao.simple_kv;

import android.content.Context;

public interface IBaseKv {
    static final String TAG = "IBaseKv";

    default String getFileName() {
        return this.getClass().getSimpleName();
    }

    default Object get(Context context) {
        return getProxy().get(context);
    }

    default Object getBoolean(Context context) {
        return getProxy().getBoolean(context);
    }

    default Object getInt(Context context) {
        return getProxy().getInt(context);
    }

    default Object getLong(Context context) {
        return getProxy().getLong(context);
    }

    default Object getString(Context context) {
        return getProxy().getString(context);
    }

    default Object getHashSet(Context context) {
        return getProxy().getHashSet(context);
    }

    default void set(Context context, Object v) {
        getProxy().set(context, v);
    }

    default int increase(Context context) {
        return getProxy().increase(context);
    }

    default void reset(Context context) {
        getProxy().reset(context);
    }

    default IBaseKv getProxy() {
        IBaseKvProxy proxy = new IBaseKvProxy();
        proxy.newProxyInstance(this);
        return (IBaseKv) proxy.getProxyInstance();
    }
}

