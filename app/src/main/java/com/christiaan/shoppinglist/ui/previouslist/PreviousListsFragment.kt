package com.christiaan.shoppinglist.ui.previouslist

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
import com.christiaan.shoppinglist.ui.create.ShoppingListViewModel
import com.christiaan.shoppinglist.ui.create.ShoppingListViewModelFactory
import com.christiaan.shoppinglist.data.ShoppingList // Ensure this import is correct

class PreviousListsFragment : Fragment() {

    private lateinit var shoppingListViewModel: ShoppingListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShoppingListAdapter // Use the correct class name

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_previous_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewPreviousLists)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize ViewModel
        val itemDatabase = ItemDatabase.getDatabase(requireContext())
        val shoppingListRepository = ShoppingListRepository(itemDatabase.shoppingListDao())
        shoppingListViewModel = ViewModelProvider(this, ShoppingListViewModelFactory(shoppingListRepository)).get(ShoppingListViewModel::class.java)

        // Observe the shopping lists
        shoppingListViewModel.getAllShoppingLists().observe(viewLifecycleOwner, Observer { shoppingLists ->
            adapter = ShoppingListAdapter(shoppingLists) // Use the correct class name
            recyclerView.adapter = adapter
        })
    }
}
