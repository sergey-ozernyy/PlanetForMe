package com.example.planetforme.network

import android.R
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class AvatarLoader {
    fun avatarDownload(imageView: ImageView?, url: String?, isBigImage: Boolean) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_btn_speak_now)
            .error(R.drawable.ic_btn_speak_now)
            .into(imageView, object : Callback {
                override fun onSuccess() {}
                override fun onError(e: Exception) {
                    e.printStackTrace()
                }
            })
    }
}