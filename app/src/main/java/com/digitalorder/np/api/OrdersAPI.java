package com.digitalorder.np.api;

import com.digitalorder.np.model.OrderMod;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrdersAPI {
    @POST("order")
    Call<Void> orderFood(@Body OrderMod orderMod);
}
