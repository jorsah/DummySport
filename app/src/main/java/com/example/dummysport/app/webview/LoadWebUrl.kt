package com.example.dummysport.app

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LoadWebUrl(url: String, context: Context, initializeWebView: (WebView) -> Unit) {
    AndroidView(factory = {
        WebView(context).apply {
            initializeWebView(this)
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    if (view?.contentHeight == 0) {
                        view.reload()
                    } else {
                        super.onPageFinished(view, url)
                    }
                }

            }

            settings.domStorageEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.setSupportZoom(false)
            settings.allowFileAccess = true
            settings.allowContentAccess = true

            loadUrl(url)
        }
    })
}
