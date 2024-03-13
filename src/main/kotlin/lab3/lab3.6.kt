package org.example.lab3

import java.time.DayOfWeek
import kotlin.math.abs

// Создать объект класса Год, используя классы Месяц, День.
// Методы: задать дату, вывести на консоль день недели по заданной дате,
// рассчитать количество дней, месяцев в заданном временном промежутке.

fun main() {

}

data class Year(private var year: Int, private var day: Day, private var month: Month) {

    fun setDate(
        year: Int = this.year,
        day: Int = this.day.day,
        dayName: DayOfWeek = this.day.name,
        month: Int = this.month.month
    ) {
        this.year = year
        this.day = Day(day, dayName)
        this.month = Month(month)
    }

    fun dayOfWeek() {
        day.name
    }

    fun countOfDays(before: Year, after: Year) =
        365 * abs(after.year - before.year) + 30 * abs(after.month.month - before.month.month) + (after.day.day - before.day.day)

    override fun toString() = "${year}-${day.day}-${month.month}"

}

data class Day(val day: Int, val name: DayOfWeek)

data class Month(val month: Int)