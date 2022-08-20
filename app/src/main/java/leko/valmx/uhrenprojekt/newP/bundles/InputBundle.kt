package leko.valmx.uhrenprojekt.newP.bundles

import com.google.android.material.textfield.TextInputEditText

class InputBundle(val title: String, val description: String = "", val listener: OnInputSendListener): ContentBundle() {
}


interface OnInputSendListener {

    fun onInput(text: String)

}
