package com.mathchine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathchine.data.QuestionDao
import com.mathchine.data.QuestionEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * 集中管理用户在某一个分类下的练习状态
 */
class PracticeViewModel(private val dao: QuestionDao) : ViewModel() {

    // 当前正在作答的题目
    private val _currentQuestion = MutableStateFlow<QuestionEntity?>(null)
    val currentQuestion: StateFlow<QuestionEntity?> = _currentQuestion

    // 对错反馈信息及解析状态
    private val _feedback = MutableStateFlow<String?>(null)
    val feedback: StateFlow<String?> = _feedback

    // 已选择的答案游标，-1 代表未选择
    private val _selectedOptionIndex = MutableStateFlow<Int>(-1)
    val selectedOptionIndex: StateFlow<Int> = _selectedOptionIndex

    fun loadNextQuestion(category: String) {
        viewModelScope.launch {
            _feedback.value = null
            _selectedOptionIndex.value = -1
            // 从离线题库中随机抽取一道该类型的题
            _currentQuestion.value = dao.getRandomQuestion(category)
        }
    }

    fun submitAnswer(index: Int) {
        if (_selectedOptionIndex.value != -1) return // 防重复点击
        
        _selectedOptionIndex.value = index
        val question = _currentQuestion.value ?: return

        if (index == question.correct_option_index) {
            _feedback.value = "回答正确！"
        } else {
            _feedback.value = "错误！正确答案是选项 ${question.correct_option_index + 1}\n\n" +
                              "解析: ${question.explanation}"
        }
    }
}
