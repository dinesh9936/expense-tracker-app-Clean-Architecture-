package com.rama.expensetrackerapp.domain.usecase

import com.rama.expensetrackerapp.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class GetMonthlyTotalUseCase(
    private val repository: ExpenseRepository
) {

     operator fun invoke(start: Long, end: Long): Flow<Double> {
        return repository.getMonthlyTotal(start, end)
    }
}