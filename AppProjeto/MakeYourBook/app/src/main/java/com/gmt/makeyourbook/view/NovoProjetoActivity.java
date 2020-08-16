package com.gmt.makeyourbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.view.fragment.ProfileFragment;

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

import javax.xml.parsers.SAXParser;

public class NovoProjetoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edtTitulo;
    private Spinner spinner_genero;
    private EditText txtHistoria;

    private String genero, titulo, historia;
    private int cd_usuario, cd_projeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_projeto);

        edtTitulo = (EditText) findViewById(R.id.tituloProjeto);
        txtHistoria = (EditText) findViewById(R.id.txtHistoria);
        spinner_genero = (Spinner) findViewById(R.id.spinner_genero);
        spinner_genero.setOnItemSelectedListener(this);

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        cd_usuario = preferences.getInt("cd_usuario", 0);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        genero = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void salvarProjeto(View view){
        titulo = edtTitulo.getText().toString();
        historia = txtHistoria.getText().toString();

        if(titulo.equals("")){
            edtTitulo.requestFocus();
        }
        else if(genero.equals("Selecione")){
            spinner_genero.requestFocus();
        }
        else if(historia.equals("")){
            txtHistoria.requestFocus();
        }
        else{
            SalvarAsyncTask task = new SalvarAsyncTask("salvarProjeto", cd_usuario, titulo, genero, historia);
            task.execute();
            this.finish();


        }

    }

    public void cancelar(View view){
        txtHistoria.setText("");
        edtTitulo.setText("");
        edtTitulo.requestFocus();
        this.finish();
    }

    public class SalvarAsyncTask
            extends
            AsyncTask<String, String, String> {

        String api_token, query, api_titulo, api_genero, api_historia;
        int api_cd_usuario;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public SalvarAsyncTask(String token, int cd_usuario, String titulo, String genero, String historia){

            this.api_token = token;
            this.api_cd_usuario = cd_usuario;
            this.api_titulo= titulo;
            this.api_genero = genero;
            this.api_historia= historia;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_cd_usuario", String.valueOf(cd_usuario));
            builder.appendQueryParameter("api_titulo", String.valueOf(titulo));
            builder.appendQueryParameter("api_genero", String.valueOf(genero));
            builder.appendQueryParameter("api_historia", String.valueOf(historia));

        }

        @Override
        protected void onPreExecute(){

            Log.i("APICadastrar","onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APICadastrar","doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            }catch (MalformedURLException e){

                Log.i("APICadastrar","doInBackground() --> "+e.getMessage());

            }catch (Exception e){

                Log.i("APICadastrar","doInBackground() --> "+e.getMessage());
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

                Log.i("APICadastrar","doInBackground() --> "+e.getMessage());

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

                Log.i("APICadastrar","doInBackground() --> "+e.getMessage());


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

                Log.i("APICadastrar","doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }


            return "Processamento concluído...";


        }

        @Override
        protected void onPostExecute(String result){

            Log.i("APICadastrar","onPostExecute()--> Result: "+result);

            try{

                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.getBoolean("Cadastro")){
                    Log.i("APICadastrar", "onPostExecute() --> Cadastro bem Sucedido"+jsonObject.getString("CD_PROJETO"));
                    cd_projeto = jsonObject.getInt("CD_PROJETO");
                    Log.i("APICadastrar", "onPostExecute() --> ID Projeto"+cd_projeto);
                    Toast.makeText(getApplicationContext(), "Cadastro bem Sucedido", Toast.LENGTH_LONG);
//                    validar = 1;
                }
                else{
                    Log.i("APICadastrar","onPostExecute()--> Cadastro Falhou");
                    Log.i("APICadastrar","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
//                    validar = 0;
                }

            }catch (Exception e){
                Log.i("APICadastrar","onPostExecute()--> : "+e.getMessage());
                Toast.makeText(getApplicationContext(), "Cadastro Falhou", Toast.LENGTH_LONG);
            }
//            goMenuPrincipal();
//            Log.i("VALIDAÇÂO", "Validar --> "+validar);
        }
    }

}