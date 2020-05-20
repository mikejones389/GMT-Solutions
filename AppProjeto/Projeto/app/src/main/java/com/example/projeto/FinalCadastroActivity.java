package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.projeto.services.MySingleton;
import com.facebook.login.LoginManager;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FinalCadastroActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView nmUsuario;
    private String valor;
    private TextView myDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView idadeF;
    private int idade;
    private int id=0;;

        private String server_url = "http://192.168.0.110/update_info.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int idadef = idade;
        int idf = id;
        String nm = valor;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_cadastro);
        nmUsuario = findViewById(R.id.nmUsuario);
        valor = getIntent().getStringExtra("nmUsuario");
        nmUsuario.setText("Esse é o último passo "+ valor);
        myDate = findViewById(R.id.date);
        idadeF = (TextView) findViewById(R.id.txtIdade);

        myDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(FinalCadastroActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet : mm/dd/yyy:" + year + "/" + (month+1) + "/" + dayOfMonth);
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                myDate.setText(date);
                calcularIdade(dayOfMonth, month , year);
            }
        };
    }

    @SuppressLint("SetTextI18n")
    public void calcularIdade(int dayN, int monthN, int yearN ){
        Calendar calendar = Calendar.getInstance();
        int yearA = calendar.get(Calendar.YEAR);
        int monthA = calendar.get(Calendar.MONTH);
        int dayA = calendar.get(Calendar.DAY_OF_MONTH);

        idade = yearA - yearN;
        if(monthN > monthA){
            idade--;
        }
        else if(monthA == monthN){
            if(dayA < dayN){
                idade--;
            }
        }
        if(idade <= 11){
            Toast.makeText(FinalCadastroActivity.this, "Data invalida", Toast.LENGTH_SHORT).show();
        }
        else{
            this.idadeF.setText("Sua idade é " + idade + " anos");
        }
    }

    public void continuar(View view){
        if(!TextUtils.isEmpty(idadeF.getText().toString())){
            id++;
            salvarNoBanco();
        }
        else {
            Toast.makeText(FinalCadastroActivity.this, "Por favor, insira uma data válida", Toast.LENGTH_SHORT).show();
        }
    }

    public void logout(View view){
        LoginManager.getInstance().logOut();
        goSplashScreen();
    }

    public void goMenuPrincipal(){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goSplashScreen(){
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void salvarNoBanco() {
        final String id,name, idade;
        id = String.valueOf(this.id);
        name = valor;
        idade = String.valueOf(this.idade);
        Log.i("TAG", id+ name+ idade);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                params.put("id", id);
                params.put("name", name);
                params.put("idade",idade);
                return params;
            }
        };

        MySingleton.getInstance(FinalCadastroActivity.this).addTorequestque(stringRequest);
    }


}
