package leko.valmx.uhrenprojekt.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class SquareImageView(context: Context?, attrs: AttributeSet?) :
    View(context!!, attrs) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

}