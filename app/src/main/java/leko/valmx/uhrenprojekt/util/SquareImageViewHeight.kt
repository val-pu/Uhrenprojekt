package leko.valmx.uhrenprojekt.util

import android.content.Context
import android.util.AttributeSet

class SquareImageViewHeight(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }

}