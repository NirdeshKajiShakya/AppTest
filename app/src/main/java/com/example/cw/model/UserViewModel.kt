package com.example.cw.model

import androidx.lifecycle.MutableLiveData
import com.example.cw.repo.UserRepo

class UserViewModel(val repo: UserRepo) {
    fun login(email : String, password : String, callback : (Boolean, String) -> Unit){
        repo.login(email, password, callback)
    }

    fun register(email: String, password: String, callback: (Boolean, String, String) -> Unit){
        repo.register(email, password, callback)
    }

    fun addUserToDatabase(userID : String, model : UserModel, callback : (Boolean, String) -> Unit){
        repo.addUserToDatabase(userID, model, callback)
    }

    fun forgotPassword(email: String, callback: (Boolean, String) -> Unit){
        repo.forgotPassword(email, callback)
    }

    private val _users = MutableLiveData<UserModel?>()
    val users : MutableLiveData<UserModel?> get() = _users

    private val _allUsers = MutableLiveData<List<UserModel>>()
    val allUsers : MutableLiveData<List<UserModel>> get() = _allUsers

    private val _loading = MutableLiveData<Boolean>()
    val loading : MutableLiveData<Boolean> get() = _loading

    fun getUserByID(userID: String){
        _loading.value = true
        repo.getUserByID(userID) { isSuccess, message, userModel ->
            if(isSuccess){
                _users.value = userModel
            }
            _loading.value = false
        }
    }

    fun getAllUsers(){
        _loading.value = true
        repo.getAllUsers { isSuccess, message, userList ->
            if(isSuccess){
                _allUsers.postValue(userList)
            }
            _loading.value = false
        }
    }

    fun editProfile(userID: String, model: UserModel, callback: (Boolean, String) -> Unit){
        repo.editProfile(userID, model, callback)
    }

    fun deleteAccount(userID: String, callback: (Boolean, String) -> Unit){
        repo.deleteAccount(userID, callback)
    }
}