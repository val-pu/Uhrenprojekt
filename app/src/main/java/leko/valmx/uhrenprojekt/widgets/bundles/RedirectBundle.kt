package leko.valmx.uhrenprojekt.widgets.bundles

import android.view.View

class RedirectBundle(
    val name: String,
    val description: String = "",
    val listener: View.OnClickListener
) : ContentBundle()