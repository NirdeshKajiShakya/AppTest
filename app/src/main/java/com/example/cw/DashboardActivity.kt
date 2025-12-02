package com.example.cw

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cw.ui.theme.Blue
import com.example.cw.ui.theme.BlueLight
import com.example.cw.ui.theme.White

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardView()
        }
    }
}

data class NavItem(val name: String, val icon : ImageVector)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(){
    val activity = (LocalContext.current as? Activity)
    var selectedIndex by remember { mutableStateOf(0) }

    var navList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Search", Icons.Default.Search),
        NavItem("Notification", Icons.Default.Notifications),
        NavItem("Profile", Icons.Default.Person)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Dashboard")},
                navigationIcon = {
                    IconButton(onClick = {
                        activity?.finish()   // closes current activity
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
        },
        bottomBar = {
             NavigationBar() {
                 navList.forEachIndexed { index, item->
                     NavigationBarItem(
                         selected = selectedIndex == index,
                         onClick = {
                             selectedIndex = index
                         },
                         label = {
                             Text(item.name)
                         },
                         icon = {
                             Icon(
                                imageVector = item.icon, contentDescription = item.name
                             )
                         }
                     )
                 }
             }
        }
    ) {
        padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when(selectedIndex){
                0 -> Text("Home")
                1 -> Text("Search")
                2 -> Text("Notification")
                3 -> Text("Profile")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    DashboardView()
}