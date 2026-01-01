package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    // Dao의 함수를 호출하여 데이터를 가져옵니다.
    val allExpenses: Flow<List<ExpenseEntity>> = expenseDao.getAllExpenses()

    suspend fun insert(expense: ExpenseEntity) {
        expenseDao.insert(expense)
    }

    suspend fun update(expense: ExpenseEntity) {
        expenseDao.update(expense)
    }

    suspend fun delete(expense: ExpenseEntity) {
        expenseDao.delete(expense)
    }
}