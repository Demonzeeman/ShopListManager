package com.christiaan.shoppinglist.ui.previouslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.christiaan.shoppinglist.R
import com.christiaan.shoppinglist.data.ShoppingList

class ShoppingListAdapter(private val shoppingList: List<ShoppingList>) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Update this line to match the ID in your layout file
        val textViewItemName: TextView = itemView.findViewById(R.id.textViewItemName) // Change from textViewListName to textViewItemName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_list, parent, false)
        return ShoppingListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val currentItem = shoppingList[position]
        // Ensure you are using the correct property from your ShoppingList class
        holder.textViewItemName.text = currentItem.name // Change this to use the correct property if necessary
    }

    override fun getItemCount() = shoppingList.size
}
