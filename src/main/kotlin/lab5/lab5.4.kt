package org.example.lab5

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

// Выполнить задания из варианта 2 лабораторной работы 3, реализуя собственные обработчики
// исключений и исключения ввода/вывода.

// Train: Пункт назначения, Номер поезда, Время отправления, Число мест (общих, купе, плацкарт, люкс).
// Создать массив объектов. Вывести:
// a) список поездов, следующих до заданного пункта назначения;
// b) список поездов, следующих до заданного пункта назначения и отправляющихся после заданного часа;
// c) список поездов, отправляющихся до заданного пункта назначения и имеющих общие места

fun main() {
    val listOfTrains = mutableListOf<Train>()
    print("Enter n: ")
    val n = readln().toIntOrNull() ?: 0
    repeat(n) {

        println("destination")
        val destination = readln()

        println("departure yyyy-MM-dd")
        val date = try {
            convertDate(readln())
        } catch (e: IncorrectDateFormat) {
            println(e.message)
            Date()
        } catch (e: Exception) {
            println("Something went wrong")
            Date()
        }

        println("departure hh:mm")
        val time = try {
            convertTime(readln())
        } catch (e: IncorrectDateFormat) {
            println(e.message)
            Date()
        } catch (e: Exception) {
            println("Something went wrong")
            Date()
        }

        listOfTrains.add(
            Train(
                destination = destination,
                departureDate = date,
                departureTime = time
            )
        )
    }


    println("Enter destination to filter")
    val name = readln()
    println(listOfTrains.filter { it.destination.contains(name) })

    println("Enter date to filter")
    val date = try {
        convertDate(readln())
    } catch (e: IncorrectDateFormat) {
        println(e.message)
        Date()
    } catch (e: Exception) {
        println("Something went wrong")
        Date()
    }
    println(listOfTrains.filter { it.departureDate.after(date) && it.departureTime.after(date) && it.destination.contains(name) })
    println(listOfTrains.filter { it.destination.contains(name) && it.seats.public != 0 })
}

data class Train(
    val destination: String,
    val number: Int = Random.nextInt(),
    val departureDate: Date,
    val departureTime: Date,
    val seats: Seats = Seats()
)

data class Seats(
    val public: Int = (0..100).random(),
    val lux: Int = (0..20).random(),
    val standard: Int = (50..100).random(),
    val default: Int = (50..100).random()
)

fun convertTime(time: String): Date {
    val parsed = time.split(":").map { it.toIntOrNull() ?: 0 }
    if ((parsed.size == 2) && ((0..23).contains(parsed[0])) && ((0..59).contains(parsed[1]))
    ) {
        return SimpleDateFormat("hh:mm").parse(time) ?: Date()
    } else {
        throw IncorrectDateFormat()
    }
}