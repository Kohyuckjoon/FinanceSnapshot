package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// 쿼리 정의(@Dao)
@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String, // 내용
    val amount: Long, // 금액
    val catergory: String, // 식비, 교통 등
    val data: Long // 저장시간
)