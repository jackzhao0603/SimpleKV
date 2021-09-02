package com.jackzhao.simple_kv;

import android.content.Context;

public interface IBaseKv {

    default String getFileName() {
        return this.getClass().getSimpleName();
    }

    default Object get(Context context, Object type) {
        return null;
    }

    default void set(Context context, Object v) {
    }

    default int increase(Context context) {
        return 0;
    }

    default void reset(Context context) {
    }

    default IBaseKv getReal() {
        IBaseKvProxy proxy = new IBaseKvProxy();
        proxy.newProxyInstance(this);
        return (IBaseKv) proxy.getProxyInstance();
    }
}

