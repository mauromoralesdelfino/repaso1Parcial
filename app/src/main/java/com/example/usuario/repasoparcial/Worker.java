package com.example.usuario.repasoparcial;

import android.os.Handler;
import android.os.Message;

import java.net.URL;

/**
 * Created by alumno on 11/10/2018.
 */

public class Worker extends Thread {

    Handler h;
    URL url;
    boolean img,json;
    String conex;
    String urlImg;
    int pos;

    public Worker(Handler h, String url)
    {
        this.h =h;
        this.conex = url;

    }

    public Worker(Handler h, String url, boolean img, int pos)

    {
        this.h =h;
        this.urlImg = url;
        this.img = img;
        this.pos=pos;
    }

    public Worker(Handler h,String url, boolean json)
    {
        this.h =h;
        this.conex = url;
        this.json = json;

    }
    @Override
    public void run() {

        try {

            if (this.img==true)
            {
                HttpConection foto = new HttpConection("GET");
                Message m = new Message();
                m.arg1 = 2;
                m.arg2=this.pos;
                m.obj= foto.getBytesData(urlImg);
                h.sendMessage(m);


            }else if(img == false && json == false){
                HttpConection z = new HttpConection("GET");
                Message m = new Message();
                m.arg1 = 1;
                m.obj = ParseProductoXML.Listar(new String(z.getBytesData(conex)));
                h.sendMessage(m);
            }else if(json == true)
            {
                HttpConection z = new HttpConection("GET");
                Message m = new Message();
                m.arg1 = 1;
                m.obj = ParseProductoXML.JASON(new String(z.getStringData(conex)));
                h.sendMessage(m);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}