package com.example.animated_flip_view

import android.content.Context
import android.widget.Toast

object AnimatedFlipView {
    fun showToast(context: Context, message:String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}