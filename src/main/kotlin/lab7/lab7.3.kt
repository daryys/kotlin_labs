// 9. Напечатать слова русского текста в алфавитном порядке по первой букве.
// Слова, начинающиеся с новой буквы, печатать с красной строки.
// 10. Рассортировать слова русского текста по возрастанию доли гласных букв
// (отношение количества гласных к общему количеству букв в слове)

fun main() {
    fifthTask()
    sixthTask()
}

fun fifthTask() {
    readln().lowercase().split(" ").sorted().groupBy { it.first() }.forEach {
        it.value.forEach { word ->
            print("$word ")
        }
        println()
    }
}

fun sixthTask() {
    val result = readln().lowercase().split(" ").map {
        Pair(
            it.filter { letter ->
                listOf(
                    "а",
                    "е",
                    "и",
                    "о",
                    "у",
                    "э",
                    "я",
                    "ю"
                ).contains(letter.toString())
            }.length / it.length,
            it
        )
    }.sortedBy { it.first }.reversed()
    println(result)
}