package calc.win.betboom.sharedprefs

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtil {
    private const val KEY = "link"
    lateinit var sharedPreferences: SharedPreferences

    private val edit: SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    fun loadSharedPrefs(context: Context) {
        sharedPreferences = context.getSharedPreferences("LocalShP", Context.MODE_PRIVATE)
    }

    fun saveLink(url: String) {
        edit.putString(KEY, url)
        edit.apply()
    }

    fun getLink(): String? = sharedPreferences.getString(KEY, null)

}