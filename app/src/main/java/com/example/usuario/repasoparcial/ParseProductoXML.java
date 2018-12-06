package com.example.usuario.repasoparcial;

import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 11/10/2018.
 */

public class ParseProductoXML {




    public static List<Noticia> Listar(String stringXML){
        List<Noticia> noticias = new ArrayList<>();
        Noticia noti = null;

        try {
            XmlPullParser xml = Xml.newPullParser();
            xml.setInput(new StringReader(stringXML));
            SimpleDateFormat fechaParse = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
           // fechaParse.par
           // DateFormat fechaParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            //SimpleDateFormat fechaParse = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
            //SimpleDateFormat fechaParse = new SimpleDateFormat("dd MM yyyy HH:mm:ss");



            /*
             SimpleDateFormat sdf = new sdf("DD/MM/YYYY HH24:mm");
             String fecha = sdf.format(noti.getFecha());
            * */
            //Thu, 08 Nov 2018 18:20:52 -0300
            int event = 0;
            event = xml.getEventType();
            while( event != XmlPullParser.END_DOCUMENT ) {
              //  Log.d("Wile","while");

                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                     //   Log.d("StartTag","tag: "+xml.getName());
                        //("title".equals(xmlPullParser.getName()) && n != null
                        if ("item".equals(xml.getName())){
                            noti = new Noticia();
                        }else if ("title".equals(xml.getName())&& noti != null) {
                            noti.setTitulo(xml.nextText());
                        }else if ("link".equals(xml.getName())&& noti != null) {
                            noti.setUrl(xml.nextText());
                        }else if ("description".equals(xml.getName())&& noti != null) {
                            noti.setDescripcion(xml.nextText());
                        }else if ("pubDate".equals(xml.getName())&& noti != null) {
                           // try {
                               // Log.d("TEST", ""+fechaParse.parse(xml.nextText()));
                              //  Date date = fechaParse.parse(xml.nextText());
                             //   Log.d("TEST", ""+date);
                               // noti.setFecha(date);
                           // } catch (ParseException e) {
                             //   e.printStackTrace();
                            //}
                        }else if("dc:creator".equals(xml.getName()) && noti != null)
                            noti.setCreador(xml.nextText());
                        else if("enclosure".equals(xml.getName()) && noti != null){
                            if(xml.getAttributeValue(null, "url") != null)
                                noti.setImagen(xml.getAttributeValue(null, "url"));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                       // Log.d("EndTag","tag: "+xml.getName());
                        if("item".equals(xml.getName()))
                        {
                            noticias.add(noti);
                        }
                        break;
                }
                event   = xml.next();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return noticias;
    }

    public static List<JSONObject> JASON(String s) throws JSONException {

        JSONArray listaJson = new JSONArray(s);
        List<JSONObject> a = new ArrayList<>();
        for(int i=0; i< listaJson.length();i++)
        {
            try {
                JSONObject o = listaJson.getJSONObject(i);
                a.add(o);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return a;
    }

//public static String json(String stringXML)


}