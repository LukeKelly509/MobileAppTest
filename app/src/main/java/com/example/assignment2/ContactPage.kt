package com.example.assignment2

import Bars.MyBottomBar
import Bars.MyTopBar
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.assignment2.Bars.backAppBar
import com.example.assignment2.Room.AppDatabase
import com.example.assignment2.Room.ContactIssue
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun contactPage(navController: NavController) {
    val tusGold = Color(0xFFA39461)
    val currentUser = FirebaseAuth.getInstance().currentUser
    val scroll = rememberScrollState()
    val context = LocalContext.current.applicationContext //application's context
    val database = AppDatabase.getDatabase(context) //getting database so I can sava to it
    val contactIssueDAO = database.contactIssueDao() //database part that works with the contactIssue
    val coroutineScope = rememberCoroutineScope() //for running in background - saving to database

    backAppBar(navController)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 30.dp)
                .verticalScroll(scroll)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account Icon",
                modifier = Modifier
                    .size(80.dp)
            )

            Text(
                text = "Hi, ${currentUser?.email ?: ""}!\nWhat Problem Are You Having?",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )

            val problems = listOf(
                "Connectivity Issue",
                "App Crash",
                "Feature Request",
                "Account Settings",
                "Payment Issue"
            )

            problems.forEach { problem ->
                Button(
                    onClick = {
                        val email = currentUser?.email ?: ""
                        //contactIssue variables so they can be added
                        val issue = ContactIssue(
                            email = email,
                            problem = problem
                        )
                        coroutineScope.launch {
                            contactIssueDAO.addContactIssue(issue) //this is where it is added
                            Log.d("RoomDatabase", "Added Issue: $issue") //logcat stuff so I can see if it was added to the database
                        }
                        navController.navigate("homePage")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(tusGold)
                ) {
                    Text(
                        text = problem,
                        color = Color.White,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}
