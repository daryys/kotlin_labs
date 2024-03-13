package org.example.lab3

import java.util.*

// Создать объект класса Фотоальбом, используя класс Фотография.
// Методы: задать название фотографии, дополнить фотоальбом фотографией, вывести на консоль количество фотографий.

data class Album(private val listOfPhotos: MutableList<Photo> = mutableListOf()) {

    fun addPhoto(photo: Photo) {
        listOfPhotos.add(photo)
    }

    fun setName(index: Int, name: String) {
        listOfPhotos.getOrNull(index)?.name = name
    }

    fun albumSize() = println("Album size: ${listOfPhotos.size}")

    override fun toString() = "Album: $listOfPhotos"
}

data class Photo(val date: Date = Date(), val url: String = "", var name: String = "")