package com.example.assignment2

import Bars.MyBottomBar
import Bars.MyTopBar
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2.Models.GoogleMapsPageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun googleMapsPage(navController: NavController, scope: CoroutineScope, drawerState: DrawerState) {
    val homePageActive = navController.currentDestination?.route == "homePage"
    val tusGold = Color(0xFFA39461)
    val googleMapsPageActive = navController.currentDestination?.route == "googleMapsPage"
    val favoritesPageActive = navController.currentDestination?.route == "favoritesPage"
    val leaderboardPageActive = navController.currentDestination?.route == "leaderboardPage"
    val configuration = LocalConfiguration.current
    val tabletView = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    GoogleMapsPageViewModel().googleMap()

    //https://www.youtube.com/watch?v=2pGTSiqnW90
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .background(tusGold)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.applogo),
                                contentDescription = "App Icon",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.secondary),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))

                            Text(
                                text = "Quizleys",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                )
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(10.dp).fillMaxWidth()
                            .background(color = Color.White)
                    )

                    val backgroundColor = Color(0xFFD8CAB8)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = "Home Page",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                    )
                                },
                                selected = homePageActive,
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate("homePage")
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Home,
                                        contentDescription = "Home Icon",
                                        tint = Color.Black,
                                    )
                                },
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )

                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = "Google Maps",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                    )
                                },
                                selected = googleMapsPageActive,
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate("googleMapsPage")
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = "Location Icon",
                                        tint = Color.Black,
                                    )
                                },
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(backgroundColor)
                            )

                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = "Favorites",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                    )
                                },
                                selected = favoritesPageActive,
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate("favoritesPage")
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Favorites Icon",
                                        tint = Color.Black,
                                    )
                                },
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )

                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = "Leaderboard",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                    )
                                },
                                selected = leaderboardPageActive,
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate("leaderboardPage")
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Leaderboard,
                                        contentDescription = "Leaderboard Icon",
                                        tint = Color.Black,
                                    )
                                },
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )

                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = "Account Details",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                    )
                                },
                                selected = navController.currentDestination?.route == "accountDetails",
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate("accountDetails")
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.AccountCircle,
                                        contentDescription = "Account Icon",
                                        tint = Color.Black,
                                    )
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )

                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = "Settings",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                    )
                                },
                                selected = navController.currentDestination?.route == "",
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate("")
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Settings,
                                        contentDescription = "Account Icon",
                                        tint = Color.Black,
                                    )
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )

                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = "Contact Us!",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                    )
                                },
                                selected = navController.currentDestination?.route == "contactPageActive",
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate("contactPage")
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.ContactPage,
                                        contentDescription = "Account Icon",
                                        tint = Color.Black,
                                    )
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )
                        }
                    }
                }
            }
        },
        drawerState = drawerState
    ) {
        if (!tabletView) {
            Box(modifier = Modifier.fillMaxSize()) {
                MyTopBar(
                    scope = scope,
                    drawerState = drawerState,
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                )
            }
        }

        if (!tabletView) {
            Box(modifier = Modifier.fillMaxSize()) {
                MyBottomBar(
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            }
        }
    }
}