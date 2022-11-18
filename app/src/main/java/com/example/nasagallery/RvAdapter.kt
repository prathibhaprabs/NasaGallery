package com.example.nasagallery

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RvAdapter(
    private var mContext: Context,
    private var nasaImagesList: ArrayList<Nasa>
) : RecyclerView.Adapter<RvAdapter.RvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val inflater = LayoutInflater.from(mContext)
            .inflate(R.layout.nasa_image, parent, false) as ViewGroup
        return RvViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val size: Int = mContext.resources.displayMetrics.ydpi.toInt()

        Picasso.get().load(nasaImagesList[position].hdurl)
            .resize(size, size).centerCrop().into(holder.image, object : Callback {
                override fun onSuccess() {
                    holder.progress.visibility = GONE
                }

                override fun onError(e: Exception?) {
                    holder.progress.visibility = GONE
                    Picasso.get().load(android.R.drawable.ic_delete).into(holder.image)
                }
            })

        holder.image.setOnClickListener {
            val intent = Intent(mContext, ImageDetailsActivity::class.java)
            intent.putExtra("nasaImages", nasaImagesList)
            intent.putExtra("imagePosition", position)
            mContext.startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(mContext as Activity).toBundle())
        }
    }

    override fun getItemCount(): Int {
        return nasaImagesList.size
    }

    class RvViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var image: ImageView = v.findViewById(R.id.image)
        var progress: ProgressBar = v.findViewById(R.id.progress)
    }
}