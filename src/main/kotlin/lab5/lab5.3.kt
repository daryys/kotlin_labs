package org.example.lab5

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

// Выполнить задания из варианта 2 лабораторной работы 3, реализуя собственные обработчики
// исключений и исключения ввода/вывода.

// 9. Product: id, Наименование, UPC, Производитель, Цена, Срок хранения, Количество.
// Создать массив объектов. Вывести:
// a) список товаров для заданного наименования;
// b) список товаров для заданного наименования, цена которых не превосходит заданную;
// c) список товаров, срок хранения которых больше заданного.


fun main() {
    val listOfProduct = mutableListOf<Product>()
    print("Enter n: ")
    val n = readln().toIntOrNull() ?: 0
    repeat(n) {
        println("name")
        val name = readln()
        println("upc")
        val upc = readln()
        println("manufacturer")
        val manufacturer = readln()
        println("price")
        val price = readln().toIntOrNull() ?: 0
        println("date")
        val date = try {
            convertDate(readln())
        } catch (e: IncorrectDateFormat) {
            println(e.message)
            Date()
        } catch (e: Exception) {
            println("Something went wrong")
            Date()
        }
        println("count")
        val count = readln().toIntOrNull() ?: 0

        listOfProduct.add(
            Product(
                name = name,
                upc = upc,
                manufacturer = manufacturer,
                price = price,
                date = date,
                count = count
            )
        )
    }

    println("Enter name of a product to filter")
    val name = readln()
    print(listOfProduct.filter { it.name.contains(name) })
    println()

    println("Enter price to filter")
    val price = readln().toIntOrNull() ?: 0
    print(listOfProduct.filter { it.name.contains(name) && it.price <= price })
    println()

    println("Enter expiration date to filter")

    try {
        print(listOfProduct.filter { it.date.after(convertDate(readln())) })
    } catch (e: IncorrectDateFormat) {
        println(e.message)
    } catch (e: Exception) {
        println("Something went wrong")
    }
}

data class Product(
    private val id: Int = Random.nextInt(),
    val name: String,
    val upc: String,
    val manufacturer: String,
    val price: Int,
    val date: Date,
    val count: Int
)

fun convertDate(date: String): Date {
    val parsed = date.split("-").map { it.toIntOrNull() ?: 0 }
    if ((parsed.size == 3) && (parsed[0] > 2023) && ((1..12).contains(parsed[1])) &&
        ((1..31).contains(parsed[2]))
    ) {
        return SimpleDateFormat("yyyy-MM-dd").parse(date) ?: Date()
    } else {
        throw IncorrectDateFormat()
    }
}

class IncorrectDateFormat(override val message: String = "Incorrect Date Format") : Exception()