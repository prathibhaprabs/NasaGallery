package com.example.nasagallery.repo

import com.example.nasagallery.utilities.Constants
import retrofit2.Call
import retrofit2.http.GET

interface ApiCallInterface {
    @GET(Constants.URL)
    fun getNasaImagesList(): Call<List<NasaImagesResponseObj>>

}