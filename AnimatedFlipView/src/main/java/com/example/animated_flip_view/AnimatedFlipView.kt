package com.example.animated_flip_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.widget.RelativeLayout

class AnimatedFlipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var frontView: LinearLayout
    private var backView: LinearLayout
    private var isFlipped = false
    private var flipDuration: Long = 400

    init {
        LayoutInflater.from(context).inflate(R.layout.animated_flip_view, this, true)
        frontView = findViewById(R.id.frontView)
        backView = findViewById(R.id.backView)
        backView.visibility = View.GONE

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AnimatedFlipView,
            0, 0).apply {

            try {
                flipDuration = getInt(R.styleable.AnimatedFlipView_flipDuration, 400).toLong()
            } finally {
                recycle()
            }
        }

        setOnClickListener { flip() }
    }
    fun flip() {
        if (isFlipped) {
            flipToFront()
        } else {
            flipToBack()
        }
        isFlipped = !isFlipped
    }


    fun flipToFront() {
//        if (isFlipped) {
            val frontAnim = AnimatorInflater.loadAnimator(context, R.anim.front_animator) as AnimatorSet
            val backAnim = AnimatorInflater.loadAnimator(context, R.anim.back_animator) as AnimatorSet

            frontAnim.setTarget(backView)
            backAnim.setTarget(frontView)
            frontAnim.start()
            backAnim.start()

            frontView.visibility = View.VISIBLE
            backView.visibility = View.GONE
            isFlipped = false
//        }
    }


    fun flipToBack() {
//        if (!isFlipped) {
            val frontAnim = AnimatorInflater.loadAnimator(context, R.anim.front_animator) as AnimatorSet
            val backAnim = AnimatorInflater.loadAnimator(context, R.anim.back_animator) as AnimatorSet

            frontAnim.setTarget(frontView.addView(R.layout.animated_flip_view))
            backAnim.setTarget(backView)
            frontAnim.start()
            backAnim.start()

            frontView.visibility = View.GONE
            backView.visibility = View.VISIBLE
            isFlipped = true
//        }
    }
}
