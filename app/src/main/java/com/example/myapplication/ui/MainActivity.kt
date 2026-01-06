package com.example.myapplication.ui.theme

import android.graphics.drawable.Icon
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.AddDataScreen
import com.example.myapplication.viewmodel.ExpenseViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.data.ExpenseEntity

// 앱의 진입점 및 내비게이션 설정
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable ("home"){
                        Home(
                            onNavigateToList = { },
                            onNavigateToAdd = {
                                navController.navigate("add_data")
                            }
                        )
                    }
                    composable ("add_data"){
                        AddDataScreen(
                            onBack = {
                                navController.popBackStack()
                            },

                            onNavigateToHome = {
                                navController.popBackStack()
                            }
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
    viewModel: ExpenseViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val expenseList by viewModel.allExpenses.collectAsState(initial = emptyList())
    val totalAmount by viewModel.totalAmount.collectAsState(initial = 0L)
    val formatter = DecimalFormat("#,###")

//    HomeContent(
//        expenseList = expenseList,
//        totalAmount = totalAmount,
//        onNavigateToAdd = onNavigateToAdd,
//        onDeleteExpense = { viewModel.deleteExpense(it) }
//    )

    // 다이얼로그 추가
    var showDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<ExpenseEntity?> (null) }

    if (showDialog && itemToDelete != null){
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showDialog = false }, // 외부 클릭시 닫기
            title = { Text(text = "선택한 항목을 삭제하시겠습니까?")},
            confirmButton = {
                androidx.compose.material3.TextButton(
                    onClick = {
                        itemToDelete?.let {viewModel.deleteExpense(it)}
                        showDialog = false
                    }
                ) {
                    Text("삭제", color = Color.Red)
                }
            },

            dismissButton = {
                androidx.compose.material3.TextButton(
                    onClick = {showDialog = false}
                ) {
                    Text("취소")
                }
            }

        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.surfaceVariant,
                ),

                title = {
                    Text("부동산 계산기", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToAdd() },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "추가 버튼")
            }
        }
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
                    Text("총 보증금액", fontSize = 13.sp)
                    Text(
                        "${formatter.format(totalAmount)}원",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column (
                modifier = Modifier.fillMaxWidth().padding(10.dp, 20.dp, 10.dp, 7.dp)
            ){
                Text("최근 내역", fontWeight = FontWeight.Bold, color = Color.Gray)
            }

            if (expenseList.isEmpty()) {
                Column (
                    modifier = Modifier.fillMaxWidth().padding(50.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = androidx.compose.ui.Modifier.size(64.dp),
                        tint = Color.Gray
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "등록된 내역이 없습니다.",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "하단 + 버튼을 눌러서 추가해주세요.",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
            } else {
                LazyColumn (
                    modifier = Modifier.padding(15.dp).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    items(expenseList) { expense ->
                        val formatter = DecimalFormat("#,###")
                        val formattedAmount = formatter.format(expense.amount)

                        Card (modifier = Modifier.fillMaxWidth()){
                            androidx.compose.foundation.layout.Row (
                                modifier = Modifier.fillMaxWidth().padding(10.dp, 7.dp, 10.dp, 7.dp)
                            ){
                                Column (modifier = Modifier.weight(1f)){
                                    Text(
                                        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                                        text = expense.title,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                                        text = "${formattedAmount}원",
                                        color = Color.Red
                                    )
                                }

                                IconButton(onClick = {
                                    itemToDelete = expense
//                                viewModel.deleteExpense(expense)
                                    showDialog = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "삭제",
                                        tint = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeContent(
//    extenseList: List<ExpenseEntity>,
//    totalAmount: Long,
//    onNavigateToAdd: () -> Unit,
//    onDeleteExpense: (ExpenseEntity) -> Unit
//) {
//    val
//}
//
//)


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