package com.example.projeto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FinalCadastroActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView nmUsuario;
    private String valor;
    private TextView myDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView idadeF;
    private int idade;
    private int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            goMenuPrincipal();
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

    private boolean salvarNoBanco(){
        boolean salvar = false;
        URL url = null;
        try {
            url = new URL("");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection conn = null;
        StringBuilder json = new StringBuilder();
        try {
            String jjson = "{\"id\":12,\"imagem\":[],\"titulo\":\"Mano\",\"valor\":1000.0}";
            conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setFixedLengthStreamingMode(jjson.length());
            conn.connect();

            OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            os.write(jjson);
            os.flush();
            os.close();

            InputStream it = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(it));
            String linha;
            while((linha = br.readLine()) != null){
                json.append(linha);
            }

            final String jaba = json.toString();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tela.setText(jaba);
                }
            });

            conn.disconnect();
        }catch (IOException e){
            conn.disconnect();
            e.printStackTrace();
        }

    return salvar;
    }

}
