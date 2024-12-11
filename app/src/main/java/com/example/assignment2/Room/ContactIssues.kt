package com.example.assignment2.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

//https://developer.android.com/training/data-storage/room
//https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase
//https://developer.android.com/reference/androidx/room/Entity

@Entity(tableName = "contact_issues")
data class ContactIssue(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val problem: String
)
