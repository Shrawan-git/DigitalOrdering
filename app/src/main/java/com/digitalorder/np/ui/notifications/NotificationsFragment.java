package com.digitalorder.np.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.digitalorder.np.R;
import com.squareup.picasso.Picasso;

public class NotificationsFragment extends Fragment {
    private Switch simpleSwitch;
    SharedPreferences sharedPreferences;
    public static final String Key_isNightMode = "isNightMode";
    public static final String MyPreferences = "nightModePrefs";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        simpleSwitch = root.findViewById(R.id.simpleSwitch);
        checkNightModeActivated();
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);
                    recreate();
                }
            }
        });

        return root;
    }

    private void recreate() {
        getFragmentManager().beginTransaction()
                .detach(NotificationsFragment.this)
                .attach(NotificationsFragment.this)
                .commit();
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Key_isNightMode, nightMode);
        editor.apply();
    }

    private void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(Key_isNightMode, false)) {
            simpleSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            simpleSwitch.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}