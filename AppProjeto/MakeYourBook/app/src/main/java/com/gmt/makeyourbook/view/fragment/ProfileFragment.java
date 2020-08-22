package com.gmt.makeyourbook.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.adapter.AdapterProjetos;
import com.gmt.makeyourbook.model.Projeto;
import com.gmt.makeyourbook.view.EditarProjetoActivity;
import com.gmt.makeyourbook.view.MainActivity;

import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.gmt.makeyourbook.R.layout.item_recyclerview;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements AdapterProjetos.OnProjectListener{

    private ImageView imgPerfil;
    private int cd_usuario, avatar;
    private TextView nmUsuario, cdUsuario;
    private String nm_usuario;
    private String sexo;
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private List<Projeto> listaProjetos = new ArrayList<>();

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nmUsuario = (TextView) view.findViewById(R.id.nmUsuario);
        cdUsuario = (TextView) view.findViewById(R.id.cdUsuario);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        imgPerfil = (ImageView) view.findViewById(R.id.imagePerfil);

        SharedPreferences preferences = getActivity().getSharedPreferences("user_preferences", MODE_PRIVATE);
        cd_usuario = preferences.getInt("cd_usuario", 0);

        progressBar.setVisibility(View.VISIBLE);
        ConsultarAsyncTask task = new ConsultarAsyncTask("consultarUsuario", cd_usuario);
        task.execute();

        recyclerView = view.findViewById(R.id.listaProjetos);

        //Configurar adapter
        AdapterProjetos adapterProjetos = new AdapterProjetos( listaProjetos, "perfil", avatar, this );

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterProjetos);

        progressBar.setVisibility(View.VISIBLE);

        //Listagem de Usuario no listarProjetosAsyncTask
        ListarProjetosAsyncTask task2 = new ListarProjetosAsyncTask("listarProjetos", cd_usuario);
        task2.execute();
        return view;
    }


    public class ListarProjetosAsyncTask extends AsyncTask<String, String, String>{


        String api_token, query;
        int api_cd_usuario;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://gmtmarketplace.com.br/api/api.php";

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;

        public ListarProjetosAsyncTask(String token, int cd_usuario){

            this.api_token = token;
            this.api_cd_usuario = cd_usuario;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_cd_usuario",String.valueOf(cd_usuario));

        }

        @Override
        protected void onPreExecute(){

            Log.i("APIListarProjetos","onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIListarProjetos","doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            }catch (MalformedURLException e){

                Log.i("APIListarProjetos","doInBackground() --> "+e.getMessage());

            }catch (Exception e){

                Log.i("APIListarProjetos","doInBackground() --> "+e.getMessage());
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

                Log.i("APIListarProjetos","doInBackground() --> "+e.getMessage());

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

                Log.i("APIListarProjetos","doInBackground() --> "+e.getMessage());


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

                Log.i("APIListarProjetos","doInBackground() --> "+e.getMessage());
            }
            finally {
                conn.disconnect();
            }


            return "Processamento concluído...";
        }

        @Override
        protected void onPostExecute(String result){

            Log.i("APIListarProjetos","onPostExecute()--> Result: "+result);

            try{

                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.getBoolean("RESULTADO")){
                    int length = jsonObject.getInt("length");
                    Projeto projeto;
                    for(int i=1; i<=length; i++){
                        projeto = new Projeto(jsonObject.getInt("cd_projeto" + i),jsonObject.getString("nm_usuario" + i), jsonObject.getInt("cd_usuario" + i), jsonObject.getString("titulo" + i),
                                jsonObject.getString("genero" + i), jsonObject.getString("historia" + i), jsonObject.getInt("avatar" +i));
                        listaProjetos.add(projeto);
                    }
                }
                else{
                    Log.i("APIListar","onPostExecute()--> Consulta Falhou");
                    Log.i("APIConsultar","onPostExecute()--> : "+jsonObject.getString("SQL"));
                    //Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
                }

            }catch (Exception e){
                Log.i("APIListarMeusProjetos","onPostExecute()--> : "+e.getMessage());
                //Toast.makeText(getApplicationContext(), "Login Falhou", Toast.LENGTH_LONG);
            }
            setInformation();

        }
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
                    Log.i("APIConsultar", "onPostExecute() --> Consulta bem Sucedido"+jsonObject.getString("ID"));
                    nm_usuario = jsonObject.getString("nm_usuario");
                    cd_usuario = Integer.parseInt(jsonObject.getString("ID"));
                    sexo = jsonObject.getString("SEXO");
                    avatar = jsonObject.getInt("avatar");
                    Log.i("APIConsultar", "onPostExecute() --> ID Login"+cd_usuario);
                    Log.i("APIConsultar", "onPostExecute() --> ID NOME"+nm_usuario);
                    Log.i("APIConsultar", "onPostExecute() --> ID SEXO"+sexo);
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

    public void setInformation(){
        nmUsuario.setText(nm_usuario);
        cdUsuario.setText("ID: "+cd_usuario);
        if (avatar == 1) {
            imgPerfil.setImageResource(R.drawable.avatar1);
        }
        else if(avatar == 2){
            imgPerfil.setImageResource(R.drawable.avatar2);
        }
        else if(avatar == 3){
            imgPerfil.setImageResource(R.drawable.avatar3);
        }
        else if(avatar == 4){
            imgPerfil.setImageResource(R.drawable.avatar4);
        }
        else if(avatar == 5){
            imgPerfil.setImageResource(R.drawable.avatar5);
        }
        else if(avatar == 6){
            imgPerfil.setImageResource(R.drawable.avatar6);
        }

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProjectClick(int position) {
        Log.d("teste","click"+ position );
        Log.d("id_projeto", "ajsklgdjsa" + listaProjetos.get(position).getCd_projeto());
        int cd_projeto = listaProjetos.get(position).getCd_projeto();
        Intent intent = new Intent(getActivity(), EditarProjetoActivity.class);
        intent.putExtra("cd_projeto", String.valueOf(cd_projeto));
        startActivity(intent);
    }
}