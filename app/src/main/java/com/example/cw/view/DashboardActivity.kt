package com.example.cw.view

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.Blue
import com.example.cw.ui.theme.Cream
import com.example.cw.ui.theme.DarkCream
import com.example.cw.ui.theme.White
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cw.R
import kotlinx.coroutines.delay

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardView()
        }
    }
}

data class NavItem(val name: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView() {
    val activity = (LocalContext.current as? Activity)
    var selectedIndex by remember { mutableStateOf(0) }

    val navList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Search", Icons.Default.Search),
        NavItem("Notification", Icons.Default.Notifications),
        NavItem("Profile", Icons.Default.Person)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Dashboard") },
                navigationIcon = {
                    IconButton(onClick = { activity?.finish() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
        },
        bottomBar = {
            NavigationBar {
                navList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        label = { Text(item.name) },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedIndex) {
                0 -> HomeTab()
                1 -> CenterText("Search")
                2 -> CenterText("Notification")
                3 -> CenterText("Profile")
            }
        }
    }
}

@Composable
private fun HomeTab() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(DarkCream)
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item { SearchBarStatic() }
            item { LocationBar() }
            item { AdPager(
                items = listOf(
                    R.drawable.orangebg, // replace/add more drawables
                    R.drawable.orangebg,
                    R.drawable.orangebg
                )
            )}
        }
    }
}

@Composable
fun AdPager(
    items: List<Int>,
    modifier: Modifier = Modifier
) {
    // PagerState for snapping pages
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { items.size })

    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            // Padding shows peeking side thumbnails like the reference
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSpacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = items[page]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Optional overlay content to match the second image style
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Time for Special Deal",
                                style = TextStyle(fontSize = 16.sp),
                                color = Color.White
                            )
                            Text(
                                text = "70% Off",
                                style = TextStyle(fontSize = 28.sp),
                                color = Color.White
                            )
                            Text(
                                text = "Free shipping on orders today",
                                style = TextStyle(fontSize = 14.sp),
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }
                        Button(
                            onClick = { /* TODO: navigate or perform action */ },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
                        ) {
                            Text("Shop Now", color = Color.White)
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun AdRowCarousel(
    items: List<Int>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val snapFling = rememberSnapFlingBehavior(lazyListState = listState)

    // Optional auto-scroll
    LaunchedEffect(items) {
        while (true) {
            delay(3000L)
            val next = (listState.firstVisibleItemIndex + 1) % items.size
            listState.animateScrollToItem(next)
        }
    }

    LazyRow(
        state = listState,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        flingBehavior = snapFling
    ) {
        items(items.size) { index ->
            Card(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(0.75f) // make central card prominent; peeking side cards visible
                    .clip(RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = items[index]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    // Minimal overlay example
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Button(
                            onClick = { /* TODO */ },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
                        ) {
                            Text("Shop Now", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationBar(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        shape = RoundedCornerShape(12.dp),
        color = Cream,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Column {
                    Text("Your Location", style = TextStyle(fontSize = 16.sp, color = Color.Gray))
                    Text("123 Main St, Cityville", style = TextStyle(fontSize = 18.sp, color = Color.Black))
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null)
            }
        }
    }
}

@Composable
private fun SearchBarStatic() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            singleLine = true,
            placeholder = {
                Text("Search here", color = Color(0xFF999999))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = Color(0xFF777777)
                )
            },
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.Mic,
                        contentDescription = null,
                        tint = Color(0xFF777777)
                    )
                }
            },
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE0E0E0),
                unfocusedBorderColor = Color(0xFFE0E0E0),
                disabledBorderColor = Color(0xFFE0E0E0),
                disabledTextColor = Color(0xFF999999),
                cursorColor = Color.Transparent
            )
        )
    }
}

@Composable
private fun CenterText(label: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = 22.sp, color = Color.DarkGray)
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardView()
}