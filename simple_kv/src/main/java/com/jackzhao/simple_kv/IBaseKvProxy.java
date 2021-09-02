package com.jackzhao.simple_kv;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IBaseKvProxy implements InvocationHandler {
    private IBaseKv realObject;
    SpUtils spUtils = null;

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (spUtils == null) {
            spUtils = new SpUtils(realObject.getFileName());
        }
        if ("getReal".equals(method.getName())) {
            return method.invoke(realObject, args);
        }


        if ("reset".equals(method.getName())) {
            Object defaultValue = ReflectionUtils.getField(realObject.getClass(),
                    "defaultValue",
                    realObject);
            ReflectionUtils.setField(realObject.getClass(),
                    "key",
                    realObject,
                    defaultValue);
            return null;
        }

        if ("get".equals(method.getName())) {
            String key = (String) ReflectionUtils.getField(realObject.getClass(),
                    "key",
                    realObject);
            Object defaultValue = ReflectionUtils.getField(realObject.getClass(),
                    "defaultValue",
                    realObject);
            return spUtils.getParam((Context) args[0], key, defaultValue);
        }

        if ("set".equals(method.getName())) {
            String key = (String) ReflectionUtils.getField(realObject.getClass(),
                    "key",
                    realObject);
            spUtils.setParam((Context) args[0], key, args[1]);
            return null;
        }

        if ("increase".equals(method.getName())) {
            String key = (String) ReflectionUtils.getField(realObject.getClass(),
                    "key",
                    realObject);
            Object defaultValue = ReflectionUtils.getField(realObject.getClass(),
                    "defaultValue",
                    realObject);
            int value = (int) spUtils.getParam((Context) args[0], key, defaultValue);
            value += 1;
            spUtils.setParam((Context) args[0], key, value);
            return value;
        }


        return null;
    }

    public Object newProxyInstance(IBaseKv realObject) {
        this.realObject = realObject;
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(),
                realObject.getClass().getInterfaces(), this);
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(),
                realObject.getClass().getInterfaces(), this);
    }
}
