package com.example.usuario.repasoparcial;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alumno on 11/10/2018.
 */


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        MyViewHolder h = new MyViewHolder(v,this.activity);
        return h;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Noticia p = this.lista.get(position);

        holder.setPos(position);
        holder.titulo.setText(p.getTitulo());
        holder.descripcion.setText(p.getDescripcion());
//       holder.fecha.setText(p.getFecha().toString());
        holder.creador.setText(p.getCreador());
        holder.link.setText(p.getUrl());

       if (p.getImg() == null && p.isBuscoImagen() == false )
       {
           //busco la imagen
           p.setBuscoImagen(true);
           Worker g = new Worker(this.activity.h,p.getImagen(),p.buscoImagen,position);
           Thread hilo = new Thread(g);
           hilo.start();
       }else if(p.getImg()!=null && p.isBuscoImagen() ==true){

           holder.foto.setImageBitmap(BitmapFactory.decodeByteArray(p.getImg(),0,p.getImg().length));//bytes,0,bytes.length
       }




    }

    public void SettearLista(int posicion,byte[] foto)
    {

    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

     List<Noticia> lista;
     MainActivity activity;

    /*public MyAdapter(List<Producto> lista, MainActivity activity) {
        this.lista = lista;
        this.activity=activity;
    }*/
    public MyAdapter(List<Noticia> lista, MainActivity activity) {
        this.lista = new ArrayList<>();
        this.activity=activity;
        ordenarNoticias();
    }

    public void setLista(List<Noticia> lista)
    {
        this.lista = lista;
        ordenarNoticias();
    }

    public void AddToList(List<Noticia> lista)
    {
        this.lista.addAll(lista);
        ordenarNoticias();
    }

    public List<Noticia> getLista() {
        return lista;
    }

    public void ordenarNoticias(){
        Collections.sort(this.lista);

    }
}