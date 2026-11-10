package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geoquiz.ui.theme.GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizTheme {
                GeoQuizScreen()
            }
        }
    }
}


@Composable
fun GeoQuizScreen() {

    data class QuizQuestion(
        val text: String,
        val answer: Boolean
    )

    val questions = listOf(
        QuizQuestion("Canberra is the capital of Australia.", true),
        QuizQuestion("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        QuizQuestion("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        QuizQuestion("The source of the Nile River is in Egypt.", false),
        QuizQuestion("The Amazon River is the longest river in the Americas.", true),
        QuizQuestion("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
    )

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var hasAnswered by remember { mutableStateOf(false) }
    var answerMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "GeoQuiz",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Вопрос ${currentQuestionIndex + 1} из ${questions.size}",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = questions[currentQuestionIndex].text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(35.dp))

        if (!hasAnswered) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        hasAnswered = true
                        answerMessage = if (questions[currentQuestionIndex].answer) {
                            "Правильно!"
                        } else {
                            "Неправильно!"
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !hasAnswered
                ) {
                    Text("True")
                }

                Button(
                    onClick = {
                        hasAnswered = true
                        answerMessage = if (!questions[currentQuestionIndex].answer) {
                            "Правильно!"
                        } else {
                            "Неправильно!"
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !hasAnswered
                ) {
                    Text("False")
                }
            }
        }
        if (hasAnswered) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = answerMessage,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (currentQuestionIndex < questions.lastIndex) {
                    currentQuestionIndex++
                    hasAnswered = false
                    answerMessage = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = currentQuestionIndex < questions.lastIndex
        ) {
            Text("Next")
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Правильных ответов: 0",
            fontSize = 16.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GeoQuizPreview() {
    GeoQuizTheme {
        GeoQuizScreen()
    }
}