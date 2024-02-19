package org.example.lab2

// Ввести с консоли n – размерность матрицы a[n][n]. Задать значения элементов матрицы
// в интервале значений от -n до n с помощью датчика случайных чисел.
// 9. Построить матрицу, вычитая из элементов каждой строки матрицы ее среднее арифметическое.

fun main() {
    print("Enter n: ")
    val n = readln().toIntOrNull() ?: 0
    val matrix = List(n) {
        List(n) {
            (-n..n).random()
        }
    }

    matrix.printMatrix()

    matrix.forEachIndexed { index, row ->
        row.forEach { value ->
            print("${value - matrix[index].sum() / n} ")
        }
        println()
    }
}

fun List<List<Int>>.printMatrix() {
    this.forEach {
        it.forEach { value ->
            print("$value ")
        }
        println()
    }
    println()
}