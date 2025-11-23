package com.rama.expensetrackerapp.domain.usecase

import com.rama.expensetrackerapp.domain.model.Expense
import com.rama.expensetrackerapp.domain.repository.ExpenseRepository

class GetExpenseByIdUseCase(
    private val repository: ExpenseRepository
) {

    suspend operator fun invoke(id: Int): Expense? {
        return repository.getExpenseById(id)
    }
}