package com.rama.expensetrackerapp.domain.usecase

import com.rama.expensetrackerapp.domain.model.Expense
import com.rama.expensetrackerapp.domain.repository.ExpenseRepository

class UpdateExpenseUseCase(
    private val repository: ExpenseRepository
) {
    suspend operator fun invoke(expense: Expense) {
        repository.updateExpense(expense)
    }
}
