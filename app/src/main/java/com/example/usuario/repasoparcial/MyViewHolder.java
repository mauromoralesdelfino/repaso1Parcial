package com.example.usuario.repasoparcial;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView titulo;
    TextView descripcion;
    TextView fecha;
    TextView link;
    TextView creador;
    ImageView foto;
    MainActivity activity;
    RecyclerView rvNoticias;

    int pos;



    public MyViewHolder(@NonNull View itemView, MainActivity activity) {
        super(itemView);
        this.titulo=(TextView) itemView.findViewById(R.id.txtTitle);
        this.link=(TextView) itemView.findViewById(R.id.txtLink);
        this.descripcion=(TextView) itemView.findViewById(R.id.txtDescription);
        this.fecha=(TextView) itemView.findViewById(R.id.txtPubDate);
        this.creador=(TextView) itemView.findViewById(R.id.txtCreador);
        this.foto=(ImageView) itemView.findViewById(R.id.Imagen);
        this.activity=activity;
        this.itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    //this.activity.VistaWeb(this.link.getText().toString());


    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
