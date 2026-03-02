package com.mathchine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuestionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // 此处应该配合 PackManager 先从 Assets 或 Download 目录拷贝出 .db 文件，
                // 然后再用 createFromAsset 或 createFromFile 初始化 Room。
                // 暂时写为标准构建供参考
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "math_pack.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
