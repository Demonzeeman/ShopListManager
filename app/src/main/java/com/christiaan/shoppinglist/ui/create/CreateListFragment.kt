package com.christiaan.shoppinglist.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.christiaan.shoppinglist.R
import com.christiaan.shoppinglist.data.ItemDatabase
import com.christiaan.shoppinglist.data.ItemRepository
import com.christiaan.shoppinglist.data.ShoppingListRepository
import com.christiaan.shoppinglist.data.Item
import com.christiaan.shoppinglist.data.ShoppingList
import com.christiaan.shoppinglist.ui.ItemViewModel
import com.christiaan.shoppinglist.ui.ItemViewModelFactory
import com.christiaan.shoppinglist.ui.create.ShoppingListViewModel
import com.christiaan.shoppinglist.ui.create.ShoppingListViewModelFactory

class CreateListFragment : Fragment() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var shoppingListViewModel: ShoppingListViewModel
    private lateinit var listViewItems: ListView
    private lateinit var adapter: ArrayAdapter<Item>
    private lateinit var editTextListName: EditText
    private lateinit var buttonCreateList: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextListName = view.findViewById(R.id.editTextListName)
        buttonCreateList = view.findViewById(R.id.buttonCreateList)
        listViewItems = view.findViewById(R.id.listViewItems)

        // Initialize ViewModels
        val itemDatabase = ItemDatabase.getDatabase(requireContext())
        val itemRepository = ItemRepository(itemDatabase.itemDao())
        itemViewModel = ViewModelProvider(this, ItemViewModelFactory(itemRepository)).get(ItemViewModel::class.java)

        val shoppingListRepository = ShoppingListRepository(itemDatabase.shoppingListDao())
        shoppingListViewModel = ViewModelProvider(this, ShoppingListViewModelFactory(shoppingListRepository)).get(ShoppingListViewModel::class.java)

        // Setup the ListView adapter
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_multiple_choice, mutableListOf())
        listViewItems.adapter = adapter
        listViewItems.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        // Observe items from the ViewModel
        itemViewModel.items.observe(viewLifecycleOwner, Observer { items ->
            adapter.clear()
            adapter.addAll(items)
        })

        // Handle Create List button click
        buttonCreateList.setOnClickListener {
            val listName = editTextListName.text.toString()
            val selectedItems = mutableListOf<Int>()

            // Get selected item IDs
            for (i in 0 until adapter.count) {
                if (listViewItems.isItemChecked(i)) {
                    val item = adapter.getItem(i) // No need for casting since adapter is of type Item
                    if (item != null) { // Check for null
                        selectedItems.add(item.id) // This will now work since id is Int
                    }
                }
            }

            // Create and save the shopping list
            if (listName.isNotEmpty() && selectedItems.isNotEmpty()) {
                val newList = ShoppingList(name = listName, items = selectedItems)
                shoppingListViewModel.insertShoppingList(newList)
                editTextListName.text.clear() // Clear the input field after creating the list
            }
        }
    }
}
