package com.example.planetforme

import android.content.Context
import androidx.room.Room
import com.example.planetforme.database.UserDatabase
import com.example.planetforme.model.Data
import com.example.planetforme.model.User
import com.example.planetforme.network.UserApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "user-database"

class UserRepository private constructor(context:Context){

    private val database:UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val userDao = database.userDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getUsers(): Flowable<List<User>> = userDao.getUsers()
    fun getUser(uuid: UUID): Flowable<User?> = userDao.getUser(uuid)

    fun updateUser(user: User){
        executor.execute{
            userDao.updateUser(user)
        }
    }

    fun addUser(user: User){
        executor.execute {
            userDao.addUser(user)
        }
    }

    fun getUsersFromServer() {
        UserApiService.getInstance()
            .getJSONApi()
            .getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Data?>() {
                override fun onSuccess(value: Data?) {
                    value?.data?.map {
                        addUser(it)
                    }

                }

                override fun onError(e: Throwable) {}

            })
    }

    companion object {

        private var INSTANCE:UserRepository? = null

        fun initialize(context: Context){
            if (INSTANCE == null){
                INSTANCE = UserRepository(context)
            }
        }

        fun get():UserRepository{
            return INSTANCE ?:
                throw IllegalStateException("UserRepository must be initialized")
        }

    }

}