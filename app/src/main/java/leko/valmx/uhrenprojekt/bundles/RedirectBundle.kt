package leko.valmx.uhrenprojekt.bundles

import android.view.View

class RedirectBundle(
    val name: String,
    val description: String = "",
    val listener: View.OnClickListener
) : ContentBundle()