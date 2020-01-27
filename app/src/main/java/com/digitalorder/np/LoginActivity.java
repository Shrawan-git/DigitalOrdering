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

import com.digitalorder.np.bll.LoginBLL;
import com.digitalorder.np.strictmode.StrictModeClass;

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
        txtsignup.setPaintFlags(txtsignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

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

                login();
            }
        });
    }
    private void login(){
        String name = edname.getText().toString();
        String password = edpwd.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(name, password)) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            edname.requestFocus();
        }
    }
//    private void SaveSharedPreferences() {
//        SharedPreferences sharedPreferences = this.getSharedPreferences("User", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("username",Username);
//        editor.putString("password", Password);
//        editor.commit();
//
//        Toast.makeText(this, "Successfully registered", Toast.LENGTH_SHORT).show();
//    }
}


