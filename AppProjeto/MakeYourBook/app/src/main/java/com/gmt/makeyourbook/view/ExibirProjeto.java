package com.gmt.makeyourbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gmt.makeyourbook.R;

public class ExibirProjeto extends AppCompatActivity {

    private TextView projeto;
    private int cd_projeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_projeto);

        Bundle extras = getIntent().getExtras();
        cd_projeto = Integer.parseInt(extras.getString("cd_projeto"));

        projeto = findViewById(R.id.cd_projeto);

        projeto.setText(String.valueOf(cd_projeto));

    }
}