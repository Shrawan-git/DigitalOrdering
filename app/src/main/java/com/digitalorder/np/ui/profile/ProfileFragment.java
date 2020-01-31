package com.digitalorder.np.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.digitalorder.np.CurrentLocation;
import com.digitalorder.np.R;
import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.UpdateMod;
import com.digitalorder.np.model.Users;
import com.digitalorder.np.serverresponse.SignUpResponse;
import com.digitalorder.np.strictmode.StrictModeClass;
import com.digitalorder.np.ui.home.HomeFragment;
import com.digitalorder.np.url.Url;

import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener {
   private CircleImageView imgProgile;
   private EditText tvName,tvEmail,tvGender;
   private Button update;
   private ImageButton tvLocation;
   String ad;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        imgProgile = root.findViewById(R.id.imgProgile);
        tvName = root.findViewById(R.id.tvName);
        tvEmail = root.findViewById(R.id.tvEmail);
        tvGender = root.findViewById(R.id.tvGender);
        tvLocation = root.findViewById(R.id.tvLocation);

        update = root.findViewById(R.id.update);
        update.setOnClickListener(this);

        loadCurrentUser();
        return root;
    }
    private void loadCurrentUser(){

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);


       // preferences = getSharedPreferences("UserData", 0);

       // String userid = preferences.getString("uid", null);

        Call<Users> userCall = usersAPI.getUserDetails(Url.token);

        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

//
//                Picasso.get().load(imgPath).into(imgProgile);

                StrictModeClass.StrictMode();
                try {
                    String imgPath = Url.imagePath +  response.body().getImage();
                    URL url = new URL(imgPath);
                    imgProgile.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

           //         String userId = response.body().get_id();
                    String Name= response.body().getName();
                    String Email= response.body().getEmail();
                    String Gender= response.body().getGender();

                    tvName.setText(Name);
                    tvEmail.setText(Email);
                    tvGender.setText(Gender);
             //       ad = userId;

         //           Toast.makeText(getActivity(), "User id: +" +ad, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
         }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

//                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CurrentLocation.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        updateProfile();

    }
    public void updateProfile()
    {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);

        String name,email;

//        uid = ad;
        name= tvName.getText().toString();
        email=tvEmail.getText().toString();

       // Toast.makeText(getActivity(), "User id: +" +ad, Toast.LENGTH_SHORT).show();

 //       SharedPreferences preferences=(getActivity()).getSharedPreferences("UserData",0);

//        String userName=preferences.getString("name",null);

        UpdateMod updateMod = new UpdateMod(name, email);

        Call<String> updateProfileData = usersAPI.updateProfile(updateMod);
        updateProfileData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Code "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}