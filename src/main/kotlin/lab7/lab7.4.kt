import org.example.lab7.removeIfNotLetter
import kotlin.math.abs

// 9. Преобразовать каждое слово в тексте, удалив из него все последующие (предыдущие) вхождения
// первой (последней) буквы этого слова
// 10. Исключить из текста подстроку максимальной длины, начинающуюся и заканчивающуюся одним и тем же символом

fun main() {
    seventhTask()
    eighthTask()
}

fun seventhTask() {
    val resultFirst = readln().split(" ").map { it.removeIfNotLetter().lowercase() }.joinToString(" ") {
        it.filterIndexed { index, char ->
            index == 0 || char != it[0]
        }
    }
    val resultSecond = readln().split(" ").map { it.removeIfNotLetter().lowercase() }.joinToString(" ") {
        it.filterIndexed { index, char ->
            index == it.length - 1 || char != it[it.length - 1]
        }
    }
    println(resultFirst)
    println(resultSecond)
}

fun eighthTask() {
    var max = 0
    var startIndex = 0
    var endIndex = 0
    val result = readln().apply {
        forEachIndexed { index, first ->
            val last = this.indexOfLast { it == first }
            if (abs(index - last) + 1 > max) {
                max = abs(index - last) + 1
                startIndex = index
                endIndex = last
            }
        }
    }.removeRange(startIndex..endIndex)
    println(result)
}
