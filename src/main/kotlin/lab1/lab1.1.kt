package org.example.lab1

//3. Создать приложение, выводящее n строк с переходом и без перехода на новую строку.
fun main() {
    val listOfString = arrayOf("Hello", "my", "name", "is", "Daria")
    var flag = true
    while (flag) {
        print("Enter N: ")
        val n = readln().toIntOrNull() ?: 1

        for (i in 0..<n) {
            print("${listOfString[i % 5]} ")
        }
        println()

        for (i in 0..<n) {
            println(listOfString[i % 5])
        }
        println("Exit?")
        val exitCheck = readlnOrNull()
        if (exitCheck.equals("y") or exitCheck.equals("Y"))
            flag = false
    }
}