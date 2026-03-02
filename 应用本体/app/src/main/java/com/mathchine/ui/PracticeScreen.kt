package com.mathchine.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mathchine.ui.components.LatexView
import com.mathchine.viewmodel.PracticeViewModel
import org.json.JSONArray

@Composable
fun PracticeScreen(
    category: String,
    viewModel: PracticeViewModel,
    onBack: () -> Unit
) {
    val question by viewModel.currentQuestion.collectAsState()
    val feedback by viewModel.feedback.collectAsState()
    val selectedIndex by viewModel.selectedOptionIndex.collectAsState()

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("$category 练习") },
                navigationIcon = {
                    Button(onClick = onBack) { Text("返回") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (question == null) {
                CircularProgressIndicator()
            } else {
                val q = question!!
                
                // 1. 题干区，直接交给 LaTeX 组件渲染
                Card(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(8.dp)
                ) {
                    LatexView(latexText = q.question, modifier = Modifier.padding(16.dp))
                }

                // 2. 选项区
                val optionsArray = try { JSONArray(q.options) } catch (e: Exception) { JSONArray() }
                for (i in 0 until optionsArray.length()) {
                    val optString = optionsArray.getString(i)
                    val isSelected = selectedIndex == i
                    val buttonColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant

                    Button(
                        onClick = { viewModel.submitAnswer(i) },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                    ) {
                        LatexView(latexText = optString) // 选项也支持 LaTeX 解析
                    }
                }

                // 3. 结果与反馈区
                if (feedback != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = feedback!!, 
                        color = if (selectedIndex == q.correct_option_index) Color.Green else Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.loadNextQuestion(category) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("下一题") // 極简的继续推进
                    }
                }
            }
        }
    }
}
