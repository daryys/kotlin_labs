package org.example.lab2

//9. Используя оператор switch, написать программу, которая выводит на экран сообщения о
// принадлежности некоторого значения k интервалам (-10k, 0], (0, 5], (5, 10], (10, 10k].

fun main() {
    var flag = true
    while (flag){

    print("Enter k: ")
    val k = readln().toIntOrNull()

        when {
            (k in (-9999..0)) -> println("k is in (-10k, 0]")
            (k in (1..5)) -> println("k is in (0, 5]")
            (k in (6..10)) -> println("k is in (5, 10]")
            (k in (11..10000)) -> println("k is in (10, 10k]")
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