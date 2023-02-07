package com.example.dummysport

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dummysport.app.*
import com.example.dummysport.app.laliga.DummyScreen
import com.example.dummysport.remoteconfig.RemoteConfig
import com.example.dummysport.sharedprefs.SharedPreferencesUtil
import com.example.dummysport.ui.theme.DummySportTheme


class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    return if (webView.canGoBack()) {
                        webView.goBack()
                        true
                    } else
                        false
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = mutableStateOf("")

        SharedPreferencesUtil.loadSharedPrefs(this)
        RemoteConfig.loadSettings()

        RemoteConfig.fetchingStatus.observe(this) {
            when (it) {
                RemoteConfig.FetchStatus.SUCCESSFUL -> {
                    val validatedUrl = RemoteConfig.url
                    if (validatedUrl.isNotEmpty()){
                        SharedPreferencesUtil.saveLink(validatedUrl)
                    }
                    url.value = validatedUrl
                }
                else -> {
                    Log.d("FirebaseError", "onCreate: Error")
                }
            }
        }

        setContent {
            DummySportTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    if (url.value.isEmpty() || showWV(this)) {
                        if (!isInternetAvailable()) {
                            Toast.makeText(
                                this,
                                "- для продолжения необходимо подключение к сети ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        DummyScreen()
                    } else {
                        LoadWebUrl(url = url.value, context = this) {
                            webView = it
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DummySportTheme {
        Greeting("Android")
    }
}