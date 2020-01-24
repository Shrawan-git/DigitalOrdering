package com.digitalorder.np;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        profile_image= findViewById(R.id.profile_image);

        //check permission
        checkPermission();

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(RegisterActivity.this,profile_image);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().toString().equals("Open Camera")){
                            loadCamera();
                        }
                        if(item.getTitle().toString().equals("Open Gallary")){
                            loadGallary();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }
    private void loadGallary() {
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
        }
    }
    private  void loadCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(this.getPackageManager()) != null){
            startActivityForResult(intent, 0);
        }

    }

}

