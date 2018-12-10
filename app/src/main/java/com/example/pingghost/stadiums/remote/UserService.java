package com.example.pingghost.stadiums.remote;

import com.example.pingghost.stadiums.model.User;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by pingghost on 09/12/18.
 * may the code be with pingghost
 */

public interface UserService {
    @POST("user/signup")
    Call<JsonObject> registerUser(@Body User user);

    @POST("user/login")
    Call<JsonObject> logUser(@Body User user);

    @GET("user/getuser")
    Call<List<User>> getUsers();

    @GET("user/getuser/{id}")
    Call<JsonObject> getUser(@Path("id") String id);

    /*
    @POST("user/add/")
    Call<User> addUser(@Body User user);
    */

    @PATCH("user/updateuser/{id}")
    Call<JsonObject> updateUser(@Path("id") String id, @Body User user);

    @PATCH("user/updateuseremail/{id}")
    Call<JsonObject> updateUserEmail(@Path("id") String id, @Body String new_email);

    @FormUrlEncoded
    @PATCH("user/updateuserpassword/{id}")
    Call<JsonObject> updateUserPassword(@Path("id") String id, @Field("new_password") String new_password, @Field("old_password") String old_password);

    @DELETE("user/{id}")
    Call<User> deleteUser(@Path("id") int id);
}
