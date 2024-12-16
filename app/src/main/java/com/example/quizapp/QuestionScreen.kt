package com.example.quizapp

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@SuppressLint("DefaultLocale")
@Composable
fun QuestionScreen(
    categoryId: Int,
    questions: List<Question>,
    onQuizFinished: (score: Int, totalQuestions: Int) -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }


    var timeLeftInSeconds by remember { mutableStateOf(2 * 60) }


    LaunchedEffect(key1 = timeLeftInSeconds) {
        if (timeLeftInSeconds > 0) {
            kotlinx.coroutines.delay(1000L)
            timeLeftInSeconds--
        } else {

            onQuizFinished(score, questions.size)
        }
    }

    val minutes = timeLeftInSeconds / 60
    val seconds = timeLeftInSeconds % 60
    val currentQuestion = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = String.format("Time Left: %02d:%02d", minutes, seconds),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Question ${currentQuestionIndex + 1}: ${currentQuestion.text}",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))


        currentQuestion.answers.forEach { answer ->
            Button(
                onClick = {
                    selectedAnswer = answer
                    if (answer == currentQuestion.correctAnswer) {
                        score += 1
                    }

                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex += 1
                    } else {
                        onQuizFinished(score, questions.size)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = answer)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Score: $score", style = MaterialTheme.typography.bodyMedium)
    }
}

data class Question(
    val text: String,
    val answers: List<String>,
    val correctAnswer: String
)


