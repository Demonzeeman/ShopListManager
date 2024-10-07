package com.christiaan.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "item") // Define the table name
data class Item(
    @PrimaryKey(autoGenerate = true) // Automatically generate the ID
    val id: Int,

    val name: String,

    var isDone: Boolean = false, // Default value is false

    @ColumnInfo(name = "shopping_list_id") // Explicitly specify the column name
    val shoppingListId: Int // Foreign key to the shopping list
) {
    override fun toString(): String {
        return name // Return the name when the Item is converted to a string
    }
}
