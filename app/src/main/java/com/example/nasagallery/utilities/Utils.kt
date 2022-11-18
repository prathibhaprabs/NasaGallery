package com.example.nasagallery.utilities

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
    }
}