package org.example.lab4

// Реализовать абстрактные классы или интерфейсы, а также наследование и полиморфизм для следующих классов
// 10. interface Фильм <- class Отечественный Фильм <- class Комедия.

interface Movie {
    fun showInfo()
}

open class RussianMovie : Movie {
    open var name: String = ""
    open var description: String = ""

    override fun showInfo() {
        println("Russian movie")
    }
}

class Comedy : RussianMovie() {
    override var name: String = "Avengers"
    override var description: String = "Very funny movie"

    override fun showInfo() {
        println("Russian Movie: name - $name, description - $description")
    }
}

fun main() {
    val comedy = Comedy()
    comedy.showInfo()
}