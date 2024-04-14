package org.example.lab9

import kotlin.math.max
import kotlin.math.min

fun main() {
    first()
    second()
}

fun first() {
    List(10) {
        (0..100).random()
    }.let {
        val max = it.stream().reduce { t, u ->
            max(t, u)
        }.get()

        val min = it.stream().reduce { t, u ->
            min(t, u)
        }.get()

        println(it)
        println(max)
        println(min)
    }
}

fun second() {
    List(10) {
        (0..20).random()
    }.let {
        val sum = it.stream().reduce { t, u ->
            if (u > 10) {
                t + u
            } else {
                t
            }
        }.get()

        println(it)
        println(sum)
    }
}
