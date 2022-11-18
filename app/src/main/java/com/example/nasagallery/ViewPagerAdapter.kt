package com.example.nasagallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import java.util.*

class ViewPagerAdapter(
    private var mContext: Context,
    private var nasaImagesList: List<Nasa>
) :
    PagerAdapter() {
    override fun getCount(): Int {
        return nasaImagesList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = mLayoutInflater.inflate(R.layout.view_pager_item, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.fullImage)
        val textView: TextView = itemView.findViewById(R.id.text)

        Picasso.get().load(nasaImagesList[position].hdurl).into(imageView)
        textView.text = nasaImagesList[position].title

        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}