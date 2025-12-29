package com.example.myapplication.data

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

// ViewModel과 DB 사이에 중재자
class ExpenseRepository (private val expenseDao: Dao) {
    val allExpenses: Flow<List<ExpenseEntity>> = expenseDao.getAllExpenses()

    // 지출 추가
    suspend fun insert(expense: ExpenseEntity) {
        expenseDao.insert(expense)
    }

    // 수정 추가
    suspend fun update(expense: ExpenseEntity) {
        expenseDao.update(expense)
    }

    // 삭제 추가
    suspend fun delete(expense: ExpenseEntity) {
        expenseDao.delete(expense)
    }
}