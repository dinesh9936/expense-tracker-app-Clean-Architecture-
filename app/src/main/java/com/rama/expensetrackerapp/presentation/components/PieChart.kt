package com.rama.expensetrackerapp.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
    data: Map<String, Double>,
    modifier: Modifier = Modifier.size(220.dp)
) {
    val total = data.values.sum()
    var startAngle = -90f

    val colors = listOf(
        Color(0xFFEF5350),
        Color(0xFFAB47BC),
        Color(0xFF5C6BC0),
        Color(0xFF29B6F6),
        Color(0xFF66BB6A),
        Color(0xFFFFCA28)
    )

    Canvas(modifier = modifier) {
        val radius = size.minDimension
        val chartSize = Size(radius, radius)

        data.entries.forEachIndexed { index, entry ->
            val sweep = (entry.value / total * 360f).toFloat()
            drawArc(
                color = colors[index % colors.size],
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = true,
                size = chartSize
            )
            startAngle += sweep
        }
    }
}
