package leko.valmx.uhrenprojekt.bundles

class InputBundle(val title: String, val description: String = "", val listener: OnInputSendListener): ContentBundle() {
}


interface OnInputSendListener {

    fun onInput(text: String)

}
