package com.mathchine.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionDao {
    
    // 核心语句：从指定类别中随机抽取一道题
    @Query("SELECT * FROM questions WHERE category = :category ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomQuestion(category: String): QuestionEntity?

    // 获取当前库中所有的可用分类
    @Query("SELECT DISTINCT category FROM questions")
    suspend fun getAvailableCategories(): List<String>
    
    // 获取某一分类下的总题数（选做，用于进度统计）
    @Query("SELECT COUNT(*) FROM questions WHERE category = :category")
    suspend fun getQuestionCount(category: String): Int
}
