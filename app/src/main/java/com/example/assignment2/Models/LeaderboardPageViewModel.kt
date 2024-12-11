package com.example.assignment2.Models

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class LeaderboardPageViewModel {
    fun getLeaderboardForCategory(category: String) {
        FirebaseFirestore.getInstance()
            .collection("leaderboard")
            .whereEqualTo("categoryID", category)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val email = document.getString("email") ?: ""
                    Log.d("Leaderboard", "$(email:)")
                }
            }
            .addOnFailureListener {

            }
    }

}