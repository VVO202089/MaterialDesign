package com.example.materialdesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.BuildConfig
import com.example.materialdesign.domain.PODServerResponseData
import com.example.materialdesign.repository.NasaApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EveryDayImageViewModel(
    private val liveDataForViewToObserve: MutableLiveData<ImageData> = MutableLiveData(),
    private val apiRetrofit: NasaApiRetrofit = NasaApiRetrofit()
) : ViewModel() {

    fun getData(): LiveData<ImageData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = ImageData.Loading(null)
        //val apiKey: String = BuildConfig.NASA_API_KEY
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            ImageData.Error(Throwable("You need API key"))
        } else {
            apiRetrofit.nasaApiRetrofit().getImage(apiKey).enqueue(object :
                Callback<PODServerResponseData> {
                override fun onResponse(
                    call: Call<PODServerResponseData>,
                    response: Response<PODServerResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            ImageData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                ImageData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                ImageData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    liveDataForViewToObserve.value = ImageData.Error(t)
                }
            })
        }
    }
}