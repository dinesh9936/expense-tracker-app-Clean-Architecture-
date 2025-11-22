package com.rama.expensetrackerapp.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.rama.expensetrackerapp.domain.usecase.ExpenseUseCases
import com.rama.expensetrackerapp.utils.getCurrentMonthRange
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: ExpenseUseCases
): ViewModel(){

    private val currentMonthRange = getCurrentMonthRange()

    val monthlyExpenses = useCases.getExpensesByMonthUseCase(currentMonthRange.first, currentMonthRange.second)
    val totalThisMonth = useCases.getMonthlyTotalUseCase(currentMonthRange.first, currentMonthRange.second)
}