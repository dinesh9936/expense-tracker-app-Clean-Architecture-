package com.rama.expensetrackerapp.data.repository.impl

import com.rama.expensetrackerapp.data.local.dao.ExpenseDao
import com.rama.expensetrackerapp.data.mappers.toDomain
import com.rama.expensetrackerapp.data.mappers.toEntity
import com.rama.expensetrackerapp.domain.model.Expense
import com.rama.expensetrackerapp.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseRepositoryImpl(
    private val dao: ExpenseDao
) : ExpenseRepository{

    override suspend fun addExpense(expense: Expense) {
        dao.insertExpense(expense.toEntity())
    }

    override suspend fun deleteExpense(expense: Expense) {
        dao.deleteExpense(expense.toEntity())
    }

    override fun getExpensesByMonth(start: Long, end: Long): Flow<List<Expense>> {
        return dao.getExpensesByMonth(start, end).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getMonthlyTotal(start: Long, end: Long): Flow<Double> {
        return dao.getMonthlyTotal(start, end).map { it ?: 0.0 }
    }
}