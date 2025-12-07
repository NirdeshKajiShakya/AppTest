package com.example.cw.repo

import com.example.cw.model.UserModel
import com.google.firebase.database.DatabaseReference

class UserRepoImpl : UserRepo {
    val auth = com.google.firebase.auth.FirebaseAuth.getInstance()
    val database = com.google.firebase.database.FirebaseDatabase.getInstance()
    val ref: DatabaseReference = database.getReference("Users")

    override fun login(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Login successful")
                } else {
                    callback(false, "${it.exception?.message}")
                }
            }
    }

    override fun register(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun addUserToDatabase(
        userID: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(
        email: String,
        callback: (Boolean, String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Password reset email sent")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun getUserByID(
        userID: String,
        callback: (Boolean, String, UserModel) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(callback: (Boolean, String, List<UserModel>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun editProfile(
        userID: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteAccount(
        userID: String,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}