package org.example.lab6

// 10. На прямой гоночной трассе стоит N автомобилей, для каждого из которых известны начальное
// положение и скорость. Определить, сколько произойдет обгонов.

fun main() {

    val racersList = mutableListOf<Pair<Int, Int>>()
    for (i in 1..7) {
        racersList.add(
        Pair((i), ((1..150).random()))
        )
    }
    println(racersList.sortedBy { it.first })
    println(overTake(racersList))
}

fun overTake(racersList: List<Pair<Int, Int>>): Int {
    var count = 0
    racersList.forEachIndexed { i, racer ->
        for (j in (i + 1..<racersList.size)) {
            if (racer.first < racersList[j].first && racer.second > racersList[j].second) {
                count += 1
            }
        }
    }
    return count
}