package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.ExpenseEntity
import com.example.myapplication.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// UI 상태 관리 및 로직
class ExpenseViewModel (private val repository: ExpenseRepository) : ViewModel() {
    val allExpenses: StateFlow<List<ExpenseEntity>> = repository.allExpenses
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addExpense(title: String, amount: String) {
        viewModelScope.launch {
            val entity = ExpenseEntity(
                title = title,
                amount = amount.toLongOrNull() ?: 0L,
                catergory = "기타",
                data = System.currentTimeMillis()
            )
            repository.insert(entity)
        }
    }
}