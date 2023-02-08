package com.example.dummysport.utils

sealed class FirebaseResult<out T> {
    data class OpenWeb<out T>(val data: T) : FirebaseResult<T>()
    object Loading : FirebaseResult<Nothing>()
    object OpenDummyScreen : FirebaseResult<Nothing>()
    object OpenInternetConnectionScreen : FirebaseResult<Nothing>()
}
