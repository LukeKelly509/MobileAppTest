package com.example.assignment2

import Bars.MyBottomBar
import Bars.MyTopBar
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import com.example.assignment2.Bars.backAppBar
import com.example.assignment2.CloudMessaging.NotificationMessaging
import com.example.assignment2.Models.AccountDetailsViewModel
import com.example.assignment2.Models.CategoryPageViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@Composable
fun categoryPage(navController: NavController, chosenCategory: String, favorites: MutableList<String>) {
    val quizzes = remember { mutableStateOf<List<CategoryPageViewModel.Quizzes>>(emptyList()) }

    //was having issues with it in a viewModel so I put it here and it worked
    FirebaseFirestore.getInstance()
        .collection("quizzes")
        .whereEqualTo("categoryID", chosenCategory)
        .get()
        .addOnSuccessListener { documents ->
            quizzes.value = documents.map { document ->
                CategoryPageViewModel.Quizzes(
                    text = document.getString("text") ?: "",
                )
            }
        }
        .addOnFailureListener {
            quizzes.value = emptyList()
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "$chosenCategory",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (quizzes.value.isEmpty()) {
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(quizzes.value) { quiz ->
                        quizCard(quiz = quiz, navController = navController, favorites = favorites)
                    }
                }
            }
        }
    }
}


@Composable
fun quizCard(quiz: CategoryPageViewModel.Quizzes, navController: NavController, favorites: MutableList<String>) {
    val context = LocalContext.current
    val externalNotification = NotificationMessaging(context) //this is calling in the notification class
    val isFavorite = remember { mutableStateOf(favorites.contains(quiz.text)) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                navController.navigate("quizPage/${quiz.text}")
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD8CAB8)),
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = quiz.text,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = {
                    if (isFavorite.value) {
                        favorites.remove(quiz.text)
                    } else {
                        favorites.add(quiz.text)

                        externalNotification.triggerNotification(
                            title = "\"${quiz.text}\" favorited",
                            message = "The quiz has been added to your favorites."
                        )
                        //this is making it so the quiz text aka name will be displayed on the notification when favorited
                    }
                    isFavorite.value = !isFavorite.value
                }){
                    Icon(
                        imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}