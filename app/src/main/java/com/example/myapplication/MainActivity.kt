package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Home() {}
            }
        }
    }
}

@Composable
fun Home( onClick: () -> Unit ) {
    val lightBlue = Color(0xA6DAF4)

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD1E4FF)),
            onClick = { onClick() }
        ) {
            Column (
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(32.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFD1E4FF)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("총 자산 현황",
                    fontSize = 15.sp,
                    color = Color.Black)

                Text("₩ 1,500,000",
                    Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp),
                    fontSize = 35.sp,
                    color = Color.Black)

                Text("전월 대비  + 1.5%",
                    fontSize = 14.sp,
                    color = Color.Green)
            }
        }

        Column(
            modifier = Modifier
                .padding(20.dp, 20.dp, 5.dp, 5.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            Text("자산 분류 비율 (차트 대체)",
                fontSize = 20.sp)
        }

        Card (
            modifier = Modifier
                .clickable(onClick = onClick)
                .clip(RoundedCornerShape(15.dp))
                .padding(20.dp, 10.dp, 20.dp, 5.dp)
                .fillMaxWidth()
        ) {
            Column (modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally){

                Text("파이 차트 시각영역")
            }
        }

        Column (
            modifier = Modifier
                .padding(20.dp, 20.dp, 5.dp, 5.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            Text("주요 자산 목록 / 최근 활동",
                fontSize = 20.sp)
        }

        val assetList = listOf("은행 잔고 1", "은행 잔고 2", "은행 잔고 3")
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(assetList) { asset ->
                Text(text = asset, modifier = Modifier.padding(16.dp))
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Home() {}
    }
}