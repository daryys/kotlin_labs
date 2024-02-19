package org.example.lab2

//10. Используя оператор switch, написать программу, которая выводит на экран сообщения о
// принадлежности некоторого значения k интервалам (-10k, 5], [0, 10], [5, 15], [10, 10k].

fun main() {
    var flag = true
    while (flag) {
        print("Enter k: ")
        val k = readln().toIntOrNull()
        when {
            (k == 5) -> println("k is in (-10k, 5], [0, 10], [5, 15]")
            (k == 10) -> println("k is in [0, 10], [5, 15], [10, 10k]")

            (k in (0..5)) -> println("k is in (-10k, 5], [0, 10]")
            (k in (6..9)) -> println("k is in [0, 10], [5, 15]")
            (k in (11..15)) -> println("k is in [5, 15], [10, 10k]")

            (k in (-9999..5)) -> println("k is in (-10k, 5]")
            (k in (0..10)) -> println("k is in [0, 10]")
            (k in (5..15)) -> println("k is in [5, 15]")
            (k in (10..10000)) -> println("k is in [10, 10k]")
            else -> {
                println("k is not in range")
            }
        }

        print("Exit? ")
        val exitCheck = readlnOrNull()
        if (exitCheck.equals("y") or exitCheck.equals("Y"))
            flag = false
    }
}