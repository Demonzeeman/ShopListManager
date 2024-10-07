package com.christiaan.shoppinglist.ui.previouslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.christiaan.shoppinglist.R
import com.christiaan.shoppinglist.data.ShoppingList

class ShoppingListAdapter(
    private val shoppingLists: List<ShoppingList>,
    private val clickListener: (ShoppingList) -> Unit // Ensure this line is included
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    // ViewHolder for the RecyclerView
    class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text_shopping_list_name) // Adjust this ID to your layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping_list, parent, false) // Inflate your item layout
        return ShoppingListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val shoppingList = shoppingLists[position]
        holder.nameTextView.text = shoppingList.name // Assuming your ShoppingList has a 'name' property

        // Set click listener
        holder.itemView.setOnClickListener {
            clickListener(shoppingList) // Invoke the click listener with the selected shopping list
        }
    }

    override fun getItemCount(): Int {
        return shoppingLists.size // Return the number of shopping lists
    }
}
