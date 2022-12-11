package leko.valmx.uhrenprojekt.widgets.bundles

import androidx.recyclerview.widget.RecyclerView

class RecyclerBundle(
    val title: String,
    val adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
    val description: String,
    val layoutManager: RecyclerView.LayoutManager
) : ContentBundle()