/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.FUN;

import Cl.Burgos.Notas.ENT.ClPuntos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author march
 */
public class Archivo {
    public static List<ClPuntos> nombres = new ArrayList();
    public void leerARchivo(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String[] parts = null ;
        String separador = ";";
        
        
          try {
             // Apertura del fichero y creacion de BufferedReader para poder
             // hacer una lectura comoda (disponer del metodo readLine()).
             archivo = new File ("escalaNotas.txt");
             fr = new FileReader (archivo);
             br = new BufferedReader(fr);

             // Lectura del fichero
             String linea;
             while((linea=br.readLine())!=null){
//                System.out.println(linea);
                parts = linea.split(Pattern.quote(";")); 
                double part1 = Double.parseDouble(parts[0]);
                double part2 = Double.parseDouble(parts[1]);
                ClPuntos p = new ClPuntos(part1, part2);
                nombres.add(p);
             }
          }
          catch(Exception e){
             e.printStackTrace();
          }finally{
             // En el finally cerramos el fichero, para asegurarnos
             // que se cierra tanto si todo va bien como si salta 
             // una excepcion.
             try{                    
                if( null != fr ){   
                   fr.close();     
                }                  
             }catch (Exception e2){ 
                e2.printStackTrace();
             }
          }
    }
    
    public void EscribeArchivo(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("c:/prueba.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}
