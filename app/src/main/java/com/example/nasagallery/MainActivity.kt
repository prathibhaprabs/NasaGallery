package com.example.nasagallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasagallery.utilities.Utils
import com.example.nasagallery.viewmodel.NasaImagesViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var isOnline = true
    private val nasaList: ArrayList<Nasa> = ArrayList()
    var rv: RecyclerView? = null
    private lateinit var mNasaImagesViewModel: NasaImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        if (isOnline) loadOnlineData() else loadOfflineData()
    }

    private fun initUi() {
        rv = findViewById(R.id.thumbnailsRv)
        mNasaImagesViewModel = ViewModelProvider(this)[NasaImagesViewModel::class.java]
        val mToolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        val layoutManager = GridLayoutManager(this, 2)
        rv?.layoutManager = layoutManager
        rv?.adapter = RvAdapter(this, nasaList)
        rv?.setItemViewCacheSize(20);
    }

    private fun loadOfflineData() {

        val json: String? = try {
            val stream: InputStream = assets.open("nasa_json")
            val size: Int = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            null
        }

        nasaList.addAll(
            Gson().fromJson<ArrayList<Nasa>>(
                json,
                object : TypeToken<List<Nasa>>() {}.type
            )
        )
        rv?.adapter?.notifyDataSetChanged()
    }

    private fun loadOnlineData() {
        mNasaImagesViewModel.getNasaImages()
        mNasaImagesViewModel.observeNasaImagesLiveData().observe(this, {
            nasaList.clear()
            nasaList.addAll(Utils.getNasaImages(it))
            rv?.adapter?.notifyDataSetChanged()
        })
    }
}