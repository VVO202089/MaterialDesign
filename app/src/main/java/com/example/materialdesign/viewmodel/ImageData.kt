package com.example.materialdesign.viewmodel

import com.example.materialdesign.domain.PODServerResponseData

sealed class ImageData {
    data class Error(val error: Throwable) : ImageData()
    data class Loading(val progress: Int?) : ImageData()
    data class Success(val serverResponseData: PODServerResponseData) : ImageData()
}