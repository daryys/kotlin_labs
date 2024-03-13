package org.example.lab3

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

// Train: Пункт назначения, Номер поезда, Время отправления, Число мест (общих, купе, плацкарт, люкс). Создать массив объектов.
// Вывести:
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
        println("departure")
        val date = convertDateTime(readln())

        listOfTrains.add(
            Train(
                destination = destination,
                departure = date
            )
        )
    }

    println("Destination")
    val name = readln()
    print(listOfTrains.filter { it.destination.contains(name) })

    println("Date")
    val date = convertDateTime(readln())
    print(listOfTrains.filter { it.departure.after(date) && it.destination.contains(name) })

    print(listOfTrains.filter { it.destination.contains(name) && it.seats.public != 0 })
}

data class Train(
    val destination: String,
    val number: Int = Random.nextInt(),
    val departure: Date,
    val seats: Seats = Seats()
)

data class Seats(
    val public: Int = (0..100).random(),
    val lux: Int = (0..20).random(),
    val standard: Int = (50..100).random(),
    val default: Int = (50..100).random()
)

fun convertDateTime(date: String) = SimpleDateFormat("dd/M/yyyy hh:mm:ss").parse(date) ?: Date()