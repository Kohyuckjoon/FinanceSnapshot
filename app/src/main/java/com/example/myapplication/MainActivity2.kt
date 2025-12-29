//package com.example.myapplication
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material.icons.filled.PieChart
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//// 임시 데이터 모델
//data class Expense(val id: Int, val title: String, val amount: String, val category: String, val color: Color)
//
//class MainActivity2 : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MainNavigation()
//        }
//    }
//}
//
//@Composable
//fun MainNavigation() {
//    // 화면 전환을 위한 상태 (실제 앱에서는 Navigation Library를 쓰지만, 지금은 빠른 확인을 위해 State 사용)
//    var currentScreen by remember { mutableStateOf("Home") }
//
//    Surface(color = MaterialTheme.colorScheme.background) {
//        when (currentScreen) {
//            "Home" -> HomeScreen(
//                onNavigateToAdd = { currentScreen = "Add" },
//                onNavigateToStats = { currentScreen = "Stats" }
//            )
//            "Add" -> AddExpenseScreen(onBack = { currentScreen = "Home" })
//            "Stats" -> StatisticsScreen(onBack = { currentScreen = "Home" })
//        }
//    }
//}
//
//// --- Screen A: Home (지출 내역 리스트) ---
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(onNavigateToAdd: () -> Unit, onNavigateToStats: () -> Unit) {
//    val dummyExpenses = listOf(
//        Expense(1, "스타벅스", "5,500원", "식비", Color(0xFFFF7043)),
//        Expense(2, "버스 요금", "1,250원", "교통", Color(0xFF26A69A)),
//        Expense(3, "무신사 쇼핑", "45,000원", "쇼핑", Color(0xFF42A5F5))
//    )
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("미니 시그널 (가계부)", fontWeight = FontWeight.Bold) },
//                actions = {
//                    IconButton(onClick = onNavigateToStats) {
//                        Icon(Icons.Default.PieChart, contentDescription = "통계")
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = onNavigateToAdd) {
//                Icon(Icons.Default.Add, contentDescription = "추가")
//            }
//        }
//    ) { padding ->
//        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
//            // 요약 카드
//            Card(
//                modifier = Modifier.padding(16.dp).fillMaxWidth(),
//                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
//                shape = RoundedCornerShape(16.dp)
//            ) {
//                Column(modifier = Modifier.padding(24.dp)) {
//                    Text("이번 달 총 지출", fontSize = 16.sp)
//                    Text("51,750원", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
//                }
//            }
//
//            Text(
//                "최근 내역",
//                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
//                fontWeight = FontWeight.Bold,
//                color = Color.Gray
//            )
//
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                items(dummyExpenses) { expense ->
//                    ListItem(
//                        headlineContent = { Text(expense.title, fontWeight = FontWeight.Medium) },
//                        supportingContent = { Text(expense.category) },
//                        trailingContent = { Text(expense.amount, color = Color.Red, fontWeight = FontWeight.Bold) },
//                        leadingContent = {
//                            Box(modifier = Modifier.size(12.dp).background(expense.color, RoundedCornerShape(50)))
//                        }
//                    )
//                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
//                }
//            }
//        }
//    }
//}
//
//// --- Screen B: Add (지출 입력) ---
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddExpenseScreen(onBack: () -> Unit) {
//    var amount by remember { mutableStateOf("") }
//    var memo by remember { mutableStateOf("") }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("지출 내역 추가") },
//                navigationIcon = {
//                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "뒤로가기") }
//                }
//            )
//        }
//    ) { padding ->
//        Column(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
//            OutlinedTextField(
//                value = amount,
//                onValueChange = { amount = it },
//                label = { Text("금액") },
//                modifier = Modifier.fillMaxWidth(),
//                placeholder = { Text("0") },
//                prefix = { Text("₩ ") }
//            )
//
//            OutlinedTextField(
//                value = memo,
//                onValueChange = { memo = it },
//                label = { Text("내용") },
//                modifier = Modifier.fillMaxWidth(),
//                placeholder = { Text("어디에 쓰셨나요?") }
//            )
//
//            Button(
//                onClick = { /* 임시 저장 로직 */ },
//                modifier = Modifier.fillMaxWidth().height(56.dp),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Text("저장하기", fontSize = 18.sp)
//            }
//        }
//    }
//}
//
//// --- Screen C: Statistics (분석 통계) ---
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun StatisticsScreen(onBack: () -> Unit) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("소비 분석") },
//                navigationIcon = {
//                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "뒤로가기") }
//                }
//            )
//        }
//    ) { padding ->
//        Column(modifier = Modifier.padding(padding).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Spacer(modifier = Modifier.height(32.dp))
//
//            // Canvas를 활용한 아주 간단한 파이 차트 프로토타입
//            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(200.dp)) {
//                Canvas(modifier = Modifier.size(200.dp)) {
//                    drawArc(color = Color(0xFF42A5F5), startAngle = 0f, sweepAngle = 200f, useCenter = true, size = Size(size.width, size.height))
//                    drawArc(color = Color(0xFFFF7043), startAngle = 200f, sweepAngle = 100f, useCenter = true, size = Size(size.width, size.height))
//                    drawArc(color = Color(0xFF26A69A), startAngle = 300f, sweepAngle = 60f, useCenter = true, size = Size(size.width, size.height))
//                }
//            }
//
//            Spacer(modifier = Modifier.height(40.dp))
//
//            // 카테고리별 합계 리스트
//            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
//                StatRow("쇼핑", "45,000원", Color(0xFF42A5F5), "60%")
//                StatRow("식비", "5,500원", Color(0xFFFF7043), "30%")
//                StatRow("교통", "1,250원", Color(0xFF26A69A), "10%")
//            }
//        }
//    }
//}
//
//
//// --- 피그마 작업을 위한 미리보기 코드 ---
//@androidx.compose.ui.tooling.preview.Preview(showBackground = true, name = "홈 화면")
//@Composable
//fun HomePreview() {
//    MaterialTheme {
//        HomeScreen(onNavigateToAdd = {}, onNavigateToStats = {})
//    }
//}
//
//@androidx.compose.ui.tooling.preview.Preview(showBackground = true, name = "입력 화면")
//@Composable
//fun AddPreview() {
//    MaterialTheme {
//        AddExpenseScreen(onBack = {})
//    }
//}
//
//@androidx.compose.ui.tooling.preview.Preview(showBackground = true, name = "통계 화면")
//@Composable
//fun StatsPreview() {
//    MaterialTheme {
//        StatisticsScreen(onBack = {})
//    }
//}
//
//
//@Composable
//fun StatRow(label: String, amount: String, color: Color, percent: String) {
//    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
//        Box(modifier = Modifier.size(12.dp).background(color, RoundedCornerShape(2.dp)))
//        Text(label, modifier = Modifier.padding(start = 8.dp).weight(1f))
//        Text(amount, fontWeight = FontWeight.Bold)
//        Text(percent, modifier = Modifier.padding(start = 16.dp), color = Color.Gray, fontSize = 12.sp)
//    }
//}