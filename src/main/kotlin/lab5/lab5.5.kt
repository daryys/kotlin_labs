package org.example.lab5

import java.nio.file.Files
import java.nio.file.Paths

// В следующих заданиях требуется ввести последовательность строк из текстового потока и выполнить указанные действия.
// При этом могут рассматриваться два варианта: каждая строка состоит из одного слова; каждая строка состоит из нескольких слов.
// Имена входного и выходного файлов, а также абсолютный путь к ним могут быть введены как параметры командной строки
// или храниться в файле.

// 9. Входной файл содержит совокупность строк. Строка файла содержит строку квадратной матрицы.
// Ввести матрицу в двумерный массив (размер матрицы найти). Вывести исходную матрицу и результат ее транспонирования.

// 10. Входной файл хранит квадратную матрицу по принципу: строка представляет собой число. Определить размерность.
// Построить 2-мерный массив, содержащий матрицу. Вывести исходную матрицу и результат ее поворота на 90 градусов по
// часовой стрелке.

fun main() {
    doFirstTask()
    doSecondTask()
}

fun doFirstTask() {
    val matrix = mutableListOf<List<Int>>()
    Files.lines(Paths.get(FileName.First.name)).use { stream ->
        stream.forEach { value ->
            matrix.add(
                value.split(" ").map { it.toIntOrNull() ?: 0 }
            )
        }
        println("Matrix size: ${matrix.size}")
        println(matrix)
    }

    val transposedMatrix = matrix.mapIndexed { i, row ->
        List(row.size) { j ->
            matrix[j][i]
        }
    }
    println(transposedMatrix)
}

fun doSecondTask() {
    val matrix = Files.lines(Paths.get(FileName.Second.name)).use { stream ->
        stream.map { value ->
            value.map { it.toString().toIntOrNull() ?: 0 }
        }.toList()
    }

        println("Matrix size: ${matrix.size}")
        println(matrix)
        val n = matrix.size

        val turnedMatrix = matrix.mapIndexed { i, row ->
            List(row.size) { j ->
                matrix[n - j - 1][i]
            }
        }
        println(turnedMatrix)
}

enum class FileName {
    First,
    Second,
    Third,
    Fourth,
    ResultThird,
    ResultFourth,
}