package com.example.planetforme.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.planetforme.model.User

@Database(entities = [User::class], version = 1)
@TypeConverters(UserTypeConverters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao():UserDao
}