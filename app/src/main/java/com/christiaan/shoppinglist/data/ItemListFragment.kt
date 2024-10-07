package com.christiaan.shoppinglist.ui.itemlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.christiaan.shoppinglist.R
import com.christiaan.shoppinglist.data.ItemDatabase
import com.christiaan.shoppinglist.data.ShoppingListRepository
import com.christiaan.shoppinglist.data.Item
import com.christiaan.shoppinglist.ui.create.ShoppingListViewModel
import com.christiaan.shoppinglist.ui.create.ShoppingListViewModelFactory

class ItemListFragment : Fragment() {

    private lateinit var shoppingListViewModel: ShoppingListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewItems)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize ViewModel
        val itemDatabase = ItemDatabase.getDatabase(requireContext())
        val shoppingListRepository = ShoppingListRepository(itemDatabase.shoppingListDao())
        shoppingListViewModel = ViewModelProvider(this, ShoppingListViewModelFactory(shoppingListRepository)).get(ShoppingListViewModel::class.java)

        // Assuming you pass the shopping list ID as an argument
        val shoppingListId = arguments?.getInt("shoppingListId") ?: 0

        // Observe items in the shopping list
        shoppingListViewModel.getItemsInShoppingList(shoppingListId).observe(viewLifecycleOwner, Observer { items: List<Item> ->
            adapter = ItemAdapter(items)
            recyclerView.adapter = adapter
        })
    }
}
