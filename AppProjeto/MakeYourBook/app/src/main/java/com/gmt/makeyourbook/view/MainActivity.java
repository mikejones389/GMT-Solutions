package com.gmt.makeyourbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    private int cd_usuario;
    private TextView resultado;
    private String nm_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.texto_resultado);

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        cd_usuario = preferences.getInt("cd_usuario", 0);

        ConsultarAsyncTask task = new ConsultarAsyncTask("consultar", cd_usuario);
        task.execute();


        int pausa = 0;
    }

    public class ConsultarAsyncTask
            extends
            AsyncTask<String, String, String> {

        String api_token, query, api_nm_usuario, api_login, api_senha;
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
//            this.api_nm_usuario = nm_usuario;
//            this.api_login = login;
//            this.api_senha = senha;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_cd_usuario",String.valueOf(cd_usuario));
//            builder.appendQueryParameter("api_senha", String.valueOf(senha));

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

            }catch (Exception e){

                Log.i("APIValidarLogin","doInBackground() --> "+e.getMessage());
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

                if(jsonObject.getBoolean("RESULTADO")){
                    Log.i("APIValidarLogin", "onPostExecute() --> Login bem Sucedido"+jsonObject.getString("ID"));
                    nm_usuario = jsonObject.getString("nm_usuario");
                    cd_usuario = Integer.parseInt(jsonObject.getString("ID"));
                    Log.i("APIValidarLogin", "onPostExecute() --> ID Login"+cd_usuario);
                    Log.i("APIValidarLogin", "onPostExecute() --> ID NOME"+nm_usuario);
                    Toast.makeText(getApplicationContext(), "Login bem Sucedido", Toast.LENGTH_LONG);
                }
                else{
                    Log.i("APIValidarLogin","onPostExecute()--> Login Falhou");
                    Log.i("APIValidarLogin","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
                }

            }catch (Exception e){
                Log.i("APIValidarLogin","onPostExecute()--> : "+e.getMessage());
                Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
            }
            setInformation();

        }
    }

    public void setInformation(){
        resultado.setText("ID: "+cd_usuario+"nome :"+ nm_usuario);
    }

    public void logout (View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();

    }

}