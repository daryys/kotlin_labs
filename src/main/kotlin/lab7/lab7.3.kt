import org.example.lab7.removeIfNotLetter

// 9.	Напечатать слова русского текста в алфавитном порядке по первой букве. Слова, начинающиеся с новой буквы, печатать с красной строки.

//10.	Рассортировать слова русского текста по возрастанию доли гласных букв (отношение количества гласных к общему количеству букв в слове)


fun main() {
    first()
    second()
}

fun first() {
    val result = readln().split(" ").map { it.removeIfNotLetter().lowercase() }.sorted()
        .groupBy { it.first() }.forEach {
            it.value.forEach { word ->
                print("$word ")
            }
            println()
        }
}

fun second() {
    val result = readln().split(" ").map { it.removeIfNotLetter().lowercase() }.map {
        Pair(
            it.filter { letter -> listOf("a", "o", "e", "i", "u", "y").contains(letter.toString()) }.length / it.length,
            it
        )
    }
    println(result)
}