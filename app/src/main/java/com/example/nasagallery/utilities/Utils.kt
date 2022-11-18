package com.example.nasagallery.utilities

import android.content.Context
import android.net.ConnectivityManager
import com.example.nasagallery.Nasa
import com.example.nasagallery.repo.NasaImagesResponseObj

class Utils {
    companion object {

        fun getNasaImages(list: List<NasaImagesResponseObj>): List<Nasa> {
            val nasaImagesList: ArrayList<Nasa> = arrayListOf()
            var nasa: Nasa?
            list.forEach {
                nasa = Nasa(
                    it.copyright,
                    it.date,
                    it.explanation,
                    it.hdurl,
                    it.media_type,
                    it.service_version,
                    it.title,
                    it.url
                )

                nasaImagesList.add(nasa!!)
            }

            return nasaImagesList
        }

        fun isNetworkAvailable(mContext: Context) : Boolean {
            val cm : ConnectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            return info != null && info.isConnected
        }
    }
}