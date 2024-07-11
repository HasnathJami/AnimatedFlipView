package com.example.animatedflipviewhost

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.animated_flip_view.AnimatedFlipView

class AnimatedFlipViewHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_flip_view_host)

//        AnimatedFlipView.showToast(this@AnimatedFlipViewHostActivity, "Test AFV")
        val animatedFlipView = findViewById<AnimatedFlipView>(R.id.myEasyFlipView)

//        animatedFlipView.flip()
        animatedFlipView.flipToFront()
//        animatedFlipView.findViewById<LinearLayout>(R.id.frontViewContent).setOnClickListener {
//            animatedFlipView.flipToBack()
//           // animatedFlipView.flip()
//        }

//// Flip to back
//        animatedFlipView.flipToBack()
//
//// Flip to front
//        animatedFlipView.flipToFront()

    }
}