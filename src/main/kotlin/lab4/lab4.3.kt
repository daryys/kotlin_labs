package org.example.lab4

// Реализовать абстрактные классы или интерфейсы, а также наследование и полиморфизм для следующих классов
// 9. interface Мебель <- abstract class Шкаф <- class Книжный Шкаф.

interface Furniture {
    fun showInfo()
}

abstract class Wardrobe : Furniture {
    abstract var color: String
    abstract var material: String
}

class BookShelf : Wardrobe() {
    override var color: String = "brown"
    override var material: String = "wood"

    override fun showInfo() {
        println("BookShelf: color - $color, material - $material")
    }
}

fun main() {
    val bookWardrobe = BookShelf()
    bookWardrobe.showInfo()
}