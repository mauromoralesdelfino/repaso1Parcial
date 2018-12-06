package com.example.usuario.repasoparcial;

import android.support.annotation.NonNull;

import java.util.Date;

public class Noticia implements Comparable<Noticia> {

    private Integer id;
    private String titulo;
    private String url;
    private String imagen;
    private String descripcion;
    private Date fecha;
    private String creador;
    boolean buscoImagen;
    byte[] img;

    public boolean isBuscoImagen() {
        return buscoImagen;
    }

    public void setBuscoImagen(boolean buscoImagen) {
        this.buscoImagen = buscoImagen;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Noticia()  {}

    public Noticia(Integer id, String titulo, String url, String imagen, String descripcion, Date fecha, String creador) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.creador = creador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    @Override
    public int compareTo(@NonNull Noticia o) {
        return getTitulo().compareTo(o.getTitulo());
    }
}
