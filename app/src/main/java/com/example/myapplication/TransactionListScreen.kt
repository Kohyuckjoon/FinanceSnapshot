package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.example.myapplication.ui.theme.MyApplicationTheme

class TransactionListScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TransactionListScreenHome() {}
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreenHome(onBack: () -> Unit) {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                
                title = {
                    Text("전체 내역 조회", color = Color.Black)
                },

                navigationIcon = {
                    IconButton(onClick = {onBack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
                }
            )
        },
    ){ innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
            ) {
                ElevatedCard(
                    onClick = { },
                    modifier = Modifier.weight(1f), // 화면에서 절반 차지
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(7.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("자산 유형 : 전체")
                    }
                }

                ElevatedCard(
                    onClick = { },
                    modifier = Modifier.weight(1f), // 화면에서 절반 차지
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(7.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("정렬 : 최신순")
                    }
                }
            }

            androidx.compose.foundation.lazy.LazyColumn (
                modifier = Modifier.fillMaxSize(),
            ){
                items (20){ index ->
                    TransactionListItem("자산 내역 ${index + 1}")
                }
            }
        }
    }
}

@Composable
fun TransactionListItem(title: String) {
    Column (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.bodyLarge)
                Text(text = "은행 | 2024.12.24", style = MaterialTheme.typography.labelSmall)
            }

            Text(
                text =  "50,000원",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }

        androidx.compose.material3.HorizontalDivider(
            modifier = Modifier.padding(top = 12.dp),
            thickness = 0.5.dp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    MyApplicationTheme {
        TransactionListScreenHome() {}
    }
}