package com.example.cw.repo

import com.example.cw.model.UserModel

interface UserRepo {

    fun login(email : String, password : String, callback : (Boolean, String) -> Unit)

    fun register(email: String, password: String, callback: (Boolean, String, String) -> Unit)

    fun addUserToDatabase(userID : String, model : UserModel, callback : (Boolean, String) -> Unit)

    fun forgotPassword(email: String, callback: (Boolean, String) -> Unit)

    fun getUserByID(userID: String, callback: (Boolean, String, UserModel) -> Unit)

    fun getAllUsers(callback: (Boolean, String, List<UserModel>) -> Unit)

    fun editProfile(userID: String, model: UserModel, callback: (Boolean, String) -> Unit)

    fun deleteAccount(userID: String, callback: (Boolean, String) -> Unit)
}