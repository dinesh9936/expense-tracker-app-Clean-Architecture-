package com.rama.expensetrackerapp.presentation.home
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rama.expensetrackerapp.domain.model.Expense
import com.rama.expensetrackerapp.domain.usecase.ExpenseUseCases
import com.rama.expensetrackerapp.utils.getMonthRangeFor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject
import kotlin.collections.emptyList

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: ExpenseUseCases
) : ViewModel() {

    private val currentDate = LocalDate.now()

    private val _selectedMonth = MutableStateFlow(currentDate.monthValue)
    val selectedMonth = _selectedMonth

    private val _selectedYear = MutableStateFlow(currentDate.year)
    val selectedYear = _selectedYear

    private fun getMonthRange(year: Int, month: Int): Pair<Long, Long> {
        return getMonthRangeFor(year, month) // I'll update your util next
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val monthlyExpenses = combine(_selectedMonth, _selectedYear) { month, year ->
        getMonthRange(year, month)
    }.flatMapLatest { (start, end) ->
        useCases.getExpensesByMonthUseCase(start, end)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val totalThisMonth = combine(_selectedMonth, _selectedYear) { month, year ->
        getMonthRange(year, month)
    }.flatMapLatest { (start, end) ->
        useCases.getMonthlyTotalUseCase(start, end)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0.0)

    fun nextMonth() {
        if (_selectedMonth.value == 12) {
            _selectedMonth.value = 1
            _selectedYear.value++
        } else {
            _selectedMonth.value++
        }
    }

    fun previousMonth() {
        if (_selectedMonth.value == 1) {
            _selectedMonth.value = 12
            _selectedYear.value--
        } else {
            _selectedMonth.value--
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            useCases.deleteExpenseUseCase(expense)
        }
    }

}
