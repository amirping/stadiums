package com.example.pingghost.stadiums.remote;

import com.example.pingghost.stadiums.model.Contract;
import com.example.pingghost.stadiums.model.User;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by pingghost on 10/12/18.
 * may the code be with pingghost
 */

public interface ContractService {
    @POST("contact/")
    Call<JsonObject> createContract(@Body Contract contract);

    @GET("contact/")
    Call<JsonObject> getContracts();

    @GET("contact/{id}")
    Call<JsonObject> getContract(@Path("id") String id);

    @GET("contact/byuser/{id}")
    Call<JsonObject> getUserContracts(@Path("id") String id);

    @PATCH("contact/{id}")
    Call<JsonObject> updateContract(@Path("id") String id, @Body Contract contract);

    /*
    @POST("user/add/")
    Call<User> addUser(@Body User user);


    @PATCH("user/updateuser/{id}")
    Call<JsonObject> updateUser(@Path("id") String id, @Body User user);

    @PATCH("user/updateuseremail/{id}")
    Call<JsonObject> updateUserEmail(@Path("id") String id, @Body String new_email);

    @FormUrlEncoded
    @PATCH("user/updateuserpassword/{id}")
    Call<JsonObject> updateUserPassword(@Path("id") String id, @Field("new_password") String new_password, @Field("old_password") String old_password);

    @DELETE("user/{id}")
    Call<User> deleteUser(@Path("id") int id);
    */
}
