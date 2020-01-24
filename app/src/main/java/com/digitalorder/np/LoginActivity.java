package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LoginActivity extends AppCompatActivity {

    private EditText edname, edpwd;
    private TextView txtsignup;
    private Button btnlogin;
    private String Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btnlogin);
        edname = findViewById(R.id.edname);
        edpwd = findViewById(R.id.edpwd);
        txtsignup = findViewById(R.id.txtsignup);
        txtsignup.setPaintFlags(txtsignup.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Username = edname.getText().toString().trim();
                Password = edpwd.getText().toString().trim();

                if (Username.equals("admin") && Password.equals("admin")) {
                    edname.setText("");
                    edpwd.setText("");

                    SaveSharedPreferences();

                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, Username);
                    startActivity(intent);
                } else {
                    edname.setError("Invalid username");
                    edpwd.setError("Invalid password");
                }
            }
        });

    }
    private void SaveSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username",Username);
        editor.putString("password", Password);
        editor.commit();

        Toast.makeText(this, "Successfully registered", Toast.LENGTH_SHORT).show();
    }
}


