package com.example.planetforme.network;

import com.example.planetforme.model.Data;
import com.example.planetforme.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface UserApi {
    @GET("users?page=2")
    Single<Data> getUsers();

}
