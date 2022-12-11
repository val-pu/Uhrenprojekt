package leko.valmx.uhrenprojekt.widgets.bundles

import android.view.View

class CustomBundle(val layout: Int, val logic: View.() -> Unit) : ContentBundle() {}