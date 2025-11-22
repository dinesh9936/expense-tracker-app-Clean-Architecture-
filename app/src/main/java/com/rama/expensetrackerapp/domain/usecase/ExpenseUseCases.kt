package com.rama.expensetrackerapp.domain.usecase

data class ExpenseUseCases (
    val addExpenseUseCase: AddExpenseUseCase,
    val deleteExpenseUseCase: DeleteExpenseUseCase,
    val getMonthlyTotalUseCase: GetMonthlyTotalUseCase,
    val getExpensesByMonthUseCase: GetExpensesByMonthUseCase,
)