package com.digitalorder.np.bll;

import android.widget.Toast;

import com.digitalorder.np.Onclickfood;
import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.Product;
import com.digitalorder.np.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderBLL{
    private boolean isSuccess = false;

    public boolean orderUser(String foodName, String foodPrice, String foodCategory) {
        Product product = new Product(foodName, foodPrice, foodCategory);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Void> orderCall = usersAPI.orderUser(Url.token, product);

        orderCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    isSuccess = true;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        return  isSuccess;
    }
}
