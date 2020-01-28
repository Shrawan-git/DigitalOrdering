package com.digitalorder.np.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.digitalorder.np.CurrentLocation;
import com.digitalorder.np.DashboardActivity;
import com.digitalorder.np.R;
import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.Users;
import com.digitalorder.np.strictmode.StrictModeClass;
import com.digitalorder.np.url.Url;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.digitalorder.np.url.Url.imagePath;

public class NotificationsFragment extends Fragment {
   private CircleImageView imgProgile;
   private TextView tvName,tvEmail,tvGender;
   private ImageButton tvLocation;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        imgProgile = root.findViewById(R.id.imgProgile);
        tvName = root.findViewById(R.id.tvName);
        tvEmail = root.findViewById(R.id.tvEmail);
        tvGender = root.findViewById(R.id.tvGender);
        tvLocation = root.findViewById(R.id.tvLocation);
        loadCurrentUser();
        return root;


    }
    private void loadCurrentUser(){

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
//        final String url = "http://10.0.2.2:3024/uploads/";

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

                    String Name= response.body().getName();
                    String Email= response.body().getEmail();
                    String Gender= response.body().getGender();

                    tvName.setText(Name);
                    tvEmail.setText(Email);
                    tvGender.setText(Gender);

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


}
