package com.example.cw.model

import androidx.core.app.GrammaticalInflectionManagerCompat

data class UserModel(
    val userID : String = "",
    val firstName : String = "",
    val lastName : String = "",
    val email : String = "",
    val gender : String = "",
    val dob : String = "",
){
    fun  toMap(): Map<String, Any?> {
        return mapOf(
            "userID" to userID,
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "gender" to gender,
            "dob" to dob
        )
    }
}
