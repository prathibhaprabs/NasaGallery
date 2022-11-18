package com.example.nasagallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasagallery.repo.NasaImagesResponseObj
import com.example.nasagallery.repo.RetrofitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class NasaImagesViewModel : ViewModel() {

    private var getNasaImagesLiveData = MutableLiveData<List<NasaImagesResponseObj>>()

    fun getNasaImages() {
        RetrofitCall.getData().enqueue(object : Callback<List<NasaImagesResponseObj>> {
            override fun onResponse(
                call: Call<List<NasaImagesResponseObj>>,
                response: Response<List<NasaImagesResponseObj>>
            ) {
                when {
                    null != response.body() -> {
                        when {
                            response.code() >= HttpURLConnection.HTTP_OK -> {
                                when {
                                    null != response.body() -> getNasaImagesLiveData.value = response.body()!!
                                    else -> getNasaImagesLiveData.value = arrayListOf()
                                }
                            }
                            else ->  getNasaImagesLiveData.value = arrayListOf()
                        }
                    }
                    else ->  getNasaImagesLiveData.value = arrayListOf()
                }
            }

            override fun onFailure(call: Call<List<NasaImagesResponseObj>>, t: Throwable) {
                getNasaImagesLiveData.value = arrayListOf()
            }
        })
    }

    fun observeNasaImagesLiveData() : LiveData<List<NasaImagesResponseObj>> {
        return getNasaImagesLiveData
    }
}