package com.mathchine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mathchine.data.AppDatabase
import com.mathchine.data.PackManager
import com.mathchine.ui.HomeScreen
import com.mathchine.ui.PracticeScreen
import com.mathchine.viewmodel.PracticeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 极简入口：把打包好的数据直接预备入 Android
        PackManager.copyDatabaseFromAssetsIfNeeded(this)
        val database = AppDatabase.getDatabase(this)
        val dao = database.questionDao()

        setContent {
            // 使用 Jetpack Navigation 控制极简的两页面流转
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    // TODO: 正式版中从 database 获取，目前直接给几条假数据
                    val categories = listOf("四则运算", "线性代数", "微积分", "概率统计")
                    HomeScreen(
                        categories = categories,
                        onCategoryClick = { category ->
                            navController.navigate("practice/$category")
                        }
                    )
                }

                composable("practice/{category}") { backStackEntry ->
                    val category = backStackEntry.arguments?.getString("category") ?: "基础练习"
                    
                    // ViewModel 和 Database生命周期保持简单关联
                    val viewModel = remember { PracticeViewModel(dao) }
                    
                    // 加载属于该类别的题目
                    viewModel.loadNextQuestion(category)
                    
                    PracticeScreen(
                        category = category,
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
