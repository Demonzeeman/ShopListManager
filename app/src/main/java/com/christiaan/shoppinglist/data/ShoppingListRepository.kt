package com.christiaan.shoppinglist.data

import androidx.lifecycle.LiveData
import com.christiaan.shoppinglist.data.Item // Correct import for Item

class ShoppingListRepository(private val shoppingListDao: ShoppingListDao) {
    suspend fun insertShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.insert(shoppingList)
    }

    fun getAllShoppingLists(): LiveData<List<ShoppingList>> {
        return shoppingListDao.getAllShoppingLists()
    }

    fun getItemsInShoppingList(shoppingListId: Int): LiveData<List<Item>> {
        return shoppingListDao.getItemsInShoppingList(shoppingListId)
    }
}
