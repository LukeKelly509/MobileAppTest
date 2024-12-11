package Bars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyBottomBar(navController: NavController, modifier: Modifier){
    val homePageActive = navController.currentDestination?.route == "homePage"
    val googleMapsPageActive = navController.currentDestination?.route == "googleMapsPage"
    val favoritesPageActive = navController.currentDestination?.route == "favoritesPage"
    val leaderboardPageActive = navController.currentDestination?.route == "leaderboardPage"
    val tusGold = Color(0xFFA39461)
    BottomAppBar(
        modifier = modifier
            .background(color = Color.LightGray)
            .height(60.dp),
        containerColor = tusGold,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.navigate("homePage")
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                val backgroundColor by animateColorAsState(
                    if (homePageActive) Color.Black.copy(alpha = 0.2f) else Color.Transparent
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(width = 36.dp, height = 22.dp)
                            .background(color = backgroundColor, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home Page",
                            tint = Color.White
                        )
                    }
                    Text(
                        "Home",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    navController.navigate("googleMapsPage")
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                val backgroundColor by animateColorAsState(
                    if (googleMapsPageActive) Color.Black.copy(alpha = 0.2f) else Color.Transparent
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(width = 36.dp, height = 22.dp)
                            .background(color = backgroundColor, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = "Maps Page",
                            tint = Color.White
                        )
                    }
                    Text(
                        "Google Maps",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    navController.navigate("favoritesPage")
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                val backgroundColor by animateColorAsState(
                    if (favoritesPageActive) Color.Black.copy(alpha = 0.2f) else Color.Transparent
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(width = 36.dp, height = 22.dp)
                            .background(color = backgroundColor, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorites Page",
                            tint = Color.White
                        )
                    }
                    Text(
                        "Favorites",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = Color.White
                    )
                }
            }

            IconButton(
                onClick = {
                    navController.navigate("leaderboardPage")
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                val backgroundColor by animateColorAsState(
                    if (leaderboardPageActive) Color.Black.copy(alpha = 0.2f) else Color.Transparent
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(width = 36.dp, height = 22.dp)
                            .background(color = backgroundColor, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Leaderboard,
                            contentDescription = "Leaderboard Page",
                            tint = Color.White
                        )
                    }
                    Text(
                        "Leaderboard",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = Color.White
                    )
                }
            }
        }
    }
}