package com.gmt.makeyourbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmt.makeyourbook.R;
import com.gmt.makeyourbook.model.Projeto;

import java.util.ArrayList;
import java.util.List;

public class AdapterProjetos extends RecyclerView.Adapter<AdapterProjetos.MyViewHolder> {

    private List<Projeto> listaProjeto;
    private OnProjectListener mOnProjectListener;

    public AdapterProjetos(List<Projeto> projeto, OnProjectListener onProjectListener){
        this.listaProjeto = projeto;
        this.mOnProjectListener = onProjectListener;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent,false);

        //VALIDAÇÃO PARA APRESENTAR OS BOTOES DO ITEMLIST

        return new MyViewHolder(itemLista, mOnProjectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Projeto projeto = listaProjeto.get(position);
        holder.nm_usuario.setText(projeto.getNmUsuario());
        holder.cd_usuario.setText(String.valueOf(projeto.getCd_usuario())+"/"+String.valueOf(projeto.getCd_projeto()));
        holder.titulo.setText(projeto.getTitulo());
        holder.genero.setText(projeto.getGenero());
        holder.historia.setText(projeto.getHistoria());
        if(projeto.getAvatar() == 0){
            holder.img_perfil.setImageResource(R.drawable.ic_profile);
        }
        else if(projeto.getAvatar() == 1) {
            holder.img_perfil.setImageResource(R.drawable.avatar1);
        }
        else if(projeto.getAvatar() == 2) {
            holder.img_perfil.setImageResource(R.drawable.avatar2);
        }
        else if(projeto.getAvatar() == 3) {
            holder.img_perfil.setImageResource(R.drawable.avatar3);
        }
        else if(projeto.getAvatar() == 4) {
            holder.img_perfil.setImageResource(R.drawable.avatar4);
        }
        else if(projeto.getAvatar() == 5) {
            holder.img_perfil.setImageResource(R.drawable.avatar5);
        }
        else if(projeto.getAvatar() == 6) {
            holder.img_perfil.setImageResource(R.drawable.avatar6);
        }
        holder.valorTotal.setText("R$ "+ String.format("%.2f", projeto.getValorTotal()));
        holder.valorArrecadado.setText("R$ "+ String.format("%.2f", projeto.getValorArrecadado()));

    }

    @Override
    public int getItemCount() {
        return listaProjeto.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_perfil;
        TextView nm_usuario;
        TextView cd_usuario;
        TextView titulo;
        TextView genero;
        TextView historia;
        TextView valorTotal;
        TextView valorArrecadado;
        OnProjectListener onProjectListener;

        public MyViewHolder(@NonNull View itemView, OnProjectListener onProjectListener) {
            super(itemView);
            img_perfil = itemView.findViewById(R.id.imagePerfil);
            nm_usuario = itemView.findViewById(R.id.nmUsuario);
            cd_usuario = itemView.findViewById(R.id.idUsuario);
            titulo = itemView.findViewById(R.id.titulo);
            genero = itemView.findViewById(R.id.genero);
            historia = itemView.findViewById(R.id.resumoHistoria);
            valorTotal = itemView.findViewById(R.id.valorTotal);
            valorArrecadado = itemView.findViewById(R.id.valorArrecadado);
            this.onProjectListener = onProjectListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onProjectListener.onProjectClick(getAdapterPosition());
        }
    }

    public interface OnProjectListener{
        void onProjectClick(int position);
    }

    public void atualizarAdapter(List<Projeto> projetos){
        this.listaProjeto.clear();
        this.listaProjeto.addAll(projetos);
        notifyDataSetChanged();

    }

}
