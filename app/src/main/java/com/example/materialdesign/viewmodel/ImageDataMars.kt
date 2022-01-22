package com.example.materialdesign.viewmodel

sealed class ImageDataMars {

    data class Error(val error: Throwable) : ImageDataMars()
    data class Loading(val progress: Int?) : ImageDataMars()
    data class Success(val url: String) : ImageDataMars()

}
