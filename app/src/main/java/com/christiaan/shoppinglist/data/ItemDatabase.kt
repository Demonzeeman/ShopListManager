package com.christiaan.shoppinglist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.christiaan.shoppinglist.data.Item
import com.christiaan.shoppinglist.data.ShoppingList
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase



@Database(entities = [Item::class, ShoppingList::class], version = 2) // Increment version to 2
@TypeConverters(Converters::class)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun shoppingListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        // Define the migration from version 1 to version 2
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the shopping_list table if it doesn't already exist
                database.execSQL(
                    """
            CREATE TABLE IF NOT EXISTS `shopping_list` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `name` TEXT NOT NULL,
                `items` TEXT NOT NULL
            )
            """.trimIndent()
                )
            }
        }

        fun getDatabase(context: Context): ItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "item_database"
                )
                    .addMigrations(MIGRATION_1_2) // Add the migration here
                    .fallbackToDestructiveMigration() // Allow destructive migration
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}