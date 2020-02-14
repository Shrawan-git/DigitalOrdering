package com.digitalorder.np;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.bll.RegisterBLL;
import com.digitalorder.np.model.Users;
import com.digitalorder.np.serverresponse.ImageResponse;
import com.digitalorder.np.serverresponse.SignUpResponse;
import com.digitalorder.np.strictmode.StrictModeClass;
import com.digitalorder.np.url.Url;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private CircleImageView profile_image;
    private EditText etfullname,etname,etpwd,etemail,etphone, etcpwd;
    private Button btn;
    private RadioGroup myRadioGroup;
    private RadioButton male,female,others;
    String imagePath;
    String gender;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        profile_image = findViewById(R.id.profile_image);
        etfullname = findViewById(R.id.etfullname);
        etname = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        etphone = findViewById(R.id.etphone);
        etpwd = findViewById(R.id.etpwd);
        etcpwd = findViewById(R.id.etcpwd);
        myRadioGroup = findViewById(R.id.myRadioGroup);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        others = findViewById(R.id.others);
        btn = findViewById(R.id.btn);

        //check permission
        checkPermission();

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(RegisterActivity.this, profile_image);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Open Camera")) {
                            loadCamera();
                        }
                        if (item.getTitle().toString().equals("Open Gallery")) {
                            loadGallery();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etpwd.getText().toString().equals(etcpwd.getText().toString())) {
                    if (validate()) {
                        if(validateEmail()) {
                            if (validatePhone()) {
                                saveImageOnly();
                                signUp();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etpwd.requestFocus();
                    return;
                }
            }
                }
        });
    }
    private boolean validatePhone() {
        boolean status=true;
        if (etphone.getText().toString().length() < 10 || etphone.length() > 13) {
            etphone.setError("Not a valid phone number");
            status = false;
        }
            return status;
        }

    private boolean validateEmail() {
        String regEmail = etemail.getText().toString().trim();
        if (regEmail.isEmpty()) {
            etemail.setError("Required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(regEmail).matches()) {
            etemail.setError("Please enter a valid email");
            return false;
        } else {
            etemail.setError(null);
            return true;
        }
    }

    private boolean validate() {
        boolean status=true;
        if (etname.getText().toString().length() < 6) {
            etname.setError("Minimum 6 character");
            status=false;
        }
        return status;
    }

    private void loadGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
    private  void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.CAMERA
                    },0);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },1);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0 && resultCode==RESULT_OK){
            Bundle extras= data.getExtras();
            Bitmap imageBitmap=(Bitmap)extras.get("data");
            profile_image.setImageBitmap(imageBitmap);
        }
        if(requestCode==1 && resultCode==RESULT_OK) {

            if (data == null) {
                Toast.makeText(this, "Plese select an image", Toast.LENGTH_LONG).show();
            }
            Uri uri = data.getData();
            profile_image.setImageURI(uri);
            imagePath = getRealPathFromUri(uri);
        }

    }
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    public  void signUp(){
        String fullname = etfullname.getText().toString().trim();
        String name = etname.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String phone = etphone.getText().toString().trim();
        String password = etpwd.getText().toString().trim();

        int selectGender = myRadioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectGender);
        gender= radioButton.getText().toString().trim();

        RegisterBLL registerBLL = new RegisterBLL();

        StrictModeClass.StrictMode();
        if (registerBLL.signupUser(fullname, name, email, phone, password, imageName, gender)) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


    private  void loadCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(this.getPackageManager()) != null){
            startActivityForResult(intent, 0);
        }

    }
    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}



