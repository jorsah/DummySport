package calc.win.betboom.remoteconfig

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import calc.win.betboom.sharedprefs.SharedPreferencesUtil
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfig {

    private val _fetchingStatus = MutableLiveData(FetchStatus.LOADING)

    val fetchingStatus: LiveData<FetchStatus>
        get() = _fetchingStatus

    enum class FetchStatus { SUCCESSFUL, LOADING, ERROR }

    val url: String
        get() {
            return if (SharedPreferencesUtil.getLink() != null) {
                Log.d("FirebaseError", "Shared prefs value")
                SharedPreferencesUtil.getLink()!!
            } else {
                Log.d("FirebaseError", "FB prefs value")

                Firebase.remoteConfig.getString(RemoteConfigConstants.URL)
            }
        }

    fun loadSettings() {
        _fetchingStatus.value = FetchStatus.LOADING

        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }

        Firebase.remoteConfig.apply {
            setDefaultsAsync(RemoteConfigConstants.defaults)
            setConfigSettingsAsync(settings)
        }.fetchAndActivate()
            .addOnSuccessListener {
                _fetchingStatus.postValue(FetchStatus.SUCCESSFUL)
            }.addOnFailureListener {
                _fetchingStatus.postValue(FetchStatus.ERROR)
            }
    }
}
