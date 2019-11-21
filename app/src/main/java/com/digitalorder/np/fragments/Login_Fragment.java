package com.digitalorder.np.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalorder.np.Dashboard;
import com.digitalorder.np.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class Login_Fragment extends Fragment {
    TextView edname, edpwd;
    Button btnlogin;

    private String Username, Password;
    boolean status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        btnlogin = view.findViewById(R.id.btnlogin);
        edname = view.findViewById(R.id.edname);
        edpwd = view.findViewById(R.id.edpwd);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Username = edname.getText().toString().trim();
                Password = edpwd.getText().toString().trim();

                if (Username.equals("admin") && Password.equals("admin")) {
                    edname.setText("");
                    edpwd.setText("");
                    Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), Dashboard.class);
                    intent.putExtra(EXTRA_MESSAGE, Username);
                    startActivity(intent);
                } else {
                    edname.setError("Invalid username");
                    edpwd.setError("Invalid password");
                }
            }
        });
        return view;
    }}

