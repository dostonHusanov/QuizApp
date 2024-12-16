package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.QuizAppTheme


@Composable
fun QuizApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category") {
        composable("category") {
            CategoryScreen(onCategorySelected = { categoryId ->
                navController.navigate("question/$categoryId")
            })
        }
        composable("question/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toInt() ?: 1
            QuestionScreen(
                categoryId = categoryId,
                onQuizFinished = { score, totalQuestions ->
                    navController.navigate("result/$score/$totalQuestions")
                },
                questions = getQuestionsForCategory(categoryId),

                )
        }
        composable("result/{score}/{totalQuestions}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
            val totalQuestions = backStackEntry.arguments?.getString("totalQuestions")?.toInt() ?: 0
            ResultScreen(score = score, totalQuestions = totalQuestions, onRestart = {
                navController.popBackStack("category", false)
            })
        }
    }
}