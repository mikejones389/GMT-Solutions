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

import com.facebook.login.LoginManager;

import java.util.Calendar;

public class DataNascimentoActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView nmUsuario;
    private String valor;
    private TextView myDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView idadeF;
    private int idade;
    private int id = 0;
    ;

    private String server_url = "http://192.168.0.110/update_info.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int idadef = idade;
        int idf = id;
        String nm = valor;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_nascimento);
        nmUsuario = findViewById(R.id.nmUsuario);
        valor = getIntent().getStringExtra("nmUsuario");
        nmUsuario.setText("Esse é o último passo " + valor);
        myDate = findViewById(R.id.date);
        idadeF = (TextView) findViewById(R.id.txtIdade);

        myDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(DataNascimentoActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet : mm/dd/yyy:" + year + "/" + (month + 1) + "/" + dayOfMonth);
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                myDate.setText(date);
                calcularIdade(dayOfMonth, month, year);
            }
        };
    }

    @SuppressLint("SetTextI18n")
    public void calcularIdade(int dayN, int monthN, int yearN) {
        Calendar calendar = Calendar.getInstance();
        int yearA = calendar.get(Calendar.YEAR);
        int monthA = calendar.get(Calendar.MONTH);
        int dayA = calendar.get(Calendar.DAY_OF_MONTH);

        idade = yearA - yearN;
        if (monthN > monthA) {
            idade--;
        } else if (monthA == monthN) {
            if (dayA < dayN) {
                idade--;
            }
        }
        if (idade <= 11) {
            Toast.makeText(DataNascimentoActivity.this, "Data invalida", Toast.LENGTH_SHORT).show();
        } else {
            this.idadeF.setText("Sua idade é " + idade + " anos");
        }
    }

    public void continuar(View view) {
        if (!TextUtils.isEmpty(idadeF.getText().toString())) {
            id++;
            goCadastroUsuarioLogin();
        } else {
            Toast.makeText(DataNascimentoActivity.this, "Por favor, insira uma data válida", Toast.LENGTH_SHORT).show();
        }
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goSplashScreen();
    }

    public void goCadastroUsuarioLogin() {
        Intent intent = new Intent(this, FinalCadastroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("nmUsuario", valor);
        startActivity(intent);
    }

    public void goSplashScreen() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
