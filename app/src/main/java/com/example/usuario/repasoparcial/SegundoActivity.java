package com.example.usuario.repasoparcial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SegundoActivity extends AppCompatActivity implements Button.OnClickListener,Handler.Callback {

    EditText usuario;
    EditText clave;
    Handler j;
    Thread h1;
    Worker w1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.segactivity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Button boton = (Button) findViewById(R.id.boton);
        boton.setOnClickListener(this);
        j = new Handler(this);

    }

    @Override
    public void onClick(View v) {

        usuario = (EditText) findViewById(R.id.user);
        clave = (EditText) findViewById(R.id.pass);
        String u = usuario.getText().toString();
        String p = usuario.getText().toString();
        Log.d(u,p);

        if ( u.equalsIgnoreCase("m") && p.equalsIgnoreCase("m")  ) {
            w1 = new Worker(j, "http://dev.webilation.com/memeimporter/api/getArticles", true);
           // http://dev.webilation.com/memeimporter/api/getArticles
           // w1 = new Worker(j, "http://127.0.0.1:8082/android/MOCK_DATA.json", true);
            //http://127.0.0.1:8082/android/MOCK_DATA.json
            //https://localhost:8082/android/MOCK_DATA.json
            //https://www.clarin.com/rss/economia/
            h1 = new Thread(w1);
            h1.start();
            Log.d("ultimo", "ultimo");
        }
        else
        {
            Intent i = new Intent(this,MainActivity.class);
            super.startActivity(i);
        }


        //JSONArray jsonArray = JSONObject.getJSONArray("results");
       /* JSONArray jsonArray = JSONObject.get
        for (int i = 0; i < jsonArray.length(); i++)
        {
            try {
                JSONObject jsonObjectHijo = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                Log.e("Parser JSON", e.toString());
            }
        }*/

    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.d("handle", "mensaje sagrado");

        List<JSONObject> a = (List<JSONObject>)msg.obj;
        for (JSONObject item : a)
        {
            Log.d("Objeto:" , " a" + item.toString());
            try {
                String valorLlave = item.getString("title");
                Log.d("TITULO:" , "TITULO" +  valorLlave);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}
