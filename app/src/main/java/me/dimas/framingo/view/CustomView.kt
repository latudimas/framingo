package me.dimas.framingo.view

import android.content.Context
import android.graphics.*
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.View
import me.dimas.framingo.R
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.net.URI

class CustomView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

//class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // Instance declaration
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var squareFrameColor = Color.BLACK
    private var size = 360

    private lateinit var bitmap: Bitmap

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawSquareFrame(canvas)
        drawBitmapImage(canvas)
    }

    /**
     * Get height and width properties for re-sizing the frame
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        size = Math.min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    /**
     * Drawing square frame in canvas, and set it on as background
     */
    private fun drawSquareFrame(canvas: Canvas?) {
        paint.color = squareFrameColor
        paint.style = Paint.Style.FILL

        val frameSize = size.toFloat()

        canvas?.drawRect(0f, 0f, frameSize, frameSize, paint)
    }

    /**
     * Drawing the image that user chosen into the canvas
     */
    private fun drawBitmapImage(canvas: Canvas?) {

//        (bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false)

            val bitmapWidth = bitmap.width
            val bitmapHeight = bitmap.height

            val targetWidth = (0.75 * bitmapWidth).toInt()
            val targetHeight = (0.75 * bitmapHeight).toInt()

            val resizedBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false)

            canvas?.drawBitmap(resizedBitmap, 100f, 100f, null)

    }

    /**
     * Get Bitmap image from activity result
     */
    fun setBitmap(selectedBitmap: Bitmap) {

        bitmap = selectedBitmap
        Timber.d("Bitmap Successfully Set")
    }
}