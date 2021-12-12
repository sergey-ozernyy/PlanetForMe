package com.example.planetforme.vm

import androidx.lifecycle.ViewModel
import com.example.planetforme.UserRepository

class UserListViewModel: ViewModel() {

    private val userRepository = UserRepository.get()
    val userListFlowable = userRepository.getUsers()

}