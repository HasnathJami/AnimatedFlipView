package com.example.animated_flip_view.afv_custom_view

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.animated_flip_view.R
import com.example.animated_flip_view.afv_utils.FlipDirection

class AnimatedFlipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var frontView: View
    private var backView: View
    private var isFlipped = false
    private var frontFlipDuration: Long = 0
    private var backFlipDuration: Long = 0
    private var flipDirection = FlipDirection.TOP_TO_DOWN
    private val handler = Handler(Looper.getMainLooper())

    init {
        LayoutInflater.from(context).inflate(R.layout.animated_flip_view, this, true)
        frontView = findViewById(R.id.frontView)
        backView = findViewById(R.id.backView)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AnimatedFlipView,
            0, 0
        ).apply {
            try {
                frontFlipDuration =
                    getInt(R.styleable.AnimatedFlipView_frontFlipDuration, 400).toLong()
                backFlipDuration =
                    getInt(R.styleable.AnimatedFlipView_backFlipDuration, 400).toLong()
                val direction = getInt(R.styleable.AnimatedFlipView_flipDirection, 0)
                flipDirection = FlipDirection.values()[direction]
            } finally {
                recycle()
            }
        }
    }

    fun flip() {
        if (isFlipped) {
            flipToFront()
        } else {
            flipToBack()
        }
        isFlipped = !isFlipped
    }

    @SuppressLint("ResourceType")
    fun flipToFront() {
        val frontAnim = getAnimatorSet(flipDirection, true)
        val backAnim = getAnimatorSet(flipDirection, false)

        frontAnim.setTarget(backView)
        backAnim.setTarget(frontView)
        frontAnim.start()
        backAnim.start()

        frontView.visibility = View.VISIBLE
        backView.visibility = View.GONE
        isFlipped = false
    }

    @SuppressLint("ResourceType")
    fun flipToBack() {
        val frontAnim = getAnimatorSet(flipDirection, true)
        val backAnim = getAnimatorSet(flipDirection, false)

        backAnim.setTarget(backView)
        frontAnim.start()
        backAnim.start()

        frontView.visibility = View.GONE
        backView.visibility = View.VISIBLE
        isFlipped = true
    }

    private fun getAnimatorSet(direction: FlipDirection, isFront: Boolean): AnimatorSet {
        val animRes = when (direction) {
            FlipDirection.LEFT_TO_RIGHT -> if (isFront) R.animator.left_to_right_front else R.animator.left_to_right_back
            FlipDirection.RIGHT_TO_LEFT -> if (isFront) R.animator.right_to_left_front else R.animator.right_to_left_back
            FlipDirection.TOP_TO_DOWN -> if (isFront) R.animator.top_to_down_front else R.animator.top_to_down_back
            FlipDirection.DOWN_TO_TOP -> if (isFront) R.animator.down_to_top_front else R.animator.down_to_top_back
        }
        return AnimatorInflater.loadAnimator(context, animRes) as AnimatorSet
    }

    fun setFrontView(view: View) {
        frontView = view
        findViewById<ViewGroup>(R.id.frontView).addView(view)
    }

    fun setBackView(view: View) {
        backView = view
        findViewById<ViewGroup>(R.id.backView).addView(view)
    }

    fun startAutoBackFlippingWithIntervals(delay: Long) {
        handler.postDelayed({
            flipToBack()
            handler.postDelayed({
                flipToFront()
            }, delay)
        }, delay)
    }

    fun removeHandler() {
        handler.removeCallbacksAndMessages(null)
    }
}
