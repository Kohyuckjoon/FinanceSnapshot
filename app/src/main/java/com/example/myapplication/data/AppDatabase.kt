package com.example.myapplication.data

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.ExpenseEntity

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val amount: Long,
    val catergory: String,
    val data: Long
)

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() { // 괄호() 삭제하고 : RoomDatabase() 추가
    abstract fun expenseDao(): ExpenseDao
}