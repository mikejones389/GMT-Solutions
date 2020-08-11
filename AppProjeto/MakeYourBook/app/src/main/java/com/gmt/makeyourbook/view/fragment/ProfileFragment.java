package com.gmt.makeyourbook.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.view.MainActivity;
import com.gmt.makeyourbook.view.SplashActivity;

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

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ImageView imgPerfil;
    private Dialog trocarAvatar;
    private int cd_usuario;
    private TextView nmUsuario, cdUsuario;
    private String nm_usuario;
    private String sexo;
    private ProgressBar progressBar;

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
        trocarAvatar = new Dialog(getActivity());

        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView close;
                ImageView avatar1;
                trocarAvatar.setContentView(R.layout.popup_seletor_img_perfil);
                View dialogView = inflater.inflate(R.layout.popup_seletor_img_perfil, container, false);
                close = (ImageView) dialogView.findViewById(R.id.close);
                avatar1 = (ImageView) dialogView.findViewById(R.id.avatar1);
                avatar1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "Avatar1 Selecionado", Toast.LENGTH_LONG).show();
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        trocarAvatar.dismiss();
                        trocarAvatar.cancel();
                    }
                });

                trocarAvatar.show();
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("user_preferences", MODE_PRIVATE);
        cd_usuario = preferences.getInt("cd_usuario", 0);

        progressBar.setVisibility(View.VISIBLE);
        ConsultarAsyncTask task = new ConsultarAsyncTask("consultar", cd_usuario);
        task.execute();


        return view;
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
        progressBar.setVisibility(View.GONE);
    }


}