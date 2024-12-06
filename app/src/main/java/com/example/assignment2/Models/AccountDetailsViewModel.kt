package com.example.assignment2.Models

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

class AccountDetailsViewModel {
    fun signOut(navController: NavController){
        val firebase = FirebaseAuth.getInstance()
        firebase.signOut()
        navController.navigate("login")
    }

    fun deleteUser(navController: NavController){
        val firebaseCurrentUser = FirebaseAuth.getInstance().currentUser
        firebaseCurrentUser?.delete()
        navController.navigate("login")
    }
}