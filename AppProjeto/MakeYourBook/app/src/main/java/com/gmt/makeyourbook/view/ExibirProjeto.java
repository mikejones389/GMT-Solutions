package com.gmt.makeyourbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.model.Projeto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ExibirProjeto extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView txtTitulo, txtHistoria, txtNmUsuario, txtCdProjeto, txtGenero;
    private ImageView imgAvatar;
    private String titulo, historia, nmUsuario, genero;
    private int avatar, cd_projeto;
    private Double valor_total, valor_arrecadado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_projeto);

        Bundle extras = getIntent().getExtras();
        cd_projeto = Integer.parseInt(extras.getString("cd_projeto"));

        progressBar = findViewById(R.id.progress);
        txtCdProjeto = findViewById(R.id.cd_projeto);
        txtHistoria = findViewById(R.id.historia);
        txtGenero = findViewById(R.id.genero);
        txtNmUsuario = findViewById(R.id.nm_usuario);
        txtTitulo = findViewById(R.id.titulo);
        imgAvatar = findViewById(R.id.avatarPerfil);

        ConsultarProjetoAsyncTask task = new ConsultarProjetoAsyncTask("consultarProjeto", cd_projeto);
        task.execute();

    }

    public class ConsultarProjetoAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;
        int api_cd_projeto;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; //MILISEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;

        public ConsultarProjetoAsyncTask(String token, int cd_projeto){
            this.api_token = token;
            this.api_cd_projeto = cd_projeto;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_cd_projeto", String.valueOf(api_cd_projeto));
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            Log.i("APIConsultarProjetos", "onPreExecute()");
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIConsultarProjeto", "doInBackground()");

            //GERAR O CONTEÚDO PARA A URL

            try {
                url= new URL(URL_WEB_SERVICES);
            } catch (MalformedURLException e) {
                Log.i("APIConsultarProjetos", "doInBackground() --> "+e.getMessage());
            } catch (Exception e){
                Log.i("APIConsultarProjetos", "doInBackground() --> "+e.getMessage());
            }

            //GERAR UMA REQUISIÇÃO HTTP - POST - RESULT SERÁ UM ARRAYJSON

            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();
            } catch (ProtocolException e) {
                Log.i("APIConsultarProjetos", "doInBackground() --> "+e.getMessage());
            } catch (IOException e) {
                Log.i("APIConsultarProjetos", "doInBackground() --> "+e.getMessage());
            }

            //ADICIONAR O TOKEN E/ OU OUTROS PARAMETROS COMO POR EXEMPLO
            //O OBJETO A SER INCLUIDO, DELETADO OU ALTERADO.
            //CRUD COMPLETO

            try{
                query = builder.build().getEncodedQuery();

                OutputStream stream = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(stream, "utf-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                stream.close();

                conn.connect();
            } catch (UnsupportedEncodingException e) {
                Log.i("APIConsultarProjetos", "doInBackground() --> "+e.getMessage());
            } catch (IOException e) {
                Log.i("APIConsultarProjetos", "doInBackground() --> "+e.getMessage());
            }

            //RECEBER O RESPONSE - ARRAYJSON
            //HTTP - CODIGO DO RESPONSE | 200 | 404 | 503

            try{
                response_code = conn.getResponseCode();
                if(response_code == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(inputStream)
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

            } catch (IOException e) {
                Log.i("APIConsultarProjetos", "doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }
            return "Processamento concluido";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("APIConsultarProjetos", "onPostExecute() --> Result: "+result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                if (jsonObject.getBoolean("RESULTADO")){
                    //SETAR AS INFORMAÇÕES NOS TEXTVIEWs
                    titulo = jsonObject.getString("titulo");
                    genero = jsonObject.getString("genero");
                    historia = jsonObject.getString("historia");
                    valor_total = jsonObject.getDouble("valor_total");
                    valor_arrecadado = jsonObject.getDouble("valor_arrecadado");
                    avatar = jsonObject.getInt("avatar");
                    nmUsuario = jsonObject.getString("nm_usuario");
                    setInformation();
                }
                else{
                    Log.i("APIConsultarProjetos", "onPostExecute() --> Consulta Falhou");
                    Log.i("APIConsultarProjetos", "onPostExecute() --> "+jsonObject.getString("SQL"));
                }
            } catch (JSONException e) {
                Log.i("APIConsultarProjetos", "onPostExecute() --> "+e.getMessage());
            }

            progressBar.setVisibility(View.GONE);
        }
    }

    public void setInformation() {
        txtTitulo.setText(titulo);
        txtNmUsuario.setText(nmUsuario);
        txtGenero.setText(genero);
        txtHistoria.setText(historia);
        txtCdProjeto.setText(String.valueOf(cd_projeto));
        switch (avatar){
            case 1:
                imgAvatar.setImageResource(R.drawable.avatar1);
                break;
            case 2:
                imgAvatar.setImageResource(R.drawable.avatar2);
                break;
            case 3:
                imgAvatar.setImageResource(R.drawable.avatar3);
                break;
            case 4:
                imgAvatar.setImageResource(R.drawable.avatar4);
                break;
            case 5:
                imgAvatar.setImageResource(R.drawable.avatar5);
                break;
            case 6:
                imgAvatar.setImageResource(R.drawable.avatar6);
                break;
        }
    }

}