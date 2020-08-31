package com.gmt.makeyourbook.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.accessibilityservice.FingerprintGestureController;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.view.fragment.MenuFragment;
import com.gmt.makeyourbook.view.fragment.ProfileFragment;
import com.gmt.makeyourbook.view.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private int cd_usuario;
    private AlertDialog alerta;
    private Dialog dialogAvatar;
    private String positionFragment = "Menu";
    private int positionIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atualizar();

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        cd_usuario = preferences.getInt("cd_usuario", 0);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            positionFragment = extras.getString("position");
        }

        if(positionFragment.equals("Perfil")){
            positionIcon = 0;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.viewPager, new ProfileFragment()).commit();
        }
        else if(positionFragment.equals("Menu")){
            positionIcon = 1;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.viewPager, new MenuFragment()).commit();
        }
        else{
            positionIcon = 3;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.viewPager, new SettingsFragment()).commit();
        }

        configuraBottomNavigationView(positionIcon);

        dialogAvatar = new Dialog(this);


    }

    private void configuraBottomNavigationView(int positionIcon){

        int iconPosition = positionIcon;
        //faz configurações de animação do bottomNavigation
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottom_navigation);
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableShiftingMode(true);
        bottomNavigationViewEx.enableItemShiftingMode(false);

        //Habilitar Navegação
        habilitarNavegacao(bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(iconPosition);
        menuItem.setChecked(true);

    }

    private void habilitarNavegacao(BottomNavigationViewEx viewEx){

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()){
                    case R.id.ic_profile:
                        fragmentTransaction.replace(R.id.viewPager, new ProfileFragment()).commit();
                        return true;
                    case R.id.ic_home:
                        fragmentTransaction.replace(R.id.viewPager, new MenuFragment()).commit();
                        return true;
                    case R.id.ic_settings:
                        fragmentTransaction.replace(R.id.viewPager, new SettingsFragment()).commit();
                        return true;
                }

                return false;
            }
        });

    }

    public void logout (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmação de Logout");
        builder.setMessage("Realizar Logout?");

        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("ja_fez_login", false);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_LONG);
            }
        });
        alerta = builder.create();
        alerta.show();

    }

    public void newProjeto(View view){
        Intent intent = new Intent(getApplicationContext(), NovoProjetoActivity.class);
        startActivity(intent);
    }

    public void alterarAvatar(View view){
        ImageView  avatar1, avatar2, avatar3, avatar4, avatar5, avatar6;
        Button btCancelar;

        dialogAvatar.setContentView(R.layout.popup_seletor_img_perfil);

        avatar1 = dialogAvatar.findViewById(R.id.avatar1);
        avatar2 = dialogAvatar.findViewById(R.id.avatar2);
        avatar3 = dialogAvatar.findViewById(R.id.avatar3);
        avatar4 = dialogAvatar.findViewById(R.id.avatar4);
        avatar5 = dialogAvatar.findViewById(R.id.avatar5);
        avatar6 = dialogAvatar.findViewById(R.id.avatar6);
        btCancelar = dialogAvatar.findViewById(R.id.btCancelar);

        final SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("avatar", 1);
                editor.commit();
                int avatar = preferences.getInt("avatar", 0);

                SalvarAvatarAsynkTask salvarAvatarAsynkTask = new SalvarAvatarAsynkTask("salvarAvatar", avatar, cd_usuario);
                salvarAvatarAsynkTask.execute();

                atualizar();
                dialogAvatar.dismiss();
            }
        });

        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("avatar", 2);
                editor.commit();
                int avatar = preferences.getInt("avatar", 0);

                SalvarAvatarAsynkTask salvarAvatarAsynkTask = new SalvarAvatarAsynkTask("salvarAvatar", avatar, cd_usuario);
                salvarAvatarAsynkTask.execute();

                atualizar();
                dialogAvatar.dismiss();
            }
        });

        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("avatar", 3);
                editor.commit();
                int avatar = preferences.getInt("avatar", 0);

                SalvarAvatarAsynkTask salvarAvatarAsynkTask = new SalvarAvatarAsynkTask("salvarAvatar", avatar, cd_usuario);
                salvarAvatarAsynkTask.execute();

                atualizar();
                dialogAvatar.dismiss();
            }
        });

        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("avatar", 4);
                editor.commit();
                int avatar = preferences.getInt("avatar", 0);

                SalvarAvatarAsynkTask salvarAvatarAsynkTask = new SalvarAvatarAsynkTask("salvarAvatar", avatar, cd_usuario);
                salvarAvatarAsynkTask.execute();

                atualizar();
                dialogAvatar.dismiss();
            }
        });

        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("avatar", 5);
                editor.commit();
                int avatar = preferences.getInt("avatar", 0);

                SalvarAvatarAsynkTask salvarAvatarAsynkTask = new SalvarAvatarAsynkTask("salvarAvatar", avatar, cd_usuario);
                salvarAvatarAsynkTask.execute();

                atualizar();
                dialogAvatar.dismiss();
            }
        });

        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("avatar", 6);
                editor.commit();
                int avatar = preferences.getInt("avatar", 0);

                SalvarAvatarAsynkTask salvarAvatarAsynkTask = new SalvarAvatarAsynkTask("salvarAvatar", avatar, cd_usuario);
                salvarAvatarAsynkTask.execute();

                atualizar();
                dialogAvatar.dismiss();
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAvatar.dismiss();
            }
        });


