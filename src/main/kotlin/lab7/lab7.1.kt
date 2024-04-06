package org.example.lab7

// 9. Из текста удалить все слова заданной длины, начинающиеся на согласную букву.
// 10. Удалить из текста его часть, заключенную между двумя символами, которые вводятся (например,
// между скобками ‘(’ и ‘)’ или между звездочками ‘*’ и т.п.).

fun main() {
    firstTask()
    secondTask()
}

fun firstTask() {
    val n = readln().toIntOrNull() ?: 1
    val result = readln().split(" ").filter {
        it.length != n || listOf(
            "a",
            "o",
            "e",
            "i",
            "u",
            "y"
        ).contains(it.firstOrNull()?.lowercase())
    }.joinToString(" ")

    println(result)
}

fun secondTask() {
    val separator = readln()
    val result = readln().split(separator.firstOrNull()?.toString() ?: "(", separator.getOrNull(1)?.toString() ?: ")").toMutableList().apply {
        removeAt(1)
    }.joinToString("")
    println(result)
}

fun String.removeIfNotLetter(): String = this.map {
    if (it.isLetter() || it == '-' || it == '\'') {
        it
    } else {
        ' '
    }
}.joinToString("").replace(" ", "")