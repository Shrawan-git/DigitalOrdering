package com.shrawan.np.digitalorderwear;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UrlWear {

//    public static final String base_url="http://10.0.2.2:3024/";
    public static final String base_url="http://192.168.123.10:3024";
    public static String token = "Bearer ";
    public static String imagePath = base_url + "uploads/";

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
