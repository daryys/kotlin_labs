package org.example.lab1

//Ввести с консоли n целых чисел и поместить их в массив. На консоль вывести:
//9. Отсортированные числа в порядке возрастания и убывания.

fun main() {
    print("Enter N: ")
    var n = readln().toIntOrNull() ?: 0
    var intArray = mutableListOf<Int>()
    println("Enter array: ")
    for (i in 0..< n) {
        intArray.add(readln().toIntOrNull() ?: 0)
    }

    println("Ascending array: ")
    println(intArray.apply { sort() })

    println("Descending array: ")
    println(intArray.apply { reverse() })
}