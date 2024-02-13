package org.example.lab1

//4. Создать приложение для ввода пароля из командной строки и сравнения его со строкой-образцом.
fun main() {
    val password = "qwerty1234"
    var flag = true
    while (flag) {
        print("Enter password: ")
        val newPassword = readlnOrNull()
        if (newPassword.equals(password))
            println("Entered password is correct")
        else
            println("Entered password is incorrect")

        println("Exit?")
        val exitCheck = readlnOrNull()
        if (exitCheck.equals("y") or exitCheck.equals("Y"))
            flag = false
    }
}