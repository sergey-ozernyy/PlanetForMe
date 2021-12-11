package com.example.planetforme.model

import java.util.*

data class User (val id:Int = 0,
                 var first_name:String = "Ivan",
                 var last_name:String = "Ivanov",
                 var email:String = "ivan@mail.ru",
                 var avatar:String = ""
            ) {}