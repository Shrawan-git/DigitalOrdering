package com.digitalorder.np.api;

import com.digitalorder.np.model.UpdateMod;
import com.digitalorder.np.model.Users;
import com.digitalorder.np.serverresponse.ImageResponse;
import com.digitalorder.np.serverresponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UsersAPI {

    @POST("user/signup")
    Call<SignUpResponse> registerUser(@Body Users users);

    @POST("user/login")
    Call<SignUpResponse> checkUser(@Body Users users);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("user/me")
    Call<Users> getUserDetails(@Header("Authorization") String token);

    @PUT("user/UserUpdateAndroid")
    Call<String> updateProfile(@Header("Authorization") String token,@Body UpdateMod updateMod);
}
