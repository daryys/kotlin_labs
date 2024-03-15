package org.example.lab4

//10. Создать класс Cinema (кино) с внутренним классом, с помощью объектов которого можно
// хранить информацию об адресах кинотеатров, фильмах и времени сеансов.

class Cinema(val list: MutableList<CinemaInfo>) {

    fun addCinemaInfo(address: String, movie: String, time: String) {
        list.add(CinemaInfo(address, movie, time))
    }

    inner class CinemaInfo(
        val address: String,
        val movie: String,
        val time: String
    ) {
        override fun toString() = "address: $address, movie: $movie, time: $time"
    }

}

fun main() {
    val cinema = Cinema(mutableListOf())

    cinema.addCinemaInfo("439 Street", "Avengers", "17:00")
    cinema.addCinemaInfo("123 Street", "Fight Club", "20:00")

    cinema.list.forEach {
        println(it)
    }
}