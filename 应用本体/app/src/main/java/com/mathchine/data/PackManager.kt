package com.mathchine.data

import android.content.Context
import java.io.File
import java.io.FileOutputStream

/**
 * 数据包管理器，负责在应用首次启动或需要更新时，
 * 将制作好并下载的 SQLite 数据包拷贝到本应用的 database 目录下供 Room 使用。
 */
object PackManager {
    fun copyDatabaseFromAssetsIfNeeded(context: Context, dbName: String = "math_pack.db") {
        val dbFile = context.getDatabasePath(dbName)
        if (!dbFile.exists()) {
            dbFile.parentFile?.mkdirs()
            context.assets.open(dbName).use { inputStream ->
                FileOutputStream(dbFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
    }
}
