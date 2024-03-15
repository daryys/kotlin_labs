package org.example.lab3

// Определить класс Булева матрица (BoolMatrix) размерности (n x m).
// Класс должен содержать несколько конструкторов. Реализовать методы для логического сложения
// (дизъюнкции), умножения и инверсии матриц. Реализовать методы для подсчета числа единиц в
// матрице и упорядочения строк в лексикографическом порядке

fun main() {
    print("Enter n: ")
    val n = readln().toIntOrNull() ?: 0
    print("Enter m: ")
    val m = readln().toIntOrNull() ?: 0
    val matrix1 = BoolMatrix(n, m)
    val matrix2 = BoolMatrix(n, m)
    println(matrix1)
    println(matrix2)
    println("Коньюнкция матриц")
    println(matrix1.andMatrix(matrix2))
    println("Дизъюнкция матриц")
    println(matrix1.orMatrix(matrix2))
    println("Подсчет нулей матрицы")
    println(matrix1.countZeros())
    println("Инверсия матрицы")
    println(matrix1.inverseMatrix())
    println("Сортировка матрицы")
    println(matrix1.sort())
    println("Умножение матрицы")
    println(matrix1.multiplyMatrix(matrix2))
}

class BoolMatrix(private val n: Int, private val m: Int, private val matrix: List<List<Boolean>>) {

    constructor(n: Int, m: Int) : this(n, m, List(n) {
        List(m) { listOf(true, false).random() }
    })

    constructor(matrix: List<List<Boolean>>) : this(matrix.size, matrix.firstOrNull()?.size ?: 0, matrix)

    override fun toString(): String {
        return matrix.toString()
    }

    fun andMatrix(boolMatrix: BoolMatrix) = if (boolMatrix.n == n && boolMatrix.m == m) {
        BoolMatrix(boolMatrix.matrix.mapIndexed { i, row ->
            row.mapIndexed { j, value ->
                value && matrix[i][j]
            }
        })
    } else {
        throw Exception()
    }

    fun orMatrix(boolMatrix: BoolMatrix) = if (boolMatrix.n == n && boolMatrix.m == m) {
        BoolMatrix(boolMatrix.matrix.mapIndexed { i, row ->
            row.mapIndexed { j, value ->
                value || matrix[i][j]
            }
        })
    } else {
        throw Exception()
    }

    fun inverseMatrix() = BoolMatrix(matrix.map { row ->
        row.map { value ->
            !value
        }
    })

    fun multiplyMatrix(boolMatrix: BoolMatrix) = if (m == boolMatrix.n) {
        val tempMatrix = mutableListOf<MutableList<Boolean>>()
        for (i in 0..<n) {
            val row = mutableListOf<Boolean>()
            for (j in 0..<m) {
                var currentElement = false
                for (k in 0..<m) {
                    currentElement = currentElement || boolMatrix.matrix[i][k] && matrix[k][j]
                }
                row.add(currentElement)
            }
            tempMatrix.add(row)
        }
        BoolMatrix(tempMatrix)
    } else {
        throw Exception()
    }

    fun sort() = BoolMatrix(matrix.map { it.sorted() })

    fun countZeros() = matrix.sumOf {
        it.count { value -> !value }
    }

}