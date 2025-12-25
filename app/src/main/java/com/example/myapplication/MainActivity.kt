package com.example.myapplication

import android.R.attr.navigationIcon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CardColors
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.composable
import kotlin.io.path.Path

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = androidx.navigation.compose.rememberNavController()

                androidx.navigation.compose.NavHost(
                    navController = navController,
                    startDestination = "home" // 시작 화면 주소
                ) {
                    composable ("home"){
                        Home (
                            onNavigateToList = { navController.navigate("list") },
                            onNavigateToAdd = {navController.navigate("add") }
                        )
                    }

                    composable("list") {
                        TransactionListScreenHome(
                            onBack = { navController.popBackStack() } // 뒤로가기
                        )
                    }

                    composable("add") {
                        AddDataScreen (
                            onBack = { navController.popBackStack() } // 뒤로가기
                        )
                    }
                }
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
        // 전체 구조
        containerColor = Color.DarkGray,

        topBar = {
            // 상단 바
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                // 가운데서부터 시작
                title = {
                    Text("Finance Snapshot")
                },

                /**
                 * Slot 종류
                 * 오른쪽 : action / 왼쪽 : navigationIcon /가운데 : title
                 */
                // 오른쪽에서부터 시작
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "설정"
                        )
                    }
                },
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateToAdd()
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "추가 하기")
            }
        }
    ) { innerPadding ->
        // 데이터 모델 정의
        val assetList = listOf("은행 잔고 1", "은행 잔고 2", "은행 잔고 3", "은행 잔고 4", "주식 계좌", "비상금 계좌")
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                ElevatedCard(
                    modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 35.dp, bottom = 30.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFB3D9FF))
                ) {
                    Column(
                        modifier = Modifier.padding(32.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("총 자산 현황", color = Color.Black, fontSize = 15.sp)
                        Text(
                            "₩ 1,150,000",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 35.sp
                        )
                        Text("전월 대비 + 1.5%",  modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 10.dp), color = Color.Green, fontSize = 20.sp)
                    }
                }
            }

            item {
                Text("자산 분류 비율 (차트 대체)",  modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 10.dp), color = Color.Black, fontSize = 20.sp)
            }

            item {
                ElevatedCard(modifier = Modifier.padding(horizontal = 25.dp).fillMaxWidth()) {
                    Text("test", modifier = Modifier.padding(25.dp))
                }
            }

            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween, // 양쪽으로 밀어내기
                    verticalAlignment = Alignment.CenterVertically // 높이 중앙 맞춤
                ){
                    Text("주요 자산 목록 / 최근 활동")
                    Text("전체 내역 보기")
                }

            }

            items(assetList) { asset ->
                ListItem(
                    headlineContent = { Text(asset) },
                    supportingContent = { Text("자세한 자산 정보가 여기 표시됩니다.") },
                    leadingContent = {
                        Icon(Icons.Default.ArrowBack , contentDescription = null, tint = Color.Blue)
                    },
                    modifier = Modifier.clickable { /*클릭 이벤트*/ }
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 25.dp))
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