package com.gmt.makeyourbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gmt.makeyourbook.R;

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

public class LoginActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Animation fromButtons, fromBoxLoginEntrada;
    private ConstraintLayout constraintLayout;
    private Button btLogin, btCancelar, btLogin2;
    private EditText edtLogin, edtSenha;
    private String login, senha;
    private int validar ;
    private int id;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relativeLayout = (RelativeLayout) findViewById(R.id.rellay1);
        fromButtons = AnimationUtils.loadAnimation(this, R.anim.animation_login_bottom);
        fromBoxLoginEntrada = AnimationUtils.loadAnimation(this, R.anim.animation_entrada_login_box);
        relativeLayout.setAnimation(fromButtons);
        btLogin = findViewById(R.id.bt_login);
        btCancelar = findViewById(R.id.bt_cancelar);
        constraintLayout = (ConstraintLayout) findViewById(R.id.layoutLogin);
        btLogin2 = findViewById(R.id.bt_login2);
        edtLogin = findViewById(R.id.edt_login2);
        edtSenha = findViewById(R.id.edt_senha2);
        texto = findViewById(R.id.texto);
        //login = edtLogin.getText().toString();
        //senha = edtSenha.getText().toString();


        btLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = edtLogin.getText().toString();
                senha = edtSenha.getText().toString();
                ValidarLoginAsyncTask task = new ValidarLoginAsyncTask("validarLogin", login, senha);
                task.execute();
                goMenuPrincipal();
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout.setVisibility(View.VISIBLE);
                constraintLayout.startAnimation(fromBoxLoginEntrada);
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout.setVisibility(View.GONE);
            }
        });

    }

    public void newConta(View view) {
        Intent i = new Intent(this, CadastroActivity.class);
        startActivity(i);
        finish();
    }

    public class ValidarLoginAsyncTask
            extends
            AsyncTask<String, String, String>{

        String api_token, query, api_login, api_senha;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

//        final String URL_WEB_SERVICES = "http://192.168.56.1/apiCurso/apiValidarLogin.php";
        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public ValidarLoginAsyncTask(String token, String login, String senha){

            this.api_token = token;
            this.api_login = login;
            this.api_senha = senha;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_login", String.valueOf(login));
            builder.appendQueryParameter("api_senha", String.valueOf(senha));

        }

        @Override
        protected void onPreExecute(){

            Log.i("APIValidarLogin","onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIValidarLogin","doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            }catch (MalformedURLException e){

                Log.i("APIValidarLogin","doInBackground() --> "+e.getMessage());
                texto.setVisibility(View.VISIBLE);

            }catch (Exception e){

                Log.i("APIValidarLogin","doInBackground() --> "+e.getMessage());
                texto.setVisibility(View.VISIBLE);
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

                Log.i("APIValidarLogin","doInBackground() --> "+e.getMessage());
                texto.setVisibility(View.VISIBLE);
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

                Log.i("APIValidarLogin","doInBackground() --> "+e.getMessage());


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

                Log.i("APIValidarLogin","doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }


            return "Processamento concluído...";


        }

        @Override
        protected void onPostExecute(String result){

            Log.i("APIValidarLogin","onPostExecute()--> Result: "+result);

            try{

                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.getBoolean("Login")){
                    Log.i("APIValidarLogin", "onPostExecute() --> Login bem Sucedido"+jsonObject.getString("ID"));
                    id = jsonObject.getInt("ID");
                    Log.i("APIValidarLogin", "onPostExecute() --> ID Login"+id);
                    Toast.makeText(getApplicationContext(), "Login bem Sucedido", Toast.LENGTH_LONG);
                    validar = 1;
                }
                else{
                    Log.i("APIValidarLogin","onPostExecute()--> Login Falhou");
                    Log.i("APIValidarLogin","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    texto.setVisibility(View.VISIBLE);
                    edtLogin.setText("");
                    edtSenha.setText("");
                    edtLogin.requestFocus();
                    validar = 0;
                }

            }catch (Exception e){
                Log.i("APIValidarLogin","onPostExecute()--> : "+e.getMessage());
                Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
            }
            goMenuPrincipal();
            Log.i("VALIDAÇÂO", "Validar --> "+validar);
        }
    }

    public void goMenuPrincipal() {
        Log.i("menuPrincipal", "estou no menu -->"+validar);
        if(validar == 1){
            SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("ja_fez_login", true);
            //SharedPreferences.Editor editor2 = preferences.edit();
            editor.putInt("cd_usuario", id);
            editor.commit();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }
    }
}