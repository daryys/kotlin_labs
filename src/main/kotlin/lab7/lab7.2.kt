// В стихотворении найти одинаковые буквы, которые встречаются во всех словах.

// В тексте найти первую подстроку максимальной длины, не содержащую букв.

fun main() {
    firstTask()
    secondTask()
}

fun firstTask() {
    val map = mutableMapOf<Char, Int>()
    val input = readln().split(" ")
    input.map { string ->
        string.filter { it.isLetter() }.map { it }.distinct().forEach {
            map[it] = (map[it] ?: 0) + 1
        }
    }
    val result = map.filter { it.value == input.size }
    println(result)
}

fun secondTask() {
    val input = readln().lowercase().split(Regex("[a-zA-z]")).maxBy { it.length }
    println(input)
}


