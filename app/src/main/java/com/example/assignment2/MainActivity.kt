package com.example.assignment2

import com.example.assignment2.Models.LoginPageViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "homePage"
                ) {
                    composable("login") {
                        login(navController)
                    }
                    composable("signUp") {
                        signUp(navController)
                    }
                    composable("homePage") {
                        homePage(navController)
                    }
                    composable("accountDetails") {
                        accountDetails(navController)
                    }
                    composable("errorPage") {
                        errorPage(navController)
                    }
                    composable("contactPage") {
                        contactPage(navController)
                    }
                    composable("googleMapsPage"){
                        googleMapsPage(navController)
                    }
                    composable("favoritesPage"){
                        favoritesPage(navController)
                    }
                    composable("leaderboardPage"){
                        leaderboardPage(navController)
                    }
                    composable("categoryPage"){
                        categoryPage(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun login(navController: NavController){
    val tusGold = Color(0xFFA39461)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Bars.beginningBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(top = 240.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome!",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Login To Get Started!",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }

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
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(text = "Email")},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password")},
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (LoginPageViewModel().validLoginAttempt(email, password)) {
                        LoginPageViewModel().signInWithFirebase(email, password, navController)
                    } else {
                        navController.navigate("errorPage")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(tusGold)
            ) {
                Text(text = "Login")
            }
        }
    }
}