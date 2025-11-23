package com.rama.expensetrackerapp.domain.repository

import com.rama.expensetrackerapp.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun addExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun deleteExpense(id: Int)

    suspend fun getExpenseById(id: Int): Expense?

    fun getExpensesByMonth(start: Long, end: Long): Flow<List<Expense>>
    fun getMonthlyTotal(start: Long, end: Long): Flow<Double>
}