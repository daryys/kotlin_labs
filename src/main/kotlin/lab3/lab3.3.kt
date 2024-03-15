package org.example.lab3

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

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
        val date = convertDate(readln())
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

    println("Name")
    val name = readln()
    print(listOfProduct.filter { it.name.contains(name) })
    println("Price")
    val price = readln().toIntOrNull() ?: 0
    print(listOfProduct.filter { it.name.contains(name) && it.price <= price })
    println("Date")
    val date = convertDate(readln())
    print(listOfProduct.filter { it.date.after(date) })

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

fun convertDate(date: String) = SimpleDateFormat("yyyy-MM-dd").parse(date) ?: Date()