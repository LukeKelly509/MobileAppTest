package com.example.assignment2.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//https://developer.android.com/reference/androidx/room/Dao
//https://developer.android.com/training/data-storage/room
//https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase
@Dao
interface ContactIssueDAO {
    @Insert
    suspend fun addContactIssue(issue: ContactIssue)

    @Query("SELECT * FROM contact_issues")
    fun getAllContactIssues(): LiveData<List<ContactIssue>>
}