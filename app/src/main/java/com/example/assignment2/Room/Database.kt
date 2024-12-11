package com.example.assignment2.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7
//https://developer.android.com/training/data-storage/room
//https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase

@Database(entities = [ContactIssue::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactIssueDao(): ContactIssueDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "contact_issues_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}