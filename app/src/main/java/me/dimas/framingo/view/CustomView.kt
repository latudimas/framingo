package me.dimas.framingo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.Button

class CustomView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    val rectFrame1: Rect = Rect()
    val rectColor1: Paint = Paint()
    val rectFrame2: Rect = Rect()
    val rectColor2: Paint = Paint()

    private fun initialize() {
        rectFrame1.apply {
            top = 100
            left = 100
            right = left + 100
            bottom = top + 100
        }

        rectColor1.apply {
            color = Color.RED
        }

        rectFrame2.apply {
            left = 100
            top = 100
            right = left + 50
            bottom = top + 50
        }

        rectColor2.apply {
            color = Color.BLUE
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        initialize()
        canvas?.drawColor(Color.LTGRAY)
        canvas?.drawRect(rectFrame1, rectColor1)
        canvas?.drawRect(rectFrame2, rectColor2)

    }

}