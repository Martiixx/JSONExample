package consola;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Duoc UC
 */
public class MainIndicadores {
    public static void main(String[] args)throws MalformedURLException, IOException{
        // Se obtiene la url donde está el recurso a utilizar
        URL url = new URL("http://mindicador.cl/api");
        /* se obtiene el objeto JSON a partir de un reader creado desde un bjeto stream que se obtiene a partir de la URL*/
        InputStream entrada = url.openStream();
        JsonReader reader = Json.createReader(entrada);
        JsonObject objeto= reader.readObject();  
        
        double valorUF = Double.parseDouble(objeto.getJsonObject("uf").get("valor").toString());
        System.out.println("Valor UF : "+ valorUF);
        System.out.println("JSON Consumido");
        
        //Obtendremos indicadores segun entrada por teclado
        Scanner teclado = new Scanner(System.in);
        System.out.println("Indicador: ");
        String indicador = teclado.next();
        
        System.out.println("Obteniendo variaciones de "+ indicador +"...");
        URL url2 = new URL("http://mindicador.cl/api"+  "/"+ indicador);
        InputStream is = url2.openStream();
        JsonReader rdr = Json.createReader(is);
        JsonObject obj= rdr.readObject();  
        for(int i=0; i<obj.getJsonArray("serie").size(); i++){
            System.out.println("valor"+(i+1)+" : "+ obj.getJsonArray("serie").getJsonObject(i).get("valor"));
        }
    }
}
