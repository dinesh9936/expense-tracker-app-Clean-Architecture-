package com.rama.expensetrackerapp.di

import com.rama.expensetrackerapp.domain.repository.ExpenseRepository
import com.rama.expensetrackerapp.domain.usecase.AddExpenseUseCase
import com.rama.expensetrackerapp.domain.usecase.DeleteExpenseUseCase
import com.rama.expensetrackerapp.domain.usecase.ExpenseUseCases
import com.rama.expensetrackerapp.domain.usecase.GetExpensesByMonthUseCase
import com.rama.expensetrackerapp.domain.usecase.GetMonthlyTotalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideExpenseUseCases(
        repository: ExpenseRepository
    ): ExpenseUseCases {
        return ExpenseUseCases(
            addExpenseUseCase = AddExpenseUseCase(repository),
            deleteExpenseUseCase = DeleteExpenseUseCase(repository),
            getExpensesByMonthUseCase = GetExpensesByMonthUseCase(repository),
            getMonthlyTotalUseCase = GetMonthlyTotalUseCase(repository)
        )
    }
}