package com.itunes.ituneswiki.utils

import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itunes.ituneswiki.R

@RequiresApi(Build.VERSION_CODES.M)
fun getProgressDrawable(context: Context): CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        centerRadius = 50f
        strokeWidth = 10f
        setColorSchemeColors(context.getColor(R.color.teal_200))
        start()
    }
}

fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_music)
    Glide.with(this)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}