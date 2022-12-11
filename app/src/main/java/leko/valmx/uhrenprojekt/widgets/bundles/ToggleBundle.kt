package leko.valmx.uhrenprojekt.widgets.bundles

class ToggleBundle(
    val title: String,
    val desc: String,
    val condition: () -> Boolean,
    val onToggle: Boolean.() -> Unit
) : ContentBundle(){
}