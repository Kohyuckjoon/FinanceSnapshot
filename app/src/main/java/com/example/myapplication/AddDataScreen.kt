package com.example.myapplication

import android.R.id.title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButtonDefaults.colors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class AddDataScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AddDataScreen(onBack = {finish()})
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDataScreen(onBack: () -> Unit) {
    var assetType by remember { mutableStateOf("") }
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.surfaceVariant,
                ),

                title = {
                    Text("데이터 추가", color = Color.Black)
                },

                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "뒤로가기",
                        )
                    }
                }
            )
        },

        bottomBar = {
            ElevatedButton(
                onClick = { onBack() },
                modifier = Modifier.fillMaxWidth().padding(16.dp, 16.dp,16.dp, 50.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF00668C)
                )
            ) {
                Text("저장하기")
            }
        }
    ){ innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)

        ){
            Text("자산 유형 선택 : ", modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 5.dp), fontSize = 15.sp)
            OutlinedTextField(
                value = assetType,
                onValueChange = { assetType = it }, // 글자를 칠 때마다 변수에 저장
                modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 10.dp).fillMaxWidth()
            )

            Text("금액 : ", modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 10.dp), fontSize = 15.sp)
            OutlinedTextField(
                value = assetType,
                onValueChange = { assetType = it }, // 글자를 칠 때마다 변수에 저장
                modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 5.dp).fillMaxWidth()
            )

            Text("메모 / 상세 내용 : ", modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 10.dp), fontSize = 15.sp)
            OutlinedTextField(
                value = assetType,
                onValueChange = { assetType = it }, // 글자를 칠 때마다 변수에 저장
                modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 5.dp).fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyApplicationTheme {
        AddDataScreen() {}
    }
}