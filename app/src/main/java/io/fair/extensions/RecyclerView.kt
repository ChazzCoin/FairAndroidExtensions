package io.fair.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * val adapter = FairRecyclerViewAdapter(list, R.layout.list_item, ::MyViewHolder)
        recyclerView.adapter = adapter
 */

class FairRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(
    private val items: List<T>, private val layoutId: Int,
    private val viewHolder:(view: View) -> VH) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        // bind the item to the viewholder
    }

    override fun getItemCount(): Int {
        return items.size
    }
}