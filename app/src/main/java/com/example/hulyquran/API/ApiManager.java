package com.example.hulyquran.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {



    private static Retrofit retrofitInstance;
    private static Retrofit getInstance(){
        if(retrofitInstance==null){//create
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl("http://www.mp3quran.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

    public static ApiCall getAPIs(){
        ApiCall services = getInstance().create(ApiCall.class);
        return services;
    }
}
