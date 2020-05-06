package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.login.LoginManager;

public class NomeUsuarioActivity extends AppCompatActivity  {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_usuario);
        editText = findViewById(R.id.nmUsuario);
    }

    public void continuar(View view){
        goFotoScreen();
    }
    public void logout(View view){
        LoginManager.getInstance().logOut();
        goSplashScreen();
    }

    private void goFotoScreen(){
        Intent intent = new Intent(this,  FotoDePerfilActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("nmUsuario", editText.getText().toString());
        startActivity(intent);
    }

    private void goSplashScreen(){
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
