package com.christiaan.shoppinglist.data


import androidx.room.*

@Dao
interface ItemDao {

    // Insert a new item into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    // Fetch all items from the database
    @Query("SELECT * FROM item")
    suspend fun getAllItems(): List<Item>

    // Delete a specific item from the database
    @Delete
    suspend fun deleteItem(item: Item)
}
