package org.example.lab6

import java.nio.file.Files
import java.nio.file.Paths

// 9. Задан файл с текстом на английском языке. Выделить все различные слова.
// Слова, отличающиеся только регистром букв, считать одинаковыми. Использовать класс HashSet.

fun main() {
    doFirstTask()
}

fun doFirstTask() {
    val result = Files.lines(Paths.get(FileName.First.name)).use { stream ->
        stream.map { value ->
                value.split(" ").map { it.lowercase() }
        }.toList().flatten()
    }.toHashSet().sorted()

    println(result)

}

enum class FileName {
    First,
}
