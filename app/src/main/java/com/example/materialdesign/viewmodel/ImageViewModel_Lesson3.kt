package com.example.materialdesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.BuildConfig
import com.example.materialdesign.domain.ImageServerResponseData
import com.example.materialdesign.domain.PODServerResponseData
import com.example.materialdesign.domain.PhotosDataResponse
import com.example.materialdesign.repository.NasaApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewModel_Lesson3(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val apiRetrofit: NasaApiRetrofit = NasaApiRetrofit()
) : ViewModel() {

    fun getData(planet: String): LiveData<AppState> {
        getPlanet(planet)
        return liveDataForViewToObserve
    }

    private fun getPlanet(planet: String) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            AppState.Error(Throwable("You need API key"))
        } else {
            when (planet) {
                "Mars" -> {
                    apiRetrofit.nasaApiRetrofit().getMars(apiKey).enqueue(object :
                        Callback<ArrayList<PhotosDataResponse>> {
                        override fun onResponse(
                            call: Call<ArrayList<PhotosDataResponse>>,
                            response: Response<ArrayList<PhotosDataResponse>>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                liveDataForViewToObserve.value =
                                    AppState.Success_Image(response.body()!!)
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
                            call: Call<ArrayList<PhotosDataResponse>>,
                            t: Throwable
                        ) {
                            liveDataForViewToObserve.value = AppState.Error(t)
                        }
                    })

                }
                /*
                "Venus" -> {
                    apiRetrofit.nasaApiRetrofit().getVenus(apiKey).enqueue(object :
                        Callback<ImageServerResponseData> {
                        override fun onResponse(
                            call: Call<ImageServerResponseData>,
                            response: Response<ImageServerResponseData>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                liveDataForViewToObserve.value =
                                    AppState.Success_Image(response.body()!!)
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

                        override fun onFailure(call: Call<ImageServerResponseData>, t: Throwable) {
                            liveDataForViewToObserve.value = AppState.Error(t)
                        }
                    })
                }
                "Earth" -> {
                    apiRetrofit.nasaApiRetrofit().getEarth(apiKey).enqueue(object :
                        Callback<ImageServerResponseData> {
                        override fun onResponse(
                            call: Call<ImageServerResponseData>,
                            response: Response<ImageServerResponseData>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                liveDataForViewToObserve.value =
                                    AppState.Success_Image(response.body()!!)
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

                        override fun onFailure(call: Call<ImageServerResponseData>, t: Throwable) {
                            liveDataForViewToObserve.value = AppState.Error(t)
                        }
                    })
                }

                 */
            }
        }
    }
}