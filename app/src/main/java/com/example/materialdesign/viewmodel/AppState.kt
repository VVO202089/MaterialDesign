package com.example.materialdesign.viewmodel

import com.example.materialdesign.domain.PODServerResponseData
import com.example.materialdesign.domain.PhotosDataResponse
import com.google.gson.JsonPrimitive

sealed class AppState {

    data class Success(val serverResponseData: PODServerResponseData):AppState()
    data class Success_Image_Mars(val serverResponseData: PhotosDataResponse):AppState()
    data class Success_Image_Earth(val serverResponseData: JsonPrimitive):AppState()
    data class Error(val error: Throwable):AppState()
    data class Loading(val progress: Int?):AppState()
}