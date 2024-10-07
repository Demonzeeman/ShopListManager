package com.christiaan.shoppinglist.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromIntListToString(value: List<Int>?): String? {
        return value?.joinToString(separator = ",") { it.toString() }
    }

    @TypeConverter
    fun fromStringToIntList(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() }
    }

    // If you also want to keep the List<Long> converters, you can leave them as they are
    @TypeConverter
    fun fromLongListToString(value: List<Long>?): String? {
        return value?.joinToString(separator = ",") { it.toString() }
    }

    @TypeConverter
    fun fromStringToLongList(value: String?): List<Long>? {
        return value?.split(",")?.map { it.toLong() }
    }
}
