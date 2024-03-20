package org.example.lab5

import kotlin.math.sqrt

// Выполнить задания на основе варианта 1 лабораторной работы 3, контролируя состояние потоков ввода/вывода.
// При возникновении ошибок, связанных с корректностью выполнения математических операций, генерировать и
// обрабатывать исключительные ситуации. Предусмотреть обработку исключений, возникающих при нехватке памяти,
// отсутствии требуемой записи (объекта) в файле, недопустимом значении поля и т.д.

// 9. Определить класс Квадратное уравнение. Класс должен содержать несколько конструкторов.
// Реализовать методы для поиска корней, экстремумов, а также интервалов убывания/возрастания.
// Создать массив объектов и определить наибольшие и наименьшие по значению корни.

fun main() {
    val list = mutableListOf<QuadraticEquation>()
    repeat(10) {
        val range = (-10..10)
        list.add(QuadraticEquation(range.random(), range.random(), range.random()))
    }
    try {
        println(list[0].findRoots())
    } catch (e:Exception) {
        println("Something went wrong")
    }
    try {
        println(list[0].findExtremums())
        println(list[0].findDecreasingIncreasingIntervals())
    } catch (e: Exception) {
        println("A must not be equal 0")
    }

    try {
        list.filter { it.findRoots().isNotEmpty() }.let { sortedList ->
            println(sortedList.map { it.findRoots().max() })
            println(sortedList.map { it.findRoots().min() })
        }
    } catch (e: Exception) {
        println("Something went wrong")
    }
}

class QuadraticEquation(private val a: Int, private val b: Int, private val c: Int) {

    constructor(a: Int, b: Int) : this(a, b, 1)
    constructor(a: Int) : this(a, 1, 1)
    constructor() : this(1, 1, 1)

    fun findRoots(): List<Double> {
        val discriminant = b * b - 4 * a * c
        return if (discriminant == 0) {
            listOf((-b) / (2 * a).toDouble())
        } else {
            listOf((-b + sqrt(discriminant.toDouble())) / (2 * a), (-b - sqrt(discriminant.toDouble())) / (2 * a))
        }
    }

    fun findExtremums(): Pair<Double, Double> {
        val x = -b / (2 * a).toDouble()
        val y = a * x * x + b * x + c
        return Pair(x, y)
    }

    fun findDecreasingIncreasingIntervals(): String {
        val extremum = findExtremums()
        val interval = if (a > 0) Pair("убывает", "возрастает") else Pair("возрастает", "убывает")

        return "Функция ${interval.first} на интервале от  (-∞, ${extremum.first}) и ${interval.second} на интервале от (${extremum.first}, +∞)"
    }

}