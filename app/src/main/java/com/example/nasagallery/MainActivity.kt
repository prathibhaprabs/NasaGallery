package com.example.nasagallery

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasagallery.utilities.Utils
import com.example.nasagallery.viewmodel.NasaImagesViewModel

class MainActivity : AppCompatActivity() {
    private val nasaList: ArrayList<Nasa> = ArrayList()
    var rv: RecyclerView? = null
    var errorText: TextView? = null
    private lateinit var mNasaImagesViewModel: NasaImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        if (Utils.isNetworkAvailable(this)) loadOnlineData() else showDeviceOfflineMessage()
    }

    private fun initUi() {
        rv = findViewById(R.id.thumbnailsRv)
        errorText = findViewById(R.id.errorText)
        mNasaImagesViewModel = ViewModelProvider(this)[NasaImagesViewModel::class.java]
        val mToolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        val layoutManager = GridLayoutManager(this, 2)
        rv?.layoutManager = layoutManager
        rv?.adapter = RvAdapter(this, nasaList)
        rv?.setItemViewCacheSize(20);
    }

    private fun showDeviceOfflineMessage() {
        errorText?.visibility = View.VISIBLE
    }

    private fun loadOnlineData() {
        errorText?.visibility = View.GONE
        mNasaImagesViewModel.getNasaImages()
        mNasaImagesViewModel.observeNasaImagesLiveData().observe(this) {
            nasaList.clear()
            nasaList.addAll(Utils.getNasaImages(it))
            rv?.adapter?.notifyDataSetChanged()
        }
    }
}