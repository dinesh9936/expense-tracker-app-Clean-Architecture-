package com.rama.expensetrackerapp.presentation.addexpense

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rama.expensetrackerapp.domain.model.Expense
import com.rama.expensetrackerapp.domain.usecase.ExpenseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val useCases: ExpenseUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val id = savedStateHandle.get<Int>("id") ?: -1

    var title = mutableStateOf("")
    var amount = mutableStateOf("")
    var category = mutableStateOf("")
    var date = mutableStateOf(LocalDate.now())

    init {
        if (id != -1) {   // EDIT MODE
            viewModelScope.launch {
                val expense = useCases.getExpenseByIdUseCase(id)
                expense?.let {
                    title.value = it.title
                    amount.value = it.amount.toString()
                    category.value = it.category

                    date.value = Instant.ofEpochMilli(it.date)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                }
            }
        }
    }

    fun save(onDone: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val expense = Expense(
                    id = if (id == -1) 0 else id,
                    title = title.value,
                    amount = amount.value.toDouble(),
                    category = category.value,
                    date = date.value.atStartOfDay(ZoneId.systemDefault())
                        .toEpochSecond() * 1000
                )

                if (id == -1) {
                    useCases.addExpenseUseCase(expense)
                } else {
                    useCases.updateExpenseUseCase(expense)
                }
            }

            // Back on Main Thread → safe
            onDone()
        }
    }

    fun delete(onDone: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCases.deleteExpenseUseCase(id)
            }

            // Back on main → safe navigation
            onDone()
        }
    }
}
