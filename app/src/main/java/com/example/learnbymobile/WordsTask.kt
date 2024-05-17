package com.example.learnbymobile

data class WordsTask (
    val word: String,
    val pronunciation: String,
    val variants: List<String>,
    val correct: String
)