package com.example.planetforme.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(@PrimaryKey val id: UUID = UUID.randomUUID(),
                var first_name:String = "Ivan",
                var last_name:String = "Ivanov",
                var email:String = "ivan@mail.ru",
                var avatar:String = ""
            ) {}