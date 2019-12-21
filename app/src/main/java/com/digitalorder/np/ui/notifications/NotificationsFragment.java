package com.digitalorder.np.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.digitalorder.np.R;
import com.squareup.picasso.Picasso;

public class NotificationsFragment extends Fragment {
    private ImageView imageView;
    private String imageUrl = "https://saltpepperskillet.com/wp-content/uploads/perfect-sous-vide-ny-steak-horizontal.jpg";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        imageView = root.findViewById(R.id.imageView);
        Picasso.get().load(imageUrl).into(imageView);
        return root;
    }
}