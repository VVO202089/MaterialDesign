package com.example.materialdesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.BuildConfig
import com.example.materialdesign.domain.PhotosDataResponse
import com.example.materialdesign.repository.NasaApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewModel_Lesson3(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val apiRetrofit: NasaApiRetrofit = NasaApiRetrofit()
) : ViewModel() {

    fun getData(dateString: String): LiveData<AppState> {
        getPlanet(dateString)
        return liveDataForViewToObserve
    }

    private fun getPlanet(dateString: String) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            AppState.Error(Throwable("You need API key"))
        } else {
            apiRetrofit.nasaApiRetrofit().getMars(dateString,apiKey).enqueue(object :
                Callback<PhotosDataResponse> {
                override fun onResponse(
                    call: Call<PhotosDataResponse>,
                    response: Response<PhotosDataResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            AppState.Success_Image_Mars(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                AppState.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                AppState.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(
                    call: Call<PhotosDataResponse>,
                    t: Throwable
                ) {
                    liveDataForViewToObserve.value = AppState.Error(t)
                }
            })
        }

    }
}