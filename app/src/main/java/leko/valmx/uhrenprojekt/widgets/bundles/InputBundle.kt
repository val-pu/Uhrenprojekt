package leko.valmx.uhrenprojekt.widgets.bundles

class InputBundle(val title: String, val description: String = "", val listener: OnInputSendListener): ContentBundle() {
}


interface OnInputSendListener {

    fun onInput(text: String)

}
