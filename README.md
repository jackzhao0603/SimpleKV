# SimpleKV

[![](https://jitpack.io/v/jackzhao0603/SimpleKV.svg)](https://jitpack.io/#jackzhao0603/SimpleKV)
[![Apache License 2.0][1]][2]
[![API][3]][4]

# How to use

## Quick start

1. Add it in your root build.gradle at the end of repositories:
    ```gradle
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
    ```
2. Add the dependency
    ```gradle
     dependencies {
	     implementation 'com.github.jackzhao0603:SimpleKV:0.1.4'
	 }
    ```
3. Create KV class
    ```kotlin
    enum class MyConfig(var defaultValue: Any) : IBaseKv {
       IS_FIRST(true),
       LUNCH_TIMES(0), 
       NAME("no name"), 
       TMP_HASH(HashSet<String>());
    }
    ```
4 Select mmkv to replace SP(options)
   ```kotlin
       SimpleKvMgr.init(StorageType.MMKV)
   ```

5 Use it
   ```kotlin
        val tmp1 = MyConfig.IS_FIRST.get(this)
        Log.i(Companion.TAG, "onCreate: $tmp1")
        MyConfig.IS_FIRST.set(this, false)
        val tmp2 = MyConfig.IS_FIRST.get(this)
        Log.i(Companion.TAG, "onCreate: $tmp2")

        Log.i(Companion.TAG, "onCreate: ${MyConfig.LUNCH_TIMES.getInt(this)}")
        Log.i(Companion.TAG, "onCreate: ${MyConfig.IS_FIRST.getBoolean(this)}")
        Log.i(Companion.TAG, "onCreate: ${MyConfig.NAME.getString(this)}")
        Log.i(Companion.TAG, "onCreate: ${MyConfig.TMP_HASH.getHashSet(this)}")
   ```

[1]:https://img.shields.io/:License-Apache%202.0-blue.svg

[2]:https://www.apache.org/licenses/LICENSE-2.0.html

[3]:https://img.shields.io/badge/API-14%2B-red.svg?style=flat

[4]:https://android-arsenal.com/api?level=16