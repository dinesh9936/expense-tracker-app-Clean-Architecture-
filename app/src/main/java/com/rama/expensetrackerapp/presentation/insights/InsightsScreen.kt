package com.rama.expensetrackerapp.presentation.insights

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rama.expensetrackerapp.presentation.components.CategoryStatItem
import com.rama.expensetrackerapp.presentation.components.PieChart

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsScreen(
    onBack: () -> Unit,
    viewModel: InsightsViewModel = hiltViewModel()
) {
    val total by viewModel.totalAmount.collectAsState()
    val categoryStats by viewModel.categoryBreakdown.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Insights") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Total Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation()
            ) {
                Column(
                    Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total Spent This Month")
                    Text("₹ $total", style = MaterialTheme.typography.headlineMedium)
                }
            }

            Spacer(Modifier.height(20.dp))

            // Pie Chart
            if (categoryStats.isNotEmpty()) {
                PieChart(data = categoryStats)
            } else {
                Text("No data yet")
            }

            Spacer(Modifier.height(20.dp))

            // Category breakdown list
            val colors = listOf(
                Color(0xFFEF5350),
                Color(0xFFAB47BC),
                Color(0xFF5C6BC0),
                Color(0xFF29B6F6),
                Color(0xFF66BB6A),
                Color(0xFFFFCA28)
            )

            categoryStats.entries.toList().forEachIndexed { index, entry ->
                CategoryStatItem(
                    category = entry.key,
                    amount = entry.value,
                    color = colors[index % colors.size]   // ← correct
                )
            }


        }
    }
}
