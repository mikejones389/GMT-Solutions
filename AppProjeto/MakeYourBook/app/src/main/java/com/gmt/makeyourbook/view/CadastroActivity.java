package com.gmt.makeyourbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editSexo;
    private EditText editDtNasc;
    private EditText editLogin;
    private EditText editSenha;
    private int id;
    private String nm_usuario;
    private String sexo;
    private String dtNasc;
    private String login;
    private String senha;
    private Button btContinuar;
    private String token;
    private int validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        editNome = (EditText) findViewById(R.id.edt_nm_usuario);

        editSexo = (EditText) findViewById(R.id.edt_sexo);

        editDtNasc = (EditText) findViewById(R.id.edt_data_nascimento);

        editLogin = (EditText) findViewById(R.id.edt_login);

        editSenha = (EditText) findViewById(R.id.edt_senha);

        btContinuar = (Button) findViewById(R.id.bt_continuar);

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm_usuario = editNome.getText().toString();
                sexo = editSexo.getText().toString();
                dtNasc = editDtNasc.getText().toString();
                login = editLogin.getText().toString();
                senha = editSenha.getText().toString();
                CadastrarAsyncTask task = new CadastrarAsyncTask("cadastrar",nm_usuario, sexo, dtNasc, login, senha);
                task.execute();
                goMenuPrincipal();
            }
        });
    }


    public class CadastrarAsyncTask
            extends
            AsyncTask<String, String, String> {

        String api_token, query, api_nm_usuario, api_sexo, api_data_nascimento, api_login, api_senha;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public CadastrarAsyncTask(String token, String nm_usuario, String sexo, String dtNasc, String login, String senha){

            this.api_token = token;
            this.api_nm_usuario = nm_usuario;
            this.api_sexo = sexo;
            this.api_data_nascimento = dtNasc;
            this.api_login = login;
            this.api_senha = senha;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_nm_usuario", String.valueOf(nm_usuario));
            builder.appendQueryParameter("api_sexo", String.valueOf(sexo));
            builder.appendQueryParameter("api_dt_nasc", String.valueOf(dtNasc));
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
                    Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
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
//
}