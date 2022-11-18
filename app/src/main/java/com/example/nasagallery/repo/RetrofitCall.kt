package com.example.nasagallery.repo

import com.example.nasagallery.utilities.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCall{

    companion object {
        private var retrofitCall: RetrofitCall? = null

        fun getInstance(): RetrofitCall {
            synchronized(RetrofitCall::class.java) {
                if (retrofitCall == null)
                    retrofitCall = RetrofitCall()
            }
            return retrofitCall!!
        }

        fun getData(): Call<List<NasaImagesResponseObj>> {
            val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiCallInterface: ApiCallInterface = retrofit.create(ApiCallInterface::class.java)

            val nasaList: Call<List<NasaImagesResponseObj>> = apiCallInterface.getNasaImagesList()
            return nasaList
        }
    }
}