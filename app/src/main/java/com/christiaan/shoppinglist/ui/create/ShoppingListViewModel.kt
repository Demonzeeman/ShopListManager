package com.christiaan.shoppinglist.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.christiaan.shoppinglist.data.ShoppingListRepository
import com.christiaan.shoppinglist.data.ShoppingList
import com.christiaan.shoppinglist.data.Item
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val repository: ShoppingListRepository) : ViewModel() {
    // Function to insert shopping lists
    fun insertShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            repository.insertShoppingList(shoppingList)
        }
    }

    // Function to get all shopping lists as LiveData
    fun getAllShoppingLists(): LiveData<List<ShoppingList>> {
        return repository.getAllShoppingLists()
    }

    // Function to get items in a shopping list
    fun getItemsInShoppingList(shoppingListId: Int): LiveData<List<Item>> {
        return repository.getItemsInShoppingList(shoppingListId)
    }
}
