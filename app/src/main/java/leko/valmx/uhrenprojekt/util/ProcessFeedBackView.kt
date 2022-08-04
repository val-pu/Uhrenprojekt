package leko.valmx.uhrenprojekt.util

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import leko.valmx.uhrenprojekt.R
import kotlin.math.roundToInt

class ProcessFeedBackView(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {
    private var bitmap: Bitmap? = null
    private var canvas: Canvas = Canvas()

    private var erasePaint = Paint()
    private var feedBackPaint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            this.canvas.setBitmap(bitmap)

            erasePaint.color = resources.getColor(R.color.erase)
            feedBackPaint.color = resources.getColor(R.color.feedback)

            erasePaint.blendMode = BlendMode.CLEAR

            background = BitmapDrawable(bitmap)
        }

    }

    private val rippleDuration = 800L

    fun startFeedBack(duration: Int) {


        val va = ValueAnimator.ofFloat(0F, (duration / rippleDuration).toFloat())
        va.duration = duration.toLong()
        va.interpolator = LinearInterpolator()

        va.addUpdateListener {

            val animatedValue = it.animatedValue as Float % 1

            canvas.drawPaint(erasePaint)

            feedBackPaint.alpha = (255 * (1 - animatedValue)).roundToInt()

            canvas.drawCircle(width / 2F, height / 2F, width / 2F * animatedValue, feedBackPaint)

            invalidate()

        }

        va.start()


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

}