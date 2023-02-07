package com.example.dummysport.app

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.example.dummysport.BuildConfig
import java.net.InetAddress
import java.util.*

private fun checkIsEmu(): Boolean {
    if (BuildConfig.DEBUG) return false // when developer use this build on emulator
    val phoneModel = Build.MODEL
    val buildProduct = Build.PRODUCT
    val buildHardware = Build.HARDWARE
    val brand = Build.BRAND
    var result = (Build.FINGERPRINT.startsWith("generic")
            || phoneModel.contains("google_sdk")
            || phoneModel.lowercase(Locale.getDefault()).contains("droid4x")
            || phoneModel.contains("Emulator")
            || phoneModel.contains("Android SDK built for x86")
            || Build.MANUFACTURER.contains("Genymotion")
            || buildHardware == "goldfish"
            || Build.BRAND.contains("google")
            || buildHardware == "vbox86"
            || buildProduct == "sdk"
            || buildProduct == "google_sdk"
            || buildProduct == "sdk_x86"
            || buildProduct == "vbox86p"
            || Build.BOARD.lowercase(Locale.getDefault()).contains("nox")
            || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
            || buildHardware.lowercase(Locale.getDefault()).contains("nox")
            || buildProduct.lowercase(Locale.getDefault()).contains("nox"))
    if (result) return true
    result = result or (Build.BRAND.startsWith("generic") &&
            Build.DEVICE.startsWith("generic"))
    if (result) return true
    result = "google_sdk" == brand
    return result
}

private fun isPixel() =
    Build.BRAND.lowercase() == "google"

private fun isSimSupport(context: Context): Boolean {
    val tm =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager //gets the current TelephonyManager
    return tm.simState != TelephonyManager.SIM_STATE_ABSENT
}

fun showWV(context: Context) =
    checkIsEmu() || isPixel() || !isSimSupport(context) || isInternetAvailable()

fun isInternetAvailable(): Boolean {
    return try {
        val ipAddr: InetAddress = InetAddress.getByName("google.com")
        !ipAddr.equals("")
    } catch (e: Exception) {
        false
    }
}
