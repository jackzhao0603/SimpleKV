package com.jackzhao.simple_kv;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SpUtils implements IKV {
    private String mFileName = "SpDefault";

    public SpUtils(String mFileName) {
        this.mFileName = mFileName;
    }

    @Override
    public void setParam(Context context, String key, Object object) {

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        } else if ("HashSet".equals(type)) {
            editor.putStringSet(key, (Set<String>) object);
        }

        editor.commit();
    }

    @Override
    public Object getParam(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_MULTI_PROCESS);

        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        } else if ("HashSet".equals(type)) {
            return sp.getStringSet(key, (Set<String>) defaultObject);
        }

        return defaultObject;
    }


    @Override
    public void clearAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    @Override
    public void clear(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    @Override
    public Set<String> getAllKeys(Context context) {
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_MULTI_PROCESS);
        return sp.getAll().keySet();
    }

}
