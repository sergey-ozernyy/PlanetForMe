package com.example.planetforme.database

import androidx.room.TypeConverter
import java.util.*

class UserTypeConverters {

    @TypeConverter
    fun fromUUID(uuid: UUID?):String?{
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid:String?):UUID?{
        return UUID.fromString(uuid)
    }
}