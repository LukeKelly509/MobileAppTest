package com.example.assignment2.Models

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.lang.reflect.Modifier

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