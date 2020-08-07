package com.gmt.makeyourbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.gmt.makeyourbook.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //colocar a condição para o login
                SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
                boolean validacao = preferences.getBoolean("ja_fez_login", false);
                Toast.makeText(getApplicationContext(), "Validação: "+validacao, Toast.LENGTH_LONG).show();
                if(validacao){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },5000);

    }
}