package leko.valmx.uhrenprojekt.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_b_sheet.*
import leko.valmx.uhrenprojekt.R

abstract class BeautifulBottomSheet : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        content.addView(layoutInflater.inflate(getLayout(),null).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT,WRAP_CONTENT)
        })

        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun getLayout(): Int


}