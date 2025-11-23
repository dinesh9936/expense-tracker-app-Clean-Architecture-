package com.rama.expensetrackerapp.presentation.insights

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rama.expensetrackerapp.domain.usecase.ExpenseUseCases
import com.rama.expensetrackerapp.utils.getCurrentMonthRange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class InsightsViewModel @Inject constructor(
    useCases: ExpenseUseCases
) : ViewModel() {

    private val currentMonthRange = getCurrentMonthRange()
    private val start = currentMonthRange.first
    private val end = currentMonthRange.second


    val expenses = useCases.getExpensesByMonthUseCase(start, end)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    val totalAmount = expenses.map { list ->
        list.sumOf { it.amount }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0.0)

    val categoryBreakdown = expenses.map { list ->
        list.groupBy { it.category }
            .mapValues { entry -> entry.value.sumOf { it.amount } }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())
}
