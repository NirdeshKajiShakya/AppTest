package com.example.cw

fun main() {
    val age = arrayOf(1, 2, 3)
    println(age)
    println("The first element of age is " + age[0])
    println("The second element of age is " + age[1])
    println("The third element of age is " + age[2])

    println("******************************")

    val name = arrayOf("ram", "shyam", "Hari")
    name[1] = "sandis"
    println("The first element of name is " + name[0])
    println("The second element of name is " + name[1])
    println("The third element of name is " + name[2])

    println(name.size)
}
