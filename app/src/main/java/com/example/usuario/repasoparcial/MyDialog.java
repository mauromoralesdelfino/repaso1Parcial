package com.example.usuario.repasoparcial;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

;

/**
 * Created by alumno on 15/11/2018.
 */

public class MyDialog extends DialogFragment {

    SharedPreferences prefs;
   View v;
   MainActivity act;


   public MyDialog()
    {

    }

   @SuppressLint("ValidFragment")
   public MyDialog(MainActivity act)
   {
       this.act=act;
   }

    public Dialog onCreateDialog(Bundle d )
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        prefs = getContext().getSharedPreferences("miConfig", Context.MODE_PRIVATE);
         v = LayoutInflater.from(this.getContext()).inflate(R.layout.rss_menu,null);
        for(View item : v.getTouchables())
        {
            //checkUno.setChecked(myConfig.getBoolean(checkUno.getText().toString(), false));
            String nombreCheck = String.valueOf(((CheckBox) item).getText());
            ((CheckBox)item).setChecked(prefs.getBoolean(nombreCheck,((CheckBox) item).isChecked()));
        }
        builder.setTitle("Choice Rss");
        //builder.setMessage("Hola mundo");
        builder.setView(v);

        Listener l = new Listener(v,act);
        builder.setNegativeButton("Canelar",l);
        builder.setPositiveButton("Aceptar",l);

        AlertDialog ad = builder.create();
        //mostrar();
        return ad;
    }


    public void mostrar()
    {

        prefs = getContext().getSharedPreferences("miConfig", Context.MODE_PRIVATE);
        //preferencias = getSharedPreferences("miConfig", Context.MODE_PRIVATE);
        Boolean ultimo = prefs.getBoolean("Lo Ultimo",false);
        Boolean pol = prefs.getBoolean("Politica",false);
        Boolean eco = prefs.getBoolean("Economia",false);
        Boolean soc = prefs.getBoolean("Sociedad",false);
        Boolean mundo = prefs.getBoolean("Mundo",false);
        Boolean tecno = prefs.getBoolean("Tecnologia",false);


        if (ultimo.booleanValue())
        {

            Log.d("menu","Buenos dias niña de tilcara");

        }
        else if(eco)
        {
        }
        else if(pol)
        {

        }
        else if(soc)
        {

        }
        else if(mundo)
        {

        }
        else if(tecno)
        {


        }

         }





}
