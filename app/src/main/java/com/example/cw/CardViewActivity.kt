package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.Black
import com.example.cw.ui.theme.CwTheme
import com.example.cw.ui.theme.Gray
import com.example.cw.ui.theme.Orange
import com.example.cw.ui.theme.White

class CardViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardViewBody()
        }
    }
}

@Composable
fun CardViewBody(){
    Scaffold() { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .background(Black)
        ){
            Image(
                painter = painterResource(id = R.drawable.backgroundcardview), // Replace with your image
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Or other scales like FillBounds, Fit
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = "Card",
                        color = White,
                        fontSize = 45.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    Image(
                        contentDescription = null,
                        painter = painterResource(R.drawable.profile_pic),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(45.dp)
                            .clip(CircleShape)
                    )
                }
                BankCard(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    "Name",
                    "1234567890",
                    R.drawable.bgc1
                )
                BankCard(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    "Name",
                    "1234567890",
                    R.drawable.bgc2
                )
                BankCard(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    "Name",
                    "1234567890",
                    R.drawable.bgc3
                )
                BankCard(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    "Name",
                    "1234567890",
                    R.drawable.bgc4
                )
            }
        }
    }
}

@Composable
fun BankCard(modifier: Modifier, name : String, number : String, bg : Int){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(11.dp),
        border = BorderStroke(1.dp, Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
                .background(White)
                .clip(RoundedCornerShape(11.dp))
        ){
            Image(
                painter = painterResource(bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    color = Black,
                    fontSize = 24.sp
                )
                Text(
                    text = number,
                    color = Black,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardViewPreview() {
    CardViewBody()
}