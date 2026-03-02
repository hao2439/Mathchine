package com.mathchine.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 数据库表对应的实体类：对应了我们 Python 脚本生成的数据包结构
 */
@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey
    val uuid: String,
    val category: String,
    val question: String,
    val options: String, // 我们在数据库里存储为 JSON 字符串，例如 '["A", "B"]'
    val correct_option_index: Int,
    val explanation: String
)
