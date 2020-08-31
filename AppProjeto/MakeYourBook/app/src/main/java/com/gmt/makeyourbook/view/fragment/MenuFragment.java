package com.gmt.makeyourbook.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.security.keystore.SecureKeyImportUnavailableException;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.adapter.AdapterProjetos;
import com.gmt.makeyourbook.model.Projeto;
import com.gmt.makeyourbook.view.EditarProjetoActivity;
import com.gmt.makeyourbook.view.ExibirProjeto;

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
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements AdapterProjetos.OnProjectListener {


    private RecyclerView recyclerView;
    private List<Projeto> listaProjetos = new ArrayList<>();
    private ProgressBar progressBar;


    //Construtor
    public MenuFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        progressBar = view.findViewById(R.id.progress);

        recyclerView = view.findViewById(R.id.listaProjetos);

        //Configurar Adapter
        AdapterProjetos adapterProjetos = new AdapterProjetos(listaProjetos, this);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterProjetos);


        ListarProjetosAsyncTask task = new ListarProjetosAsyncTask("listarTodosProjetos");
        task.execute();

        return view;
    }

    public class ListarProjetosAsyncTask extends AsyncTask<String, String, String>{

        String api_token, query;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; //MILISEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;

        public ListarProjetosAsyncTask(String token){
            this.api_token = token;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            Log.i("APIListarProjetos", "onPreExecute()");
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIListarProjetos", "doInBackground()");

            //GERAR O CONTEÚDO PARA A URL

            try {
                url= new URL(URL_WEB_SERVICES);
            } catch (MalformedURLException e) {
                Log.i("APIListarProjetos", "doInBackground() --> "+e.getMessage());
            } catch (Exception e){
                Log.i("APIListarProjetos", "doInBackground() --> "+e.getMessage());
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
                Log.i("APIListarProjetos", "doInBackground() --> "+e.getMessage());
            } catch (IOException e) {
                Log.i("APIListarProjetos", "doInBackground() --> "+e.getMessage());
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
                Log.i("APIListarProjetos", "doInBackground() --> "+e.getMessage());
            } catch (IOException e) {
                Log.i("APIListarProjetos", "doInBackground() --> "+e.getMessage());
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
                Log.i("APIListarProjetos", "doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }
            return "Processamento concluido";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("APIListarProjetos", "onPostExecute() --> Result: "+result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                if (jsonObject.getBoolean("RESULTADO")){
                    int length = jsonObject.getInt("length");
                    Projeto projeto;
                    for(int i = 1; i<=length; i++){
                        projeto = new Projeto(jsonObject.getInt("cd_projeto"+i),
                                jsonObject.getString("nm_usuario"+i),
                                jsonObject.getInt("cd_usuario"+i),
                                jsonObject.getString("titulo"+i),
                                jsonObject.getString("genero"+i),
                                jsonObject.getString("historia"+i),
                                jsonObject.getInt("avatar"+i),
                                jsonObject.getDouble("valor_total"+i),
                                jsonObject.getDouble("valor_arrecadado"+i));
                        listaProjetos.add(projeto);
                    }
                }
                else{
                    Log.i("APIListarProjetos", "onPostExecute() --> Consulta Falhou");
                    Log.i("APIListarProjetos", "onPostExecute() --> "+jsonObject.getString("SQL"));
                }
            } catch (JSONException e) {
                Log.i("APIListarProjetos", "onPostExecute() --> "+e.getMessage());
            }

            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onProjectClick(int position) {
        int cd_projeto = listaProjetos.get(position).getCd_projeto();
        Intent intent = new Intent(getActivity(), ExibirProjeto.class);
        intent.putExtra("cd_projeto", String.valueOf(cd_projeto));
        startActivity(intent);
    }
}