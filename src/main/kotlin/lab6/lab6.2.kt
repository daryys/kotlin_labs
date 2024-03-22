package org.example.lab6

// 1. Определить множество на основе множества целых чисел.
// Создать методы для определения пересечения и объединения множеств.

fun main() {
    val firstSet = mutableSetOf<Int>()
    val secondSet = mutableSetOf<Int>()
    repeat(5) {
        firstSet.add((0..10).random())
        secondSet.add((0..10).random())
    }
    println(firstSet)
    println(secondSet)

    println(join(firstSet, secondSet))
    println(cross(firstSet, secondSet))
}

fun join(first: Set<Int>, second: Set<Int>) = first + second

fun cross(first: Set<Int>, second: Set<Int>) = first.intersect(second)