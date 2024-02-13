package org.example.lab1

//Ввести с консоли n целых чисел и поместить их в массив. На консоль вывести:
//10. Числа в порядке убывания частоты встречаемости чисел.

fun main() {
    print("Enter N: ")
    var n = readln().toIntOrNull() ?: 0
    var intArray = mutableListOf<Int>()
    println("Enter array: ")
    for (i in 0..< n) {
        intArray.add(readln().toIntOrNull() ?: 0)
    }

    val map = mutableMapOf<Int, Int>()
    intArray.forEach {
        map[it] = (map[it] ?: 0) + 1
    }

    val result = map.entries.sortedBy { it.value }.reversed()

    result.forEach{
        println("${it.key } - ${it.value }")
    }
}