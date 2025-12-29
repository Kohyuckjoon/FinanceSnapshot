package com.example.myapplication

import android.R.attr.navigationIcon
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Home(
                    onNavigateToList = {},
                    onNavigateToAdd = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    onNavigateToList: () -> Unit,
    onNavigateToAdd: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.surfaceVariant,
                ),

                title = {
                    Text("미니 시그널(가계부)", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            )
        },
//        containerColor = Color.DarkGray
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Card (
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ){
                Column (
                    modifier = Modifier.padding(20.dp).fillMaxWidth(),
                ){
                    Text("이번달 총 지출", fontSize = 13.sp)
                    Text("51,750원", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }

            Column (
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ){
                Text("최근 내역", fontWeight = FontWeight.Bold, color = Color.Gray)
            }

            LazyColumn (
                modifier = Modifier.padding(15.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                item {
                    Text("test_01")
                }

                item {
                    Text("test_02")
                }

                item {
                    Text("test_03")
                }
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Home(
            onNavigateToList = {},
            onNavigateToAdd = {}
        )
    }
}