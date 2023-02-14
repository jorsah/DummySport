package calc.win.betboom

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import calc.win.betboom.app.InternetNotConnected
import calc.win.betboom.utils.isNetworkAvailable
import calc.win.betboom.app.laliga.DummyScreen
import calc.win.betboom.app.webview.LoadWebUrl
import calc.win.betboom.utils.showWV
import calc.win.betboom.remoteconfig.RemoteConfig
import calc.win.betboom.sharedprefs.SharedPreferencesUtil
import calc.win.betboom.theme.DummySportTheme
import calc.win.betboom.utils.FirebaseResult


class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView
    private val screenState = mutableStateOf<FirebaseResult<String>>(FirebaseResult.Loading)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedPreferencesUtil.loadSharedPrefs(this)
        RemoteConfig.loadSettings()
        observe()

        setContent {
            DummySportTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    when (screenState.value) {
                        is FirebaseResult.OpenWeb -> {

                            val url = (screenState.value as FirebaseResult.OpenWeb<String>).data

                            if (url.isEmpty() || showWV(this)) {
                                if (isNetworkAvailable(this)) {
                                    screenState.value = FirebaseResult.OpenDummyScreen
                                } else
                                    screenState.value = FirebaseResult.OpenInternetConnectionScreen
                            } else {
                                LoadWebUrl(url = url, context = this) { webView = it }
                            }
                        }
                        FirebaseResult.OpenDummyScreen -> { DummyScreen() }
                        FirebaseResult.Loading -> {}
                        FirebaseResult.OpenInternetConnectionScreen -> { InternetNotConnected() }
                    }
                }

            }
        }


    }

    private fun observe(){
        RemoteConfig.fetchingStatus.observe(this) {
            when (it) {
                RemoteConfig.FetchStatus.SUCCESSFUL -> {
                    val validatedUrl = RemoteConfig.url

                    if (validatedUrl.isNotEmpty()) {
                        SharedPreferencesUtil.saveLink(validatedUrl)
                    }

                    screenState.value = FirebaseResult.OpenWeb(validatedUrl)
                }
                RemoteConfig.FetchStatus.LOADING -> {
                    screenState.value = FirebaseResult.Loading
                }
                else -> {
                    if (isNetworkAvailable(this)) {
                        screenState.value = FirebaseResult.OpenDummyScreen
                    } else {
                        screenState.value = FirebaseResult.OpenInternetConnectionScreen
                    }
                    Log.d("FirebaseError", "onCreate: Error")
                }
            }
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    return if (this::webView.isInitialized && webView.canGoBack()) {
                        webView.goBack()
                        true
                    } else
                        false
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}
