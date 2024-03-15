package org.example.lab3

import kotlin.random.Random

// Система Интернет-магазин.
// Администратор добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
// Администратор регистрирует Продажу и может занести неплательщиков в «черный список».

fun main() {
    val shop = Shop()
    while (true) {
        println("Write exit to leave")
        println("Chose action: 1) add client; 2) add admin; 3) choose role")
        val input = readln().lowercase()
        if (input != "exit") {
            when (readln().lowercase().toIntOrNull() ?: 0) {
                1 -> shop.addClient()
                2 -> shop.addAdmin()
                3 -> choseRole(shop)
                else -> println("Wrong input")
            }
        } else {
            break
        }
    }
}

fun choseRole(shop: Shop) {
    println("Chose role: Admin, Client")
    when (readln().lowercase()) {
        "admin" -> adminRole(shop)
        "client" -> clientRole(shop)
        else -> println("Wrong input")
    }
}

fun adminRole(shop: Shop) {
    println("Chose admin")
    println(shop.administrators)
    shop.administrators.getOrNull(readln().toIntOrNull() ?: 0)?.let {
        println("Choose action (number): 1) add good; 2) change good; 3) add to black list; 4) register order")
        when (readln().lowercase().toIntOrNull() ?: 0) {
            1 -> it.addGood()
            2 -> it.changeInformation()
            3 -> it.addToBlackList()
            4 -> it.registerOrder()
            else -> {}
        }
    }
}

fun clientRole(shop: Shop) {
    println("Chose client")
    println(shop.clients)
    shop.clients.getOrNull(readln().toIntOrNull() ?: 0)?.let {
        println("Choose action (number): 1) add good; 2) pay; 3) show orders")
        when (readln().lowercase().toIntOrNull() ?: 0) {
            1 -> it.addGoods()
            2 -> it.pay()
            3 -> it.showOrders()
            else -> {}
        }
    }
}

class Shop(
    private val blackList: MutableSet<Int> = mutableSetOf(),
    val administrators: MutableList<Administrator> = mutableListOf(),
    val clients: MutableList<Client> = mutableListOf(),
    private val listOfGoods: MutableList<Goods> = mutableListOf(),
    private val orders: MutableList<Order> = mutableListOf()
) {

    fun addAdmin() {
        println("Name")
        administrators.add(Administrator(name = readln()))
    }

    fun addClient() {
        println("Name")
        clients.add(Client(name = readln()))
    }

    inner class Client(
        val id: Int = Random.nextInt(),
        val name: String,
        private val list: MutableList<Goods> = mutableListOf()
    ) {

        fun addGoods() = withAccess {
            choseGood {
                list.add(it)
            }
        }

        fun pay() = withAccess {
            println("Cash")
            println(list.sumOf { it.price })
            val cash = readln().toIntOrNull() ?: 0
            if (cash >= list.sumOf { it.price }) {
                println("Success")
                orders.add(
                    Order(
                        clientId = id,
                        listOfGoods = listOfGoods
                    )
                )
                list.clear()
            } else {
                println("Error")
            }
        }

        fun showOrders() {
            println(orders.filter { it.clientId == id })
        }

        override fun toString() = name

        private fun withAccess(action: () -> Unit) {
            if (!blackList.contains(id)) {
                action()
            } else {
                println("No access")
            }
        }


    }

    inner class Administrator(val name: String) {
        fun addGood() {
            println("Name")
            val name = readln()
            println("Price")
            val price = readln().toIntOrNull() ?: 0
            println("Information")
            val information = readln()

            listOfGoods.add(
                Goods(
                    name = name,
                    price = price,
                    information = information
                )
            )
        }

        fun changeInformation() {
            choseGood {
                println("Information")
                it.information = readln()
            }
        }

        fun addToBlackList() {
            println(clients)
            clients.getOrNull(readln().toIntOrNull() ?: -1)?.let {
                blackList.add(it.id)
            }
        }

        fun registerOrder() {
            println(orders)
            orders.getOrNull(readln().toIntOrNull() ?: -1)?.let {
                println("Ready (0/1)?")
                if ((readln().toIntOrNull() ?: 0) == 1) {
                    orders[orders.indexOf(it)] = it.copy(status = true)
                }
            }
        }

        override fun toString() = name
    }

    fun choseGood(action: (goods: Goods) -> Unit) {
        println(listOfGoods.toString())
        println("Input index")
        listOfGoods.getOrNull(readln().toIntOrNull() ?: 0)?.let {
            action(it)
        } ?: println("Error")
    }

}

data class Order(
    val id: Int = Random.nextInt(),
    val clientId: Int,
    val listOfGoods: MutableList<Goods>,
    val status: Boolean = false
)

data class Goods(val id: Int = Random.nextInt(), val name: String, val price: Int, var information: String)