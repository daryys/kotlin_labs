package org.example.lab7

// 9. В стихотворении найти одинаковые буквы, которые встречаются во всех словах.
// 10. В тексте найти первую подстроку максимальной длины, не содержащую букв.

fun main() {
    thirdTask()
    fourthTask()
}

fun thirdTask() {
    val map = mutableMapOf<Char, Int>()
    val input = readln().split(" ")
    input.forEach { string ->
        string.filter { it.isLetter() }.map { it }.distinct().forEach {
            map[it] = (map[it] ?: 0) + 1
        }
    }
    val result = map.filter { it.value == input.size }
    println(result)
}

fun fourthTask() {
    val input = readln().split(Regex("[a-zA-Z]")).maxBy { it.length }
    println(input)
}
