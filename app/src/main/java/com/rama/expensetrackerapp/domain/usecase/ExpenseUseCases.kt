package com.rama.expensetrackerapp.domain.usecase

data class ExpenseUseCases (
    val updateExpenseUseCase: UpdateExpenseUseCase,
    val getExpenseByIdUseCase: GetExpenseByIdUseCase,
    val addExpenseUseCase: AddExpenseUseCase,
    val deleteExpenseUseCase: DeleteExpenseUseCase,
    val getMonthlyTotalUseCase: GetMonthlyTotalUseCase,
    val getExpensesByMonthUseCase: GetExpensesByMonthUseCase,
)