package org.example.lab5

import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths

// При выполнении следующих заданий для вывода результатов создавать новую директорию и файл средствами класса File
// 9. Прочитать строки из файла и поменять местами первое и последнее слова в каждой строке.

// 10. Ввести из текстового файла, связанного с входным потоком, последовательность строк.
// Выбрать и сохранить m последних слов в каждой из последних n строк

fun main() {
    Files.deleteIfExists(Paths.get(FileName.ResultThird.name))
    Files.deleteIfExists(Paths.get(FileName.ResultFourth.name))
    doThirdTask()
    doFourthTask()
}

fun doThirdTask() {
    val text = mutableListOf<MutableList<String>>()
    Files.lines(Paths.get(FileName.Third.name)).use { stream ->
        stream.forEach { value ->
            text.add(
                value.split(" ").toMutableList()
            )
        }

        println(text)
    }

    val result = text.map {
        val first = it.first()
        val last = it.last()
        it[0] = last
        it[it.size - 1] = first
        it.joinToString(" ")
    }

    result.forEach {
        writeToFile(it, FileName.ResultThird)
    }
}

fun writeToFile(value: String, name: FileName) {
    FileWriter(name.name, true).use { out ->
        out.write(value + "\n")
    }
}

fun doFourthTask() {
    println("Enter n:")
    val n1 = readln().toIntOrNull() ?: 0
    println("Enter m:")
    val m1 = readln().toIntOrNull() ?: 0

    val text = mutableListOf<MutableList<String>>()
    Files.lines(Paths.get(FileName.Fourth.name)).use { stream ->
        stream.forEach { value ->
            text.add(
                value.split(" ").toMutableList()
            )
        }
    }

    val result = text.map {
        it.reversed().take(m1).reversed().joinToString(" ")
    }.reversed().take(n1)

    result.forEach {
        writeToFile(it, FileName.ResultFourth)
    }
}