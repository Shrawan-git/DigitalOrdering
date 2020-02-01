package com.digitalorder.np.ui.aboutus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.digitalorder.np.CurrentLocation;
import com.digitalorder.np.R;

public class AboutFragment extends Fragment {
    private LinearLayout btnlocation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_aboutus, container, false);

        btnlocation = root.findViewById(R.id.btnlocation);
        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CurrentLocation.class);
                startActivity(intent);
            }
        });
        return root;

    }
}
