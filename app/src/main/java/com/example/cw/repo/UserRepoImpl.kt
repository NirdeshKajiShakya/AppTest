package com.example.cw.repo

import com.example.cw.model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

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
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Registration successful", "${auth.currentUser?.uid}")
                } else {
                    callback(false, "${it.exception?.message}", "")
                }
            }
    }

    override fun addUserToDatabase(
        userID: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userID).setValue(model).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "User added to database")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
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
        callback: (Boolean, String, UserModel?) -> Unit
    ) {
        ref.child(userID)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(UserModel::class.java)
                    if (user != null) {
                        callback(true, "User fetched successfully", user)
                    } else {
                        callback(false, "User not found", null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback (false, "${error.message}", null)
                }
            })
    }

    override fun getAllUsers(callback: (Boolean, String, List<UserModel>) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var allUsers = mutableListOf<UserModel>()
                    for (data in snapshot.children){
                        val user = data.getValue(UserModel::class.java)
                        if (user != null){
                            allUsers.add(user)
                        }
                    }
                    callback(true, "Users fetched successfully", allUsers)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun editProfile(
        userID: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userID).updateChildren(model.toMap()).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Profile updated successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun deleteAccount(
        userID: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userID).removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                auth.currentUser?.delete()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback(true, "Account deleted successfully")
                    } else {
                        callback(false, "${task.exception?.message}")
                    }
                }
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

}