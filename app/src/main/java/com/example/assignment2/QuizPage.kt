package com.example.assignment2

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2.Bars.backAppBar
import com.example.assignment2.Models.CategoryPageViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun quizPage(navController: NavController, chosenCategory: String) {
    val subcategories = remember { mutableStateOf<List<CategoryPageViewModel.Subcategory>>(emptyList()) }

    FirebaseFirestore.getInstance()
        .collection("chosenQuiz")
        .whereEqualTo("categoryID", chosenCategory)
        .get()
        .addOnSuccessListener { documents ->
            subcategories.value = documents.map { document ->
                CategoryPageViewModel.Subcategory(
                    text = document.getString("text") ?: "",
                    options = document.get("options") as? List<String> ?: emptyList(),
                    rightAnswer = document.getLong("rightAnswer")?.toInt() ?: -1
                )
            }
        }
        .addOnFailureListener { exception ->
            subcategories.value = emptyList()
        }

    backAppBar(navController)

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = "$chosenCategory",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (subcategories.value.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(subcategories.value) { index, subcategory ->
                        questionCard(subcategory = subcategory, currentNumber = index) //current number is used for the question card - helps with what question number we on
                    }
                }
            }
        }
    }
}

@Composable
fun questionCard(subcategory: CategoryPageViewModel.Subcategory, currentNumber: Int) {
    val tusGold = Color(0xFFA39461)
    var chosenAnswer by rememberSaveable { mutableStateOf<Int?>(null) } //keeping track of user's chosen answer
    var isAnswerCorrect by rememberSaveable { mutableStateOf<Boolean?>(null) } //checks if the answer is right or wrong

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD8CAB8))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Question ${currentNumber + 1}",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subcategory.text,
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            subcategory.options.forEachIndexed { index, option ->
                Button(
                    onClick = {
                        chosenAnswer = index //saves answer when button is clicked
                        isAnswerCorrect = index == subcategory.rightAnswer //checks if the chosen answer is right
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when {
                            chosenAnswer == index && isAnswerCorrect == true -> Color.Green //changes color if right
                            chosenAnswer == index && isAnswerCorrect == false -> Color.Red //changes color if wrong
                            else -> tusGold //starting color
                        }
                    )
                ) {
                    Text(
                        text = option,
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    )
                }
            }

            //shows correct or incorrect messages
            if (chosenAnswer != null) {
                Text(
                    text = if (isAnswerCorrect == true) "Correct!" else "Incorrect", //displays correct or incorrect
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = if (isAnswerCorrect == true) Color.Green else Color.Red
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}