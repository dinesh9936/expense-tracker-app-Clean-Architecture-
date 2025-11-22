package com.rama.expensetrackerapp.data.mappers

import com.rama.expensetrackerapp.data.local.entity.ExpenseEntity
import com.rama.expensetrackerapp.domain.model.Expense

fun ExpenseEntity.toDomain(): Expense {
    return Expense(
        id = this.id,
        title = this.title,
        amount = this.amount,
        category = this.category,
        date = this.date
    )
}


fun Expense.toEntity(): ExpenseEntity {
    return ExpenseEntity(
        id = this.id,
        title = this.title,
        amount = this.amount,
        category = this.category,
        date = this.date
    )
}