package com.christiaan.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShoppingListDao {
    @Insert
    suspend fun insert(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_list")
    fun getAllShoppingLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM item WHERE shopping_list_id = :shoppingListId")
    fun getItemsInShoppingList(shoppingListId: Int): LiveData<List<Item>> // Assuming there's an Item entity
}


