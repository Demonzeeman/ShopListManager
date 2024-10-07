package com.christiaan.shoppinglist.ui.newitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.christiaan.shoppinglist.R
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.christiaan.shoppinglist.data.Item
import com.christiaan.shoppinglist.data.ItemDatabase
import com.christiaan.shoppinglist.data.ItemRepository
import com.christiaan.shoppinglist.ui.ItemViewModel
import com.christiaan.shoppinglist.ui.ItemViewModelFactory

class NewItemsFragment : Fragment() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var editTextItem: EditText
    private lateinit var buttonAddItem: Button
    private lateinit var listViewItems: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private var currentItem: Item? = null
    private var shoppingListId: Int = 0 // Initialize a variable for shoppingListId

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextItem = view.findViewById(R.id.editTextItem)
        buttonAddItem = view.findViewById(R.id.buttonAddItem)
        listViewItems = view.findViewById(R.id.listViewItems)

        // Initialize the repository and ViewModel
        val itemDatabase = ItemDatabase.getDatabase(requireContext())
        val repository = ItemRepository(itemDatabase.itemDao())
        itemViewModel = ViewModelProvider(this, ItemViewModelFactory(repository)).get(ItemViewModel::class.java)

        // Setup the ListView with an ArrayAdapter
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mutableListOf())
        listViewItems.adapter = adapter

        // Observe the LiveData from the ViewModel
        itemViewModel.items.observe(viewLifecycleOwner, Observer { items ->
            // Clear the adapter and add the new items to the list
            adapter.clear()
            adapter.addAll(items.map { it.name }) // Assuming `name` is a property of Item
        })

        // Handle the button click to add or edit an item
        buttonAddItem.setOnClickListener {
            val itemName = editTextItem.text.toString()
            if (itemName.isNotEmpty()) {
                if (currentItem == null) {
                    // Create a new Item
                    val newItem = Item(id = 0, name = itemName, shoppingListId = shoppingListId) // Room will assign a real ID
                    itemViewModel.insertItem(newItem)
                } else {
                    // Update existing item
                    val updatedItem = currentItem!!.copy(name = itemName)
                    itemViewModel.insertItem(updatedItem) // Reinsert to update
                    currentItem = null // Clear current item after editing
                }
                editTextItem.text.clear() // Clear the input field after adding or editing
            }
        }

        // Handle item click to edit or delete
        listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = itemViewModel.items.value?.get(position)
            selectedItem?.let {
                // Populate the EditText with the item's name for editing
                editTextItem.setText(it.name)
                currentItem = it // Set the current item for editing
            }
        }

        // Handle item long click to delete
        listViewItems.setOnItemLongClickListener { _, _, position, _ ->
            val selectedItem = itemViewModel.items.value?.get(position)
            selectedItem?.let {
                itemViewModel.deleteItem(it)
                Toast.makeText(requireContext(), "${it.name} deleted", Toast.LENGTH_SHORT).show()
                true // Return true to indicate that the event was handled
            } ?: false
        }
    }
}