//        int avatar = preferences.getInt("avatar", 0);
//
//        SalvarAvatarAsynkTask salvarAvatarAsynkTask = new SalvarAvatarAsynkTask("salvarAvatar", avatar, cd_usuario);
//        salvarAvatarAsynkTask.execute();

        dialogAvatar.show();


    }

    public class SalvarAvatarAsynkTask extends AsyncTask<String, String, String>{

        String api_token, query;
        int api_avatar, api_cd_usuario;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public SalvarAvatarAsynkTask(String token, int avatar, int cd_usuario){

            this.api_token = token;
            this.api_avatar = avatar;
            this.api_cd_usuario = cd_usuario;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_avatar", String.valueOf(avatar));
            builder.appendQueryParameter("api_cd_usuario", String.valueOf(cd_usuario));

        }

        @Override
        protected void onPreExecute(){

            Log.i("APISalvarAvatar","onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APISalvarAvatar","doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            }catch (MalformedURLException e){

                Log.i("APISalvarAvatar","doInBackground() --> "+e.getMessage());

            }catch (Exception e){

                Log.i("APISalvarAvatar","doInBackground() --> "+e.getMessage());
            }

            // Gerar uma requisição HTTP - POST - Result será um ArrayJson

            // conn

            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset","utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();

            }catch (Exception e){

                Log.i("APISalvarAvatar","doInBackground() --> "+e.getMessage());

            }

            // Adicionar o TOKEN e/ou outros parâmetros como por exemplo
            // um objeto a ser incluido, deletado ou alterado.
            // CRUD completo

            try {

                query = builder.build().getEncodedQuery();

                OutputStream stream = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(stream,"utf-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                stream.close();

                conn.connect();


            }catch (Exception e){

                Log.i("APISalvarAvatar","doInBackground() --> "+e.getMessage());


            }

            // receber o response - arrayJson
            // http - código do response | 200 | 404 | 503

            try {

                response_code = conn.getResponseCode();

                if(response_code == HttpURLConnection.HTTP_OK){

                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(input)
                    );

                    StringBuilder result = new StringBuilder();

                    String linha = null;

                    while((linha = reader.readLine()) != null){
                        result.append(linha);
                    }

                    return result.toString();

                }
                else{
                    return "HTTP ERRO:"+response_code;
                }

            }catch (Exception e){

                Log.i("APISalvarAvatar","doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }


            return "Processamento concluído...";


        }

        @Override
        protected void onPostExecute(String result){

            Log.i("APISalvarAvatar","onPostExecute()--> Result: "+result);

            try{

                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.getBoolean("RESULTADO")){
                    Log.i("APISalvarAvatar", "onPostExecute() --> Avatar Salvo com sucesso"+jsonObject.getString("ID"));
                }
                else{
                    Log.i("APISalvarAvatar","onPostExecute()--> Falha ao salvar avatar");
                    Log.i("APISalvarAvatar","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
                }

            }catch (Exception e){
                Log.i("APISalvarAvatar","onPostExecute()--> : "+e.getMessage());
                Toast.makeText(getApplicationContext(), "Falha ao salvar avatar", Toast.LENGTH_LONG);
            }
            //setInformation();


        }
    }

    public void atualizar(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new ProfileFragment()).commit();
    }
}