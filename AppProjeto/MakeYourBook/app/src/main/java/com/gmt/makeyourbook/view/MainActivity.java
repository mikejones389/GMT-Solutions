package com.gmt.makeyourbook.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    private TextView resultado;
    private String nm_usuario;
    private String sexo;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.texto_resultado);

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        cd_usuario = preferences.getInt("cd_usuario", 0);

        configuraBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new MenuFragment()).commit();


        ConsultarAsyncTask task = new ConsultarAsyncTask("consultar", cd_usuario);
        task.execute();

    }

    private void configuraBottomNavigationView(){
        //faz configurações de animação do bottomNavigation
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottom_navigation);
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableShiftingMode(true);
        bottomNavigationViewEx.enableItemShiftingMode(false);

        //Habilitar Navegação
        habilitarNavegacao(bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(1);
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

    public class ConsultarAsyncTask
            extends
            AsyncTask<String, String, String> {

        String api_token, query;
        int api_cd_usuario;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public ConsultarAsyncTask(String token, int cd_usuario){

            this.api_token = token;
            this.api_cd_usuario = cd_usuario;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_cd_usuario",String.valueOf(cd_usuario));
//            builder.appendQueryParameter("api_senha", String.valueOf(senha));

        }

        @Override
        protected void onPreExecute(){

            Log.i("APIConsultar","onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIConsultar","doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            }catch (MalformedURLException e){

                Log.i("APIConsultar","doInBackground() --> "+e.getMessage());

            }catch (Exception e){

                Log.i("APIConsultar","doInBackground() --> "+e.getMessage());
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

                Log.i("APIConsultar","doInBackground() --> "+e.getMessage());

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

                Log.i("APIConsultar","doInBackground() --> "+e.getMessage());


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

                Log.i("APIConsultar","doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }


            return "Processamento concluído...";


        }

        @Override
        protected void onPostExecute(String result){

            Log.i("APIConsultar","onPostExecute()--> Result: "+result);

            try{

                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.getBoolean("RESULTADO")){
                    Log.i("APIConsultar", "onPostExecute() --> Login bem Sucedido"+jsonObject.getString("ID"));
                    nm_usuario = jsonObject.getString("nm_usuario");
                    cd_usuario = Integer.parseInt(jsonObject.getString("ID"));
                    sexo = jsonObject.getString("SEXO");
                    Log.i("APIConsultar", "onPostExecute() --> ID Login"+cd_usuario);
                    Log.i("APIConsultar", "onPostExecute() --> ID NOME"+nm_usuario);
                    Log.i("APIConsultar", "onPostExecute() --> ID SEXO"+sexo);
                    Toast.makeText(getApplicationContext(), "Login bem Sucedido", Toast.LENGTH_LONG);
                }
                else{
                    Log.i("APIConsultar","onPostExecute()--> Login Falhou");
                    Log.i("APIConsultar","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
                }

            }catch (Exception e){
                Log.i("APIConsultar","onPostExecute()--> : "+e.getMessage());
                Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
            }
            //setInformation();

        }
    }

    public void setInformation(){
        resultado.setText("ID: "+cd_usuario+" Nome :"+ nm_usuario+" Sexo: "+sexo);
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
}