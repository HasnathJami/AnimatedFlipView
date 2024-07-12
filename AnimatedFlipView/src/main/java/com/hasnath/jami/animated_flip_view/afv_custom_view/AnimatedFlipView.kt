/*
 * Copyright 2024 Hasnath Jami Chowdhury
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hasnath.jami.animated_flip_view.afv_custom_view

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
import com.hasnath.jami.animated_flip_view.R
import com.hasnath.jami.animated_flip_view.afv_utils.FlipDirection

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
        try {
            val frontAnim = getAnimatorSet(flipDirection, true)
            val backAnim = getAnimatorSet(flipDirection, false)

            frontAnim.setTarget(backView)
            backAnim.setTarget(frontView)
            frontAnim.start()
            backAnim.start()

            frontView.visibility = View.VISIBLE
            backView.visibility = View.GONE
            isFlipped = false
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("ResourceType")
    fun flipToBack() {
        try {
            val frontAnim = getAnimatorSet(flipDirection, true)
            val backAnim = getAnimatorSet(flipDirection, false)

            backAnim.setTarget(backView)
            frontAnim.start()
            backAnim.start()

            backView.visibility = View.VISIBLE
            frontView.visibility = View.GONE
            isFlipped = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
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
        try {
            frontView = view
            findViewById<ViewGroup>(R.id.frontView).addView(view)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setBackView(view: View) {
        try {
            backView = view
            findViewById<ViewGroup>(R.id.backView).addView(view)
            backView.visibility = View.GONE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun startAutoBackFlippingWithIntervals(delay: Long) {
        try {
            flipToBack()
            handler.postDelayed({
                flipToFront()
            }, delay)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun removeHandler() {
        try {
            handler.removeCallbacksAndMessages(null)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
