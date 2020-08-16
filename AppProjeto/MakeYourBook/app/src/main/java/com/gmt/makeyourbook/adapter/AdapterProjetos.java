package com.gmt.makeyourbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.model.Projeto;

import java.util.List;

public class AdapterProjetos extends RecyclerView.Adapter<AdapterProjetos.MyViewHolder> {

    private List<Projeto> listaProjeto;
    public AdapterProjetos(List<Projeto> projeto) {
        this.listaProjeto = projeto;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Projeto projeto = listaProjeto.get(position);
        holder.nm_usuario.setText(projeto.getNmUsuario());
        holder.id.setText(String.valueOf(projeto.getId()));
        holder.titulo.setText(projeto.getTitulo());
        holder.genero.setText(projeto.getGenero());
        holder.historia.setText(projeto.getHistoria());

    }

    @Override
    public int getItemCount() {
        return listaProjeto.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_perfil;
        TextView nm_usuario;
        TextView id;
        TextView titulo;
        TextView genero;
        TextView historia;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_perfil = itemView.findViewById(R.id.imagePerfil);
            nm_usuario = itemView.findViewById(R.id.nmUsuario);
            id = itemView.findViewById(R.id.idUsuario);
            titulo = itemView.findViewById(R.id.titulo);
            genero = itemView.findViewById(R.id.genero);
            historia = itemView.findViewById(R.id.resumoHistoria);
        }
    }

}
