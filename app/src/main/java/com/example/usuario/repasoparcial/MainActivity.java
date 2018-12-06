package com.example.usuario.repasoparcial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, SearchView.OnQueryTextListener {

    public Handler h;
    URL url;
    RecyclerView rvProductos;
    MyAdapter adapter;
    Thread h1, h2, h3, h4, h5, h6, h7;
    Worker w1, w2, w3, w4, w5, w6;
    List<Noticia> listaN;
    List<Noticia> listaAUX;
    ImageView iv;
    SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        rvProductos = (RecyclerView) findViewById(R.id.listaRV);


        listaN = new ArrayList<>();
        h = new Handler(this);

        rvProductos = (RecyclerView) findViewById(R.id.listaRV);
        rvProductos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(listaN, this);
        rvProductos.setAdapter(adapter);

        TemasElegidos();


    }

    @Override
    protected void onStop() {
        super.onStop();

        if (h1 != null) {
            h1.interrupt();
        }
        if (h2 != null) {
            h1.interrupt();
        }
        if (h3 != null) {
            h1.interrupt();
        }
        if (h4 != null) {
            h1.interrupt();
        }
        if (h5 != null) {
            h1.interrupt();
        }
        if (h6 != null) {
            h1.interrupt();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem mi = menu.findItem(R.id.campo_buscar);
        SearchView sv = (SearchView) MenuItemCompat.getActionView(mi);

        sv.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
      /*  if (id == R.id.campo_buscar) {
            Log.d("menu","Click en buscar");
            return true;
        }*/
        if (id == R.id.action_rssMenu) {
            Log.d("menu", "Click en rss");


            MyDialog md = new MyDialog(this);
            md.show(getSupportFragmentManager(), " ");

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1 == 1) {
            Log.d("handle", "mensaje sagrado");
           /* adapter = new MyAdapter(listaN, this);
            rvProductos.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            rvProductos.setLayoutManager(new LinearLayoutManager(this));*/
            ;
            adapter.AddToList((List<Noticia>) msg.obj);
            adapter.notifyDataSetChanged();
            listaN = adapter.getLista();


        } else if (msg.arg1 == 2) {

            byte[] bytes = (byte[]) msg.obj;
            //Bitmap bytmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            //  iv = (ImageView) findViewById(R.id.Imagen);
            // iv.setImageBitmap(bytmap);

            adapter.lista.get(msg.arg2).setImg(bytes);
            adapter.notifyItemChanged(msg.arg2);
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        List<Noticia> aux = new ArrayList<Noticia>();
        if (query != null && !query.isEmpty()) {
            for (Noticia item : adapter.lista) {
                if (item.getTitulo().toLowerCase().contains(query.toLowerCase()) || item.getDescripcion().toLowerCase().contains(query.toLowerCase())) {
                    aux.add(item);
                }
            }

            adapter.setLista(aux);
            adapter.notifyDataSetChanged();
        } else {

            adapter.setLista(listaN);
            adapter.notifyDataSetChanged();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Noticia> aux = new ArrayList<Noticia>();
        if (newText != null && !newText.isEmpty()) {
            if (newText.length() >= 4) {
                for (Noticia item : adapter.lista) {
                    if (item.getTitulo().toLowerCase().contains(newText.toLowerCase()) || item.getDescripcion().toLowerCase().contains(newText.toLowerCase())) {
                        aux.add(item);
                    }
                }

                adapter.setLista(aux);
                adapter.notifyDataSetChanged();
            }
        } else {

            adapter.setLista(listaN);
            adapter.notifyDataSetChanged();
        }

        return false;
    }


    public void VistaWeb(String direccion) {
       // Intent i = new Intent(this, Web_ViewButton.class);// me permite intentar hacer algo, quie lo lanza y que lanzo parametros
        //i.putExtra("sitio", direccion);
       // super.startActivity(i);
    }

    public void TemasElegidos() {

        Log.d("primera linea", " a ver cuantas veces entra");
        listaN = new ArrayList<>();
        adapter.setLista(listaN);
        adapter.notifyDataSetChanged();

        Log.d("temas", "elegidos");
        preferencias = getSharedPreferences("miConfig", Context.MODE_PRIVATE);
        Boolean ultimo = preferencias.getBoolean("Lo Ultimo", false);
        Boolean pol = preferencias.getBoolean("Politica", false);
        Boolean eco = preferencias.getBoolean("Economia", false);
        Boolean soc = preferencias.getBoolean("Sociedad", false);
        Boolean mundo = preferencias.getBoolean("Mundo", false);
        Boolean tecno = preferencias.getBoolean("Tecnologia", false);


        if (ultimo.booleanValue()) {

            w1 = new Worker(h, "https://www.clarin.com/rss/lo-ultimo/");
            h1 = new Thread(w1);
            h1.start();
            Log.d("ultimo", "ultimo");

        } else if (eco) {
            w2 = new Worker(h, "https://www.clarin.com/rss/economia/");
            h2 = new Thread(w2);
            h2.start();
            Log.d("eco", "eco");
        } else if (pol) {

            w3 = new Worker(h, "https://www.clarin.com/rss/politica/");
            h3 = new Thread(w3);
            h3.start();
            Log.d("eco", "eco");
        } else if (soc) {
            w4 = new Worker(h, "https://www.clarin.com/rss/sociedad/");
            h4 = new Thread(w4);
            h4.start();
            Log.d("soc", "soc");
        } else if (mundo) {
            w5 = new Worker(h, "https://www.clarin.com/rss/mundo/");
            h5 = new Thread(w5);
            h5.start();
            Log.d("mundo", "mundo");
        } else if (tecno) {
            w6 = new Worker(h, "https://www.clarin.com/rss/tecnologia/");
            h6 = new Thread(w6);

            Log.d("tecno", "tecno");

        }


    }
}
