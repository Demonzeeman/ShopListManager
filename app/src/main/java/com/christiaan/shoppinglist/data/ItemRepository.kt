package com.christiaan.shoppinglist.data
import com.christiaan.shoppinglist.data.Item


class ItemRepository(private val itemDao: ItemDao) {

    // Fetch all items from the database
    suspend fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }

    // Insert a new item into the database
    suspend fun insertItem(item: Item) {
        itemDao.insertItem(item)
    }

    // Delete a specific item from the database
    suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }
}
