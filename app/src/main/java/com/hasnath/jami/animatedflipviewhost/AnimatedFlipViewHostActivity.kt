package com.hasnath.jami.animatedflipviewhost

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.hasnath.jami.animated_flip_view.afv_custom_view.AnimatedFlipView

class AnimatedFlipViewHostActivity : AppCompatActivity() {

    private lateinit var flipView: AnimatedFlipView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_flip_view_host)

        flipView = findViewById(R.id.animatedFlipView)

        //set your front layout
        val frontView = layoutInflater.inflate(R.layout.flash_card_layout_front, null)
        //set your back layout
        val backView = layoutInflater.inflate(R.layout.flash_card_layout_back, null)


        //add front layout to parent view
        flipView.setFrontView(frontView)
        //add back layout to parent view
        flipView.setBackView(backView)

//        flipView.startAutoFlippingWithIntervals(3000, 3000)


        /* control flip with view clicking*/
//        frontView.findViewById<ImageView>(R.id.dropIcon).setOnClickListener {
//            flipView.flipToBack()
//        }
//
//        backView.findViewById<RelativeLayout>(R.id.rl_root_layout_back).setOnClickListener {
//            flipView.flipToFront()
//        }

        /* handle auto back flipping */
        frontView.findViewById<ImageView>(R.id.dropIcon).setOnClickListener {
            flipView.startAutoBackFlippingWithIntervals(delay = 2000)
        }
    }

    // release the handler when screen destroys
    override fun onDestroy() {
        super.onDestroy()
        flipView.removeHandler()

    }
}