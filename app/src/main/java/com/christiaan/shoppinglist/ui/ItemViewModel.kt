package com.christiaan.shoppinglist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christiaan.shoppinglist.data.Item
import com.christiaan.shoppinglist.data.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    // LiveData to observe items
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        getAllItems()
    }

    fun getAllItems() {
        viewModelScope.launch {
            _items.value = repository.getAllItems()
        }
    }

    fun insertItem(item: Item) {
        viewModelScope.launch {
            repository.insertItem(item)
            getAllItems() // Refresh the list after inserting
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
            getAllItems() // Refresh the list after deletion
        }
    }
}
