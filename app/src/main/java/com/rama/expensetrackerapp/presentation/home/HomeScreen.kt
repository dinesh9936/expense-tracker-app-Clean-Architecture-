package com.rama.expensetrackerapp.presentation.home


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rama.expensetrackerapp.R
import com.rama.expensetrackerapp.domain.model.Expense
import com.rama.expensetrackerapp.presentation.components.ExpenseItemCard
import com.rama.expensetrackerapp.ui.theme.ExpenseTrackerAppTheme
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onAddClick: () -> Unit,
    onInsightsClick: () -> Unit,
    onExpenseItemClick: (Expense) -> Unit
) {
    val expenses = viewModel.monthlyExpenses.collectAsState(initial = emptyList())
    val total = viewModel.totalThisMonth.collectAsState(initial = 0.0)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Expense Tracker") },
                actions = {
                    IconButton(onClick = onInsightsClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_insight_icon),
                            contentDescription = "Insights"
                        )
                    }
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Expense")
            }
        }
    ) { padding ->

        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)) {

            // Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("This Month's Spending")
                    Text(
                        text = "₹ ${total.value}",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.previousMonth() }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Previous")
                }

                val month by viewModel.selectedMonth.collectAsState()
                val year by viewModel.selectedYear.collectAsState()


                val monthName = Month.of(month)
                    .getDisplayName(TextStyle.FULL, Locale.getDefault())

                Text(
                    "$monthName $year",
                    style = MaterialTheme.typography.titleMedium
                )



                IconButton(onClick = { viewModel.nextMonth() }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next")
                }
            }

            Spacer(Modifier.height(16.dp))

            Text("Recent Expenses", style = MaterialTheme.typography.titleMedium)

            LazyColumn {
                items(expenses.value) { expense ->
                    ExpenseItemCard(
                        expense,
                        onItemClick = {expense ->
                            onExpenseItemClick.invoke(expense)
                        }
                    )
                }
            }
        }
    }
}

