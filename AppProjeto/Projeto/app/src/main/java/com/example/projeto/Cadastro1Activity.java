package com.example.projeto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.login.LoginManager;

public class Cadastro1Activity extends AppCompatActivity {

    private ImageView imageView;
    private Button galeria;
    private final int GALERIA_MEDIA = 1;
    private final int PERMISSAO_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro1);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }

        galeria = findViewById(R.id.btSelecionarImg);
        imageView = findViewById(R.id.profile_image);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, GALERIA_MEDIA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == GALERIA_MEDIA){
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage,filePath,null,null,null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap imagemGaleria = (BitmapFactory.decodeFile(picturePath));
            imageView.setImageBitmap(imagemGaleria);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSAO_REQUEST) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //permissão para acessar a camera concedida
            }
            else{
                //permissão para acessar a camera negada
            }
            return;
        }
    }

    public void logout(View view){
        LoginManager.getInstance().logOut();
        goSplashScreen();
    }

    private void goSplashScreen(){
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
