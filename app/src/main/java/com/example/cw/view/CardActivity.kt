package com.example.cw.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.ui.theme.CwTheme
import com.example.cw.ui.theme.GreenForCardBG
import com.example.cw.ui.theme.White

class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CwTheme {
                CardBody()
            }
        }
    }
}

@Composable
fun CardBody(){
    Scaffold{padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(color = GreenForCardBG)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    contentDescription = null,
                    painter = painterResource(R.drawable.profile_pic),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(45.dp)
                        .clip(CircleShape)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Column(

                ) {
                    Text(
                        text = "Card",
                        color = White,
                        fontSize = 50.sp
                    )
                    Text(
                        text = "Simple and easy to use app.",
                        color = White,
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.size(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconHolder(
                    Modifier
                        .size(150.dp),
                    R.drawable.book,
                    "Text"
                )
                Spacer(modifier = Modifier.width(30.dp))
                IconHolder(
                    Modifier
                        .size(150.dp),
                    R.drawable.home,
                    "Address"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconHolder(
                    Modifier
                        .size(150.dp),
                    R.drawable.person,
                    "Character"
                )
                Spacer(modifier = Modifier.width(30.dp))
                IconHolder(
                    Modifier
                        .size(150.dp),
                    R.drawable.card,
                    "Bank Card"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconHolder(
                    Modifier
                        .size(150.dp),
                    R.drawable.key,
                    "Password"
                )
                Spacer(modifier = Modifier.width(30.dp))
                IconHolder(
                    Modifier
                        .size(150.dp),
                    R.drawable.hand,
                    "logistics"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(80.dp)
                        .padding(
                            horizontal = 30.dp,
                        )

                ) {
                   Row(
                        modifier = Modifier
                            .background(White)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                   ) {
                        Image(
                            contentDescription = null,
                            painter = painterResource(R.drawable.settings),
                            modifier = Modifier
                                .size(50.dp)
                                .padding(10.dp)
                        )
                       Text(
                           text = "Settings"
                       )
                   }
                }
            }
        }

    }
}



@Composable
fun IconHolder(modifier: Modifier, image : Int, label : String){
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                contentDescription = null,
                painter = painterResource(image),
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = label
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview(){
    CardBody()
}