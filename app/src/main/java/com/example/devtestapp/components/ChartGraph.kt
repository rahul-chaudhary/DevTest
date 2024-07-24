package com.example.devtestapp.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun Chart() {
    // Sample data for the chart
    val entries = listOf(
        Entry(0f, 4f),
        Entry(1f, 8f),
        Entry(2f, 6f),
        Entry(3f, 2f),
        Entry(4f, 18f),
        Entry(5f, 9f)
    )

    val dataSet = LineDataSet(entries, "Sample Data").apply {
        color = android.graphics.Color.BLUE
        valueTextColor = android.graphics.Color.BLACK
    }

    val lineData = LineData(dataSet)

    // AndroidView to host the MPAndroidChart LineChart
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                this.data = lineData
                description.text = "Sample Line Chart"
                invalidate() // Refresh the chart
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}