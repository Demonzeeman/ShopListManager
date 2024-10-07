package com.christiaan.shoppinglist.data

import androidx.room.TypeConverter

class GeneralConverters { // Changed after issue with same name in ShoppingListConverters.kt
    @TypeConverter
    fun fromList(value: List<Int>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toList(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() } ?: emptyList()
    }
}
