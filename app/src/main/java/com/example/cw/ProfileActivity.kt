package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileBody()
        }
    }
}

@Composable
fun ProfilePicture() {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = CircleShape
            )
            .padding(2.dp)
            .background(Color.White, CircleShape)
            .padding(1.dp)
    ){
        Image(
            painter = painterResource(R.drawable.a54fa76d3213d2052c0c21f1445fe5b1),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),

            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun ProfileBody() {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(
                        R.drawable.baseline_arrow_back_ios_24
                    ),
                    contentDescription = null
                )
                Text("UserName")
                Icon(
                    painter = painterResource(
                        R.drawable.baseline_more_vert_24
                    ),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(R.drawable.a54fa76d3213d2052c0c21f1445fe5b1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(shape = CircleShape)
                        .border(
                            width = 3.dp,
                            brush = Brush.sweepGradient(
                                listOf(
                                    Color.Red,
                                    Color(0xFFFF7F00), // Orange
                                    Color.Yellow,
                                    Color.Green,
                                    Color.Blue,
                                    Color(0xFF4B0082), // Indigo
                                    Color(0xFF8B00FF), // Violet
                                    Color.Red // smooth loop

                                )
                            ),
                            shape = CircleShape
                        ),

                    contentScale = ContentScale.Crop
                )
                Column {
                    Text("0")
                    Text("Posts")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("0M")
                    Text("Followers")
                }
                Column {
                    Text("0.0K")
                    Text("Followings")
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Text("Username")
                Text("Description")
                Text(text = "#HashTag", color = Color(0xFFADD8E6))
                Text(text = "Link", color = Color.Blue)
                Text(text = "Follow by username and username")
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RectangleShape
            ) {
                Text("Button")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {},
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFADD8E6),
                        contentColor = Color.White
                    )

                ) {
                    Text("Follow")
                }
                OutlinedButton(
                    onClick = {},
                    shape = RectangleShape
                ) {
                    Text("Message")
                }
                OutlinedButton(
                    onClick = {},
                    shape = RectangleShape
                ) {
                    Text("Email")
                }
                OutlinedButton(
                    onClick = {},
                    shape = RectangleShape
                ) {
                    Icon(
                        painter = painterResource(
                            R.drawable.outline_arrow_drop_down_24
                        ),
                        contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                repeat(5) {
                    ProfilePicture()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfile() {
    ProfileBody()
}