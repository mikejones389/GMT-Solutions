package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.projeto.services.MySingleton;
import com.facebook.login.LoginManager;

import java.util.HashMap;
import java.util.Map;

public class FinalCadastroActivity extends AppCompatActivity {


    private EditText login;
    private EditText senha;
    private String nmUsuario;

    private String server_url = "http://192.168.0.110/update_info.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_cadastro);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        nmUsuario = getIntent().getStringExtra("nmUsuario");
        
    }

    public void continuar(View view){
        if(!TextUtils.isEmpty(login.getText().toString()) && !TextUtils.isEmpty(senha.getText().toString())){
            salvarNoBanco();
            goMenuActivity();
        }

    }

    public void goMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    public void logout(View view){
        LoginManager.getInstance().logOut();
        goSplashScreen();
    }

    private void goSplashScreen() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void salvarNoBanco() {
        final String name,login,senha;
        name = nmUsuario;
        login = this.login.getText().toString();
        senha = this.senha.getText().toString();
        Log.i("TAG", name + login + senha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(FinalCadastroActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
//                builder.setTitle("Server Response");
//                builder.setMessage("Response :"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FinalCadastroActivity.this, "Error..."+error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("name", name);
                params.put("login", login);
                params.put("senha",senha);
                return params;
            }
        };

        MySingleton.getInstance(FinalCadastroActivity.this).addTorequestque(stringRequest);
    }
}
