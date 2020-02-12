package com.digitalorder.np.api;

import com.digitalorder.np.model.OrderMod;
import com.digitalorder.np.model.Product;
import com.digitalorder.np.model.Rating;
import com.digitalorder.np.model.UpdateMod;
import com.digitalorder.np.model.Users;
import com.digitalorder.np.serverresponse.ImageResponse;
import com.digitalorder.np.serverresponse.SignUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UsersAPI {

    @POST("user/signup")
    Call<SignUpResponse> registerUser(@Body Users users);

    @POST("userorder")
    Call<Void> orderUser(@Header("Authorization")String token,
                           @Body Product product);

    @GET("userorder")
    Call<List<Product>> orderUserDisplay(@Header("Authorization")String token);

    @DELETE("userorder/{foodName}")
    Call<List<Product>> orderUserDelete(@Header("Authorization")String token, @Path("foodName") String foodName);

    @POST("favourite")
    Call<Void> favouriteDetatil(@Header("Authorization")String token,
                         @Body Product product);

    @GET("favourite")
    Call<List<Product>> favouriteDetailList(@Header("Authorization")String token);

    @POST("user/login")
    Call<SignUpResponse> checkUser(@Body Users users);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("user/me")
    Call<Users> getUserDetails(@Header("Authorization") String token);

    @PUT("user/UserUpdateAndroid")
    Call<String> updateProfile(@Header("Authorization") String token,@Body UpdateMod updateMod);

    @GET("order")
    Call<List<OrderMod>> getOrderDetails(@Header("Authorization")String token);

    @GET("order/pizza")
    Call<List<OrderMod>> getPizzaCategory(@Header("Authorization")String token);

    @GET("order/burger")
    Call<List<OrderMod>> getBurgerCategory(@Header("Authorization")String token);

    @GET("order/salad")
    Call<List<OrderMod>> getSaladCategory(@Header("Authorization")String token);

    @POST("rating")
    Call<Void> addRating(@Header("Authorization")String token,
                         @Body Rating rating);
}
