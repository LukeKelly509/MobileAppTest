package Bars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.Models.AccountDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(modifier: Modifier, navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    val tusGold = Color(0xFFA39461)
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(tusGold),
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                }
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Hamburger Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Account Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            navController.navigate("accountDetails")
                        }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }