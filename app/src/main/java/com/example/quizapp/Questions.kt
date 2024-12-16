package com.example.quizapp


fun getQuestionsForCategory(categoryId: Int): List<Question> {
    return when (categoryId) {
        1 -> listOf(
            Question("What is 2 + 2?", listOf("3", "4", "5", "6"), "4"),
            Question("What is 3 + 5?", listOf("5", "8", "10", "6"), "8")

        )

        2 -> listOf(
            Question(
                "What is the boiling point of water?",
                listOf("90°C", "100°C", "110°C", "120°C"),
                "100°C"
            ),
            Question("What is the atomic number of Oxygen?", listOf("8", "16", "12", "2"), "8")
        )

        3 -> listOf(
            Question(
                "Who was the first president of the USA?",
                listOf("Abraham Lincoln", "George Washington", "Thomas Jefferson", "John Adams"),
                "George Washington"
            ),
            Question(
                "What year did World War 2 end?",
                listOf("1945", "1939", "1920", "1950"),
                "1945"
            )
        )

        else -> listOf()
    }
}
