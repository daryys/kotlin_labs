package org.example.lab3

// Определить класс Булева матрица (BoolMatrix) размерности (n x m).
// Класс должен содержать несколько конструкторов. Реализовать методы для логического сложения
// (дизъюнкции), умножения и инверсии матриц. Реализовать методы для подсчета числа единиц в
// матрице и упорядочения строк в лексикографическом порядке

fun main() {
    print("Enter n: ")
    val n1 = readln().toIntOrNull() ?: 0
    print("Enter m: ")
    val m1 = readln().toIntOrNull() ?: 0
    val matrix1 = BoolMatrix(n1, m1)
    println(matrix1)
    println(matrix1.countZeros())
}

class BoolMatrix(private val n: Int, private val m: Int, private val matrix: List<List<Boolean>>) {

    constructor(n: Int, m: Int) : this(n, m, List(n) {
        List(m) { listOf(true, false).random() }
    })

    override fun toString(): String {
        return  matrix.toString()
    }

//    fun sumMatrix(): List<BooleanArray> {
//
//    }
//
//    fun multiplyMatrix(): List<List<Int>> {
//
//    }
//
//    fun inverseMatrix(): List<List<Int>> {
//
//    }

    fun countZeros() = matrix.sumOf {
        it.count { value -> value }
    }

//    fun arrangeMatrix(): List<List<Int>> {
//
//    }

}