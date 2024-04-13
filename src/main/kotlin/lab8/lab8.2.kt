package org.example.lab8

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

fun main() {
    val shop = Shop()
    val clients = List(10) {
        shop.Client()
    }

    val manufacture = Manufacture(
        listOf(
            Product(name = "1", price = 100),
            Product(name = "1", price = 200),
            Product(name = "3", price = 300)
        )
    )

    runBlocking {
        awaitAll(
            async {
                List(10) {
                    manufacture.addProduct(shop)
                }.forEach { it.await() }
            },
            async {
                List(10) {
                    clients.random().buyProduct()
                }.forEach { it.await() }
            })
    }
}

class Shop {
    private val listOfProducts = mutableListOf<ShopProduct>()

    fun addProduct(product: Product) {
        CoroutineScope(Dispatchers.Default).launch {
            listOfProducts.find { it.product.id == product.id }?.add() ?: listOfProducts.add(ShopProduct(product, 1))
        }
    }

    fun buy(product: Product, client: Client) {
        CoroutineScope(Dispatchers.Default).launch {
            listOfProducts.find { it.product.id == product.id }?.remove(client)
        }
    }

    inner class Client {
        fun buyProduct() = CoroutineScope(Dispatchers.Default).async {
            delay((0..10).random() * 1000L)
            if (listOfProducts.isNotEmpty()) {
                listOfProducts.getOrNull(listOfProducts.indices.random())?.let {
                    println("Client: ${this@Client}, Product: $it")
                    buy(it.product, this@Client)
                }
            }
        }
    }

    data class ShopProduct(val product: Product, private var count: Int = 0) {
        private val mutex = Mutex()

        suspend fun add() {
            mutex.withLock {
                count += 1
                println("Product added: $this")
            }
        }

        suspend fun remove(client: Client) {
            mutex.withLock {
                if (count > 0) {
                    count -= 1
                    println("Product bought: $this by $client")
                } else {
                    println("No product to buy: $this by $client")
                }
            }
        }
    }


}


class Manufacture(private val listOfProduct: List<Product>) {

    fun addProduct(shop: Shop) = CoroutineScope(Dispatchers.Default).async<Unit> {
        listOfProduct.getOrNull(listOfProduct.indices.random())?.let {
            delay((0..10).random() * 1000L)
            shop.addProduct(it)
        }
    }
}


data class Product(val id: Int = Random.nextInt(), val name: String, val price: Int)