package com.rama.expensetrackerapp.domain.model

data class Expense(
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Long // timestamp
)