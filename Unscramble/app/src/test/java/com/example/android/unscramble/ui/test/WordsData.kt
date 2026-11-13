package com.example.android.unscramble.ui.test

import com.example.android.unscramble.data.allWords

private val unscrambledWordsByLetters: Map<String, String> = allWords.associateBy(
    keySelector = { lettersOf(it) },
    valueTransform = { it }
)

private fun lettersOf(word: String): String = word.toCharArray().sorted().joinToString("")

fun getUnscrambledWord(scrambledWord: String): String =
    unscrambledWordsByLetters[lettersOf(scrambledWord)] ?: ""
