package com.jackzhao.simplekv

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jackzhao.simplekv.ui.theme.SimpleKVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleKVTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
        val tmp1 = MyConfig.IS_FIRST.real.get(this, false)
        Log.e(Companion.TAG, "onCreate: $tmp1")
        MyConfig.IS_FIRST.real.set(this, false)
        val tmp2 = MyConfig.IS_FIRST.real.get(this, false)
        Log.e(Companion.TAG, "onCreate: $tmp2")

    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleKVTheme {
        Greeting("Android")
    }
}