package com.digitalorder.np.bll;

import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.Users;
import com.digitalorder.np.serverresponse.SignUpResponse;
import com.digitalorder.np.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBLL {
    private boolean isSuccess = false;

    public boolean signupUser(String fullname, String name, String email, String phone, String password, String image, String gender){
        Users users = new Users(fullname, name, email, phone, password, image, gender);
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);

        Call<SignUpResponse> registerResponseCall = usersAPI.registerUser(users);

        try{
            Response<SignUpResponse> signUpResponseResponse = registerResponseCall.execute();
            if(signUpResponseResponse.isSuccessful() && signUpResponseResponse.body().getStatus().equals("Signup success!")){
                Url.token += signUpResponseResponse.body().getToken();
                isSuccess = true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return  isSuccess;
    }
}