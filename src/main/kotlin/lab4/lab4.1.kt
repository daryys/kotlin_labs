package org.example.lab4

//9. Создать класс Park (парк) с внутренним классом, с помощью объектов которого можно
// хранить информацию об аттракционах, времени их работы и стоимости.

class Park(val list: MutableList<Attraction>) {

    fun addAttraction(name: String, openingTime: String, closingTime: String, price: Double) {
        list.add(Attraction(name, openingTime, closingTime, price))
    }

    inner class Attraction(
        val name: String,
        val openingTime: String,
        val closingTime: String,
        val price: Double
    ) {
        override fun toString() = "name: $name, open: $openingTime, close: $closingTime, price: $price"
    }
}

fun main() {
    val park = Park(mutableListOf())
    park.addAttraction("Roller Coaster", "10:00", "18:00", 250.0)
    park.addAttraction("Ferris Wheel", "11:00", "20:00", 150.0)

    park.list.forEach {
        println(it)
    }
}