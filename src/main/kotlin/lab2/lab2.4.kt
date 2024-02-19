package org.example.lab2

// Ввести с консоли n – размерность матрицы a[n][n]. Задать значения элементов матрицы
// в интервале значений от -n до n с помощью датчика случайных чисел.
// 10. Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие

fun main() {
    print("Enter n: ")
    val n = readln().toIntOrNull() ?: 0
    val matrix = MutableList(n) {
        MutableList(n) {
            (-n..n).random()
        }
    }

    matrix.printMatrix()

    val max = matrix.maxOf {
        it.max()
    }
    println(max)
    println()

    val pair = mutableListOf<Pair<Int, Int>>()

    matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
            if (value == max) {
                pair.add(Pair(i, j))
            }
        }
    }

    pair.map { it.first }.sorted().distinct().reversed().forEach { i ->
        matrix.removeAt(i)
    }

    pair.map { it.second }.sorted().distinct().reversed().forEach { j ->
        matrix.forEach {
            it.removeAt(j)
        }
    }

    matrix.printMatrix()
}