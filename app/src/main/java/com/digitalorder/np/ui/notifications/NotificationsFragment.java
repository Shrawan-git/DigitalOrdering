package com.digitalorder.np.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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

import com.digitalorder.np.DashboardActivity;
import com.digitalorder.np.R;
import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.Users;
import com.digitalorder.np.strictmode.StrictModeClass;
import com.digitalorder.np.url.Url;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.digitalorder.np.url.Url.imagePath;

public class NotificationsFragment extends Fragment {
   private ImageView imgProgile;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        imgProgile = root.findViewById(R.id.imgProgile);
        loadCurrentUser();
        return root;


    }
    private void loadCurrentUser(){

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        final String url = "http://10.0.2.2:3024//uploads/";

        Call<Users> userCall = usersAPI.getUserDetails(Url.token);

        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = url +  response.body().getImage();

                Picasso.get().load(imgPath).into(imgProgile);

                StrictModeClass.StrictMode();
                try {
                    URL url = new URL(imagePath);
                    imgProgile.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
         }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

//                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
