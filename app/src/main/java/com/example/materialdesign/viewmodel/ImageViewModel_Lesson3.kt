package com.example.materialdesign.viewmodel

import android.icu.util.Calendar
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
    private val liveDataForViewToObserve: MutableLiveData<ImageDataMars> = MutableLiveData(),
    private val apiRetrofit: NasaApiRetrofit = NasaApiRetrofit(),
    private val calendar: Calendar = Calendar.getInstance()
) : ViewModel() {

    fun getData(indexMonth: Int): LiveData<ImageDataMars> {
        calendar.add(
            Calendar.MONTH,
            indexMonth
        ) // смещаем переданную дату на на месяц в зависимости от того, какая вкладка выбрана
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)!! + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dateString = "${year}-${month}-${day}"
        getPlanet(dateString)
        return liveDataForViewToObserve
    }

    private fun getPlanet(dateString: String) {

        liveDataForViewToObserve.value = ImageDataMars.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY

        if (apiKey.isBlank()) {
            ImageDataMars.Error(Throwable("You need API key"))
        } else {
            parseImage(dateString, apiKey)
        }

    }

    private fun parseImage(dateString: String, apiKey: String) {
        apiRetrofit.nasaApiRetrofit().getMars(dateString, apiKey).enqueue(object :
            Callback<PhotosDataResponse> {
            override fun onResponse(
                call: Call<PhotosDataResponse>,
                response: Response<PhotosDataResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val arrPhotos = response.body()!!.photos
                    if (arrPhotos.size > 0) {
                        val photos = arrPhotos.get(0)
                        val url = photos.imgSrc
                        if (url.isNullOrEmpty()) {
                            ImageDataMars.Error(Throwable("Пустая ссылка!"))
                        } else {
                            liveDataForViewToObserve.value =
                                ImageDataMars.Success(url)
                        }

                    } else {
                        ImageDataMars.Error(Throwable("Массив с фото пустой!"))
                    }

                } else {
                    val message = response.message()
                    if (message.isNullOrEmpty()) {
                        liveDataForViewToObserve.value =
                            ImageDataMars.Error(Throwable("Unidentified error"))
                    } else {
                        liveDataForViewToObserve.value =
                            ImageDataMars.Error(Throwable(message))
                    }
                }
            }

            override fun onFailure(
                call: Call<PhotosDataResponse>,
                t: Throwable
            ) {
                liveDataForViewToObserve.value = ImageDataMars.Error(t)
            }
        })
    }
}