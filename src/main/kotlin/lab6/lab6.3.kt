package org.example.lab6

import java.util.Stack

// 9. Дана матрица из целых чисел. Найти в ней прямоугольную подматрицу, состоящую из максимального
// количества одинаковых элементов. Использовать класс Stack.

fun main() {
    val matrix = List(10) {
        List(10) {
            (0..1).random()
        }
    }

    matrix.forEach { row ->
        row.forEach {
            print("$it ")
        }
        println()
    }

    val result = fourth(matrix)
    println(result)

}

fun fourth(matrix: List<List<Int>>) = if (matrix.size > 1 && matrix[0].size > 1) {
    val maxStack = Stack<Pair<Int, Int>>()
    for (i in (0..matrix.size - 2)) {
        for (j in (0..matrix[0].size - 2)) {
            val result = getMaxSubMatrix(matrix, Pair(i, j))
            if (result.size > maxStack.size) {
                maxStack.clear()
                result.forEach {
                    maxStack.push(it)
                }
            }
        }
    }
    maxStack
} else {
    Stack<Pair<Int, Int>>()
}

fun getMaxSubMatrix(matrix: List<List<Int>>, element: Pair<Int, Int>): Stack<Pair<Int, Int>> {
    val maxStack = Stack<Pair<Int, Int>>()
    for (i in (element.first + 1..<matrix.size)) {
        for (j in (element.second + 1..<matrix.size)) {
            val result = checkSubMatrix(element, Pair(i, j), matrix)
            if (result.size > maxStack.size) {
                maxStack.clear()
                result.forEach {
                    maxStack.push(it)
                }
            }
        }
    }
    return maxStack
}

fun checkSubMatrix(first: Pair<Int, Int>, second: Pair<Int, Int>, matrix: List<List<Int>>): Stack<Pair<Int, Int>> {
    val result = Stack<Pair<Int, Int>>()
    for (i in (first.first..second.first)) {
        for (j in (first.second..second.second)) {
            if (matrix[first.first][first.second] != matrix[i][j]) {
                result.clear()
                return result
            } else {
                result.push(Pair(i, j))
            }
        }
    }
    return result
}



