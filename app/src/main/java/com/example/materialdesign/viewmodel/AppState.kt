package com.example.materialdesign.viewmodel

import com.example.materialdesign.domain.ImageServerResponseData
import com.example.materialdesign.domain.PODServerResponseData
import com.example.materialdesign.domain.PhotosDataResponse

sealed class AppState {

    data class Success(val serverResponseData: PODServerResponseData):AppState()
    data class Success_Image(val serverResponseData: ArrayList<PhotosDataResponse>):AppState()
    data class Error(val error: Throwable):AppState()
    data class Loading(val progress: Int?):AppState()
}