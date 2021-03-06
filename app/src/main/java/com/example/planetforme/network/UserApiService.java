package com.example.planetforme.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApiService {

    private static UserApiService instance;
    private Retrofit retrofit;
    private static final String BASE_URL = "https://reqres.in/api/";


    private UserApiService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static UserApiService getInstance() {
        if(instance == null){
            instance = new UserApiService();
        }
        return instance;
    }



    public UserApi getJSONApi(){
        return retrofit.create(UserApi.class);
    }
}
