package leko.valmx.uhrenprojekt.bundles

import androidx.recyclerview.widget.RecyclerView

class RecyclerBundle(
    val title: String,
    val adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
    val description: String,
    val layoutManager: RecyclerView.LayoutManager
) : ContentBundle()