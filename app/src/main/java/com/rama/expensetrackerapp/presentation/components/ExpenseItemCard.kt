package com.rama.expensetrackerapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rama.expensetrackerapp.domain.model.Expense
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun ExpenseItemCard(
    expense: Expense,
    onItemClick: (Expense) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(
                enabled = true,
                onClick = { onItemClick(expense) }
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column{
            Text(expense.title, style = MaterialTheme.typography.bodyLarge)
            Text(
                SimpleDateFormat("dd MMM yyyy").format(Date(expense.date)),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text("₹ ${expense.amount}")
    }
}
