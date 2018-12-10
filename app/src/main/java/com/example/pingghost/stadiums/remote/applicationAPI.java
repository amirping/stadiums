package com.example.pingghost.stadiums.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pingghost on 09/12/18.
 */

public class applicationAPI {
    private static Retrofit retrofit = null;
    public static Retrofit getAPICLient(String url) {
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
