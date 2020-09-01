package com.gmt.makeyourbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.view.fragment.ProfileFragment;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EditarProjetoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int cd_projeto;
    private String titulo, historia, genero;

    private EditText edtTitulo, edtHistoria;
    private Spinner spinnerGenero;
    private ImageView ic_salvar, ic_cancelar, ic_deletar;
    String[] generos;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_projeto);

        Bundle extras = getIntent().getExtras();
        cd_projeto = Integer.parseInt(extras.getString("cd_projeto"));
        Log.d("ID", "cd_projeto = "+ cd_projeto);

        edtTitulo = (EditText) findViewById(R.id.tituloProjeto);

        edtHistoria = (EditText) findViewById(R.id.txtHistoria);

        spinnerGenero = (Spinner) findViewById(R.id.spinner_genero);
        spinnerGenero.setOnItemSelectedListener(this);

        generos = getResources().getStringArray(R.array.genero_list);

        ic_salvar = (ImageView) findViewById(R.id.ic_salvar);

        ConsultarAsyncTask task = new ConsultarAsyncTask("consultarMeuProjeto", cd_projeto);
        task.execute();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        genero = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class ConsultarAsyncTask
            extends
            AsyncTask<String, String, String> {

        String api_token, query;
        int api_cd_projeto;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public ConsultarAsyncTask(String token, int cd_projeto){

            this.api_token = token;
            this.api_cd_projeto = cd_projeto;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_cd_projeto",String.valueOf(cd_projeto));
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
                    //Log.i("APIConsultar", "onPostExecute() --> Consulta bem Sucedido"+jsonObject.getString("ID"));
                    titulo = jsonObject.getString("titulo");
                    historia = jsonObject.getString("historia");
                    genero = jsonObject.getString("genero");
                    //Toast.makeText(getApplicationContext(), "Login bem Sucedido", Toast.LENGTH_LONG);
                }
                else{
                    Log.i("APIConsultar","onPostExecute()--> Consulta Falhou");
                    Log.i("APIConsultar","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    //Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
                }

            }catch (Exception e){
                Log.i("APIConsultar","onPostExecute()--> : "+e.getMessage());
                //Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
            }
            setInformation();

        }

    }

    private void setInformation() {
        edtTitulo.setText(titulo);
        edtHistoria.setText(historia);

        int posiçãoArray = 0;

        for(int i = 0; i<=generos.length -1; i++){

            Log.d("array", String.valueOf(generos[i]));

            if(generos[i].equals(genero)){

                posiçãoArray = i;
                break;

            }

            else{

                posiçãoArray = 0;

            }
        }

        spinnerGenero.setSelection(posiçãoArray);


    }

    public void atualizarProjeto(View view){
        titulo = edtTitulo.getText().toString();
        historia = edtHistoria.getText().toString();

        if(titulo.equals("")){
            edtTitulo.requestFocus();
        }
        else if(genero.equals("Selecione")){
            spinnerGenero.requestFocus();
        }
        else if(historia.equals("")){
            edtHistoria.requestFocus();
        }
        else{
            AtualizarAsyncTask atualizarAsyncTask = new AtualizarAsyncTask("atualizarProjeto", cd_projeto, titulo, genero, historia);
            atualizarAsyncTask.execute();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("position", "Perfil");
            startActivity(intent);
        }


    }

    public class AtualizarAsyncTask
            extends
            AsyncTask<String, String, String> {

        String api_token, query, api_titulo, api_genero, api_historia;
        int api_cd_projeto;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public AtualizarAsyncTask(String token, int cd_projeto, String titulo, String genero, String historia){

            this.api_token = token;
            this.api_cd_projeto = cd_projeto;
            this.api_titulo= titulo;
            this.api_genero = genero;
            this.api_historia= historia;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_cd_projeto", String.valueOf(cd_projeto));
            builder.appendQueryParameter("api_titulo", String.valueOf(titulo));
            builder.appendQueryParameter("api_genero", String.valueOf(genero));
            builder.appendQueryParameter("api_historia", String.valueOf(historia));

        }

        @Override
        protected void onPreExecute(){

            Log.i("APIAtualizar","onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIAtualizar","doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            }catch (MalformedURLException e){

                Log.i("APIAtualizar","doInBackground() --> "+e.getMessage());

            }catch (Exception e){

                Log.i("APIAtualizar","doInBackground() --> "+e.getMessage());
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

                Log.i("APIAtualizar","doInBackground() --> "+e.getMessage());

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

                Log.i("APIAtualizar","doInBackground() --> "+e.getMessage());


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

                Log.i("APIAtualizar","doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }


            return "Processamento concluído...";


        }

        @Override
        protected void onPostExecute(String result){

            Log.i("APIAtualizar","onPostExecute()--> Result: "+result);

            try{

                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.getBoolean("Atualizar")){
                    Log.i("APIAtualizar", "onPostExecute() --> Atualizado com sucesso "+jsonObject.getString("CD_PROJETO"));
                    Log.i("APIAtualizar", "onPostExecute() --> ID Projeto"+cd_projeto);
                 }
                else{
                    Log.i("APIAtualizar","onPostExecute()--> Cadastro Falhou");
                    Log.i("APIAtualizar","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
//                    validar = 0;
                }

            }catch (Exception e){
                Log.i("APIAtualizar","onPostExecute()--> : "+e.getMessage());
                Toast.makeText(getApplicationContext(), "Atualização Falhou", Toast.LENGTH_LONG);
            }

        }
    }

    public void cancelar(View view){
        this.finish();
    }

    public void deletar(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar Projeto");
        builder.setMessage("Voçê tem certeza que quer deletar esse projeto? Essa ação não poderá ser desfeita!");

        builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DeletarAsyncTask deletarAsyncTask = new DeletarAsyncTask("deletarProjeto", cd_projeto);
                deletarAsyncTask.execute();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("position", "Perfil");
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
        alertDialog = builder.create();
        alertDialog.show();

    }

    public class DeletarAsyncTask
            extends
            AsyncTask<String, String, String> {

        String api_token, query;
        int api_cd_projeto;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public DeletarAsyncTask(String token, int cd_projeto){

            this.api_token = token;
            this.api_cd_projeto = cd_projeto;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_cd_projeto", String.valueOf(cd_projeto));

        }

        @Override
        protected void onPreExecute(){

            Log.i("APIDeletar","onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIDeletar","doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            }catch (MalformedURLException e){

                Log.i("APIDeletar","doInBackground() --> "+e.getMessage());

            }catch (Exception e){

                Log.i("APIDeletar","doInBackground() --> "+e.getMessage());
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

                Log.i("APIDeletar","doInBackground() --> "+e.getMessage());

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

                Log.i("APIDeletar","doInBackground() --> "+e.getMessage());


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

                Log.i("APIDeletar","doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }


            return "Processamento concluído...";


        }

        @Override
        protected void onPostExecute(String result){

            Log.i("APIDeletar","onPostExecute()--> Result: "+result);

            try{

                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.getBoolean("Deletar")){
                    Log.i("APIDeletar", "onPostExecute() --> Deletado com sucesso "+jsonObject.getString("CD_PROJETO"));
                    Log.i("APIDeletar", "onPostExecute() --> ID Projeto"+cd_projeto);
                }
                else{
                    Log.i("APIDeletar","onPostExecute()--> Deletar Falhou");
                    Log.i("APIDeletar","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    Toast.makeText(getApplicationContext(), "Deletar Falhou", Toast.LENGTH_LONG);

                }

            }catch (Exception e){
                Log.i("APIDeletar","onPostExecute()--> : "+e.getMessage());
                Toast.makeText(getApplicationContext(), "Deletar Falhou", Toast.LENGTH_LONG);
            }

        }
    }


}