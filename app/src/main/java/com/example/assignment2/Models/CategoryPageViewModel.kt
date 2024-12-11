package com.example.assignment2.Models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class CategoryPageViewModel : ViewModel() {
    data class Quizzes(
        val text: String,
    )

    data class Subcategory(
        val text: String,
        val options: List<String>,
        val rightAnswer: Int
    )
}