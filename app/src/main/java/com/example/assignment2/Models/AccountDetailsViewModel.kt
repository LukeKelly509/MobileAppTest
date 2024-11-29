package com.example.assignment2.Models

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

class AccountDetailsViewModel {
    fun signOut(navController: NavController){
        val firebase = FirebaseAuth.getInstance()
        firebase.signOut()
        navController.navigate("login")
    }
}