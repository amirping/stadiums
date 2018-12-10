package com.example.pingghost.stadiums.remote;

import com.example.pingghost.stadiums.model.Contract;
import com.example.pingghost.stadiums.model.Stadium;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by pingghost on 10/12/18.
 * may the code be with pingghost
 */

public interface StadiumService {
    @POST("stadium/")
    Call<JsonObject> createStadium(@Body Stadium stadium);

    @GET("stadium/")
    Call<JsonObject> getAllStadiums();

    @GET("stadium/{id}")
    Call<JsonObject> getStadium(@Path("id") String id);

    @GET("stadium/byname/{name}")
    Call<JsonObject> getStadiumsByName(@Path("name") String name);

    @GET("stadium/bylocation/{location}")
    Call<JsonObject> getStadiumsByLocation(@Path("location") String location);
}
