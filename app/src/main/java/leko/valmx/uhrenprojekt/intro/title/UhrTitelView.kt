package leko.valmx.uhrenprojekt.intro.title

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import leko.valmx.uhrenprojekt.R

class UhrTitelView(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(
        context!!, attrs
    ) {

    lateinit var c: Canvas
    val text = context!!.obtainStyledAttributes(attrs, R.styleable.UhrTitelView)
        .getString(R.styleable.UhrTitelView_titel)

    val textArr = text!!.split(" ")

    var gridManager: GridManager? = null
    lateinit var paintManager: PaintManager
    lateinit var drawManager: DrawManager


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {


        if (gridManager == null) {
            gridManager = GridManager(this)
            paintManager = PaintManager(this)
            drawManager = DrawManager(this)

            gridManager!!.init()
            paintManager.init()
            drawManager.init()

            layoutParams.height = c.height

        }

    }

    fun getLengthOfLongestText(): Int {
        return 0
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}