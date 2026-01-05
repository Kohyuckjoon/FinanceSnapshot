package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.ExpenseEntity
import com.example.myapplication.data.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.jvm.java

// UI 상태 관리 및 로직
//class ExpenseViewModel (private val repository: ExpenseRepository) : ViewModel() {
//    val allExpenses: StateFlow<List<ExpenseEntity>> = repository.allExpenses
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
//
//    fun addExpense(title: String, amount: String) {
//        viewModelScope.launch {
//            val entity = ExpenseEntity(
//                title = title,
//                amount = amount.toLongOrNull() ?: 0L,
//                catergory = "기타",
//                data = System.currentTimeMillis()
//            )
//            repository.insert(entity)
//        }
//    }
//}

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "expense-db"
    ).build()

    private val expenseDao = db.expenseDao()

    // 전체 리스트를 가져오는 Flow (데이터 변경시에만 자동 업데이트)
    val allExpenses: Flow<List<ExpenseEntity>> = expenseDao.getAllExpenses()

    // 데이터 저장 함수
    fun addExpense(title: String, amount: Long) {
        viewModelScope.launch {
            val newExpense = ExpenseEntity(
                title = title,
                amount = amount,
                catergory = "지출", // 기본 값
                data = System.currentTimeMillis() // 현재 시간
            )
            expenseDao.insert(newExpense)
        }
    }
}