package com.example.planetforme.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.planetforme.model.User
import io.reactivex.rxjava3.core.Flowable
import java.util.*

@Dao
interface UserDao{

    @Query("SELECT * FROM user")
    fun getUsers():Flowable<List<User>>

    @Query("SELECT * FROM user WHERE id = (:id) ")
    fun getUser(id:UUID):Flowable<User?>

    @Update
    fun updateUser(user: User)

    @Insert
    fun addUser(user: User)
}