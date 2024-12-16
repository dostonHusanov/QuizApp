package com.example.quizapp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(score: Int, totalQuestions: Int, onRestart: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8)) // Soft gray background
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.85f)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = "Quiz Completed!",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4CAF50) // Success Green
                    ),
                    textAlign = TextAlign.Center
                )

                // Score Display
                Text(
                    text = "Your Score:",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.Gray
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "$score / $totalQuestions",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (score > totalQuestions / 2) Color(0xFF4CAF50) else Color(0xFFE53935) // Green or Red
                    ),
                    textAlign = TextAlign.Center
                )

                // Feedback Message
                Text(
                    text = if (score > totalQuestions / 2) "Great Job!" else "Better Luck Next Time!",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF3F51B5) // Accent Blue
                    ),
                    textAlign = TextAlign.Center
                )

                // Restart Button
                Button(
                    onClick = { onRestart() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)) // Blue button
                ) {
                    Text(
                        text = "Restart Quiz",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

