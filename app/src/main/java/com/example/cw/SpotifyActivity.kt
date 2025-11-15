package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SpotifyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpotifyBody()
        }
    }
}

@Composable
fun SpotifyBody() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // Top Section Title
        Text(
            text = "Recently played",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        // Row of Profile Pictures
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ProfilePicture(size = 80.dp, imageRes = R.drawable.twoimage)   // your composable
                Text("Lana Del Rey", color = Color.White, fontSize = 14.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ProfilePicture(size = 80.dp, imageRes = R.drawable.threeimage)
                Text("Marvin Gaye", color = Color.White, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        Row(

        ) {
            Image(
                painter = painterResource(R.drawable.oneimage),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp),
                contentScale = ContentScale.Crop
            )
            // Section Title
            Text(
                text = "Your 2021 in review",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        // Two images Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ReviewCard("Your Top Songs 2021",R.drawable.img)
            ReviewCard("Your Artists Revealed",R.drawable.img_1)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Section Title
        Text(
            text = "Editor's picks",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Two album cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AlbumCard("Ed Sheeran, Big Sean,\nJuice WRLD, Post Malone",R.drawable.img_2)
            AlbumCard("Mitski, Tame Impala,\nGlass Animals, Charli XCX",R.drawable.img_3)
        }
    }
}

@Composable
fun ProfilePicture(size: Dp = 80.dp, imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun AlbumCard(description: String, ImageRes: Int) {
    Column(

    ) {
        Image(
            painter = painterResource(ImageRes),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = description,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ReviewCard(title: String, ImageRes: Int) {
    Column(

    ) {
        Image(
            painter = painterResource(ImageRes),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = title,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SpotifyPreview() {
    SpotifyBody()
}