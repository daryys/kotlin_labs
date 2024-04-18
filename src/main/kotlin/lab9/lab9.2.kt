package org.example.lab9

fun main() {
    third()
    fourth()
}

fun third() {
    println("Input substring")
    val substring = readln()
    println("Input count of strings")
    val result = List(readln().toIntOrNull() ?: 1) {
        println("Input string")
        readln()
    }.stream().map {
        it.split(substring).size - 1
    }.reduce { acc, value -> acc + value }.get()
    println(result)
}


fun fourth() {
    listOf(Student("Ivan", 50), Student("Peter", 50), Student("Daria", 100)).stream().filter {
        it.name.first() == 'P' && (40..60).contains(it.rate)
    }.forEach {
        println(it)
    }
}

class Student(val name: String, val rate: Int)