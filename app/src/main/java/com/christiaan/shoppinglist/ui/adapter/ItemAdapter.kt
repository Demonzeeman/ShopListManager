package com.christiaan.shoppinglist.ui.itemlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.christiaan.shoppinglist.R
import com.christiaan.shoppinglist.data.Item

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.textViewItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name

        // Set strikethrough if the item is marked as done
        holder.itemName.paint.isStrikeThruText = item.isDone

        // Handle item click
        holder.itemName.setOnClickListener {
            item.isDone = !item.isDone // Toggle done status
            notifyItemChanged(position) // Refresh the item view
        }
    }

    override fun getItemCount() = items.size
}
