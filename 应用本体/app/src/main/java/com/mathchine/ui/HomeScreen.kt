package com.mathchine.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 主页界面，专注于“让用户选择他们最想练的题库类别”
 */
@Composable
fun HomeScreen(
    categories: List<String>, 
    onCategoryClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(title = { Text("Mathchine - 选择练习科目") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(categories) { category ->
                Button(
                    onClick = { onCategoryClick(category) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(60.dp) // 大尺寸按钮，极其明显
                ) {
                    Text(text = category, style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}
