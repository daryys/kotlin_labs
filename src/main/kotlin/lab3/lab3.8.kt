package org.example.lab3

import java.util.*
import kotlin.random.Random

// Система Железнодорожная касса.
// Пассажир делает Заявку на станцию назначения, время и дату поездки.
// Система регистрирует Заявку и осуществляет поиск подходящего Поезда.
// Пассажир делает выбор Поезда и получает Счет на оплату.
// Администратор вводит номера Поездов, промежуточные и конечные станции, цены

fun main() {
    val cash = Cash()
    while (true) {
        println("Write exit to leave")
        println("Chose action: 1) add client; 2) add admin; 3) chose role")
        val input = readln().lowercase()
        if (input != "exit") {
            when (readln().lowercase().toIntOrNull() ?: 0) {
                1 -> cash.addClient()
                2 -> cash.addAdmin()
                3 -> choseRole(cash)
                else -> println("Wrong input")
            }
        } else {
            break
        }
    }
}

fun choseRole(cash: Cash) {
    println("Chose role: Admin, Client")
    when (readln().lowercase()) {
        "admin" -> adminRole(cash)
        "client" -> clientRole(cash)
        else -> println("Wrong input")
    }
}

fun adminRole(cash: Cash) {
    println("Chose admin")
    println(cash.administrators)
    cash.administrators.getOrNull(readln().toIntOrNull() ?: 0)?.let {
        println("Choose action (number): 1) add train")
        when (readln().lowercase().toIntOrNull() ?: 0) {
            1 -> it.addTrain()
            else -> {}
        }
    }
}

fun clientRole(cash: Cash) {
    println("Chose client")
    println(cash.clients)
    cash.clients.getOrNull(readln().toIntOrNull() ?: 0)?.let {
        println("Choose action (number): 1) make request")
        when (readln().lowercase().toIntOrNull() ?: 0) {
            1 -> it.makeRequest()
            else -> {}
        }
    }
}

class Cash(
    private val trains: MutableList<TrainModel> = mutableListOf(),
    val clients: MutableList<Client> = mutableListOf(),
    val administrators: MutableList<Administrator> = mutableListOf()
) {
    fun registerRequest(request: Request) =
        trains.filter { it.departure?.before(request.date) ?: false && it.listOfDestination.contains(request.destination) }

    fun addAdmin() {
        println("Name")
        administrators.add(Administrator(name = readln()))
    }

    fun addClient() {
        println("Name")
        clients.add(Client(name = readln()))
    }

    inner class Client(val name: String) {
        fun makeRequest() {
            println("Date (format: dd/M/yyyy hh:mm:ss) and destination")
            val trains = registerRequest(Request(convertDateTime(readln()), readln()))
            if (trains.isNotEmpty()) {
                println(trains)
                println("Price: ${trains.getOrNull(readln().toIntOrNull() ?: 0)?.price}")
            }
        }

        override fun toString() = name
    }

    inner class Administrator(val name: String) {
        fun addTrain() {
            println("Date (format: dd/M/yyyy hh:mm:ss)")
            val date = convertDateTime(readln())
            println("Price")
            val price = readln().toIntOrNull() ?: 0
            println("Destination count")
            val listOfDestination = mutableListOf<String>()
            repeat(readln().toIntOrNull() ?: 1) {
                listOfDestination.add(readln())
            }
            trains.add(
                TrainModel(
                    departure = date,
                    listOfDestination = listOfDestination,
                    price = price
                )
            )
        }

        override fun toString() = name
    }

    data class Request(val date: Date, val destination: String)

}

data class TrainModel(
    val id: Int = Random.nextInt(),
    val departure: Date? = null,
    val price: Int,
    val listOfDestination: List<String>,
)