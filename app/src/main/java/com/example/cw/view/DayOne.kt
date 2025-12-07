package com.example.cw.view

fun main() {
    //cw1()
    cw2()
}
fun cw1(){
    //mutable
    var something : String = "someone"
    something = "ram"
    //immutable
    val age : Int = 15

    println("mero nam ${something.uppercase()} ho ani mero age chai $age ho")
}
fun cw2(){
    val age = arrayOf(10,20,30)
    age[2] = 50
    println(age[2])

    val age1 = ArrayList<Int>()
    val age2 = arrayListOf<Int>(10,20,30)
    age1.add(5)
    age2.add(40)

    println(age1)
    println(age2)

    for(i in 0 .. 10 step 1){
        print("  $i")
    }

    println("")
    
    for(i in 10 downTo 0 step 2){
        print("  $i")
    }
}
