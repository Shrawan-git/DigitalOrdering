package com.shrawan.np.digitalorderwear.Bll;

import com.shrawan.np.digitalorderwear.SignUpResponseWear;
import com.shrawan.np.digitalorderwear.UrlWear;
import com.shrawan.np.digitalorderwear.Users;
import com.shrawan.np.digitalorderwear.UsersAPIWear;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {
    boolean isSuccess = false;

    public boolean checkUser(String name, String password) {

        UsersAPIWear usersAPI = UrlWear.getInstance().create(UsersAPIWear.class);
        Call<SignUpResponseWear> usersCall = usersAPI.checkUserwear(new Users("",name,"","",password, "",""));

        try {
            Response<SignUpResponseWear> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                UrlWear.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}


