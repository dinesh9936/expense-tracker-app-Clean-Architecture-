package com.rama.expensetrackerapp.domain.usecase

import com.rama.expensetrackerapp.domain.model.Expense
import com.rama.expensetrackerapp.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class GetExpensesByMonthUseCase(
    private val repository: ExpenseRepository
) {

        operator fun invoke(start: Long, end: Long): Flow<List<Expense>> {
            return repository.getExpensesByMonth(start, end)
        }

}