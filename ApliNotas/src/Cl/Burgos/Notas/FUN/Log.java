/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.FUN;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author marchelo
 */
public class Log {
    public static void log(String msg){
//        String url="c:/Users/march/Desktop/myfile.log";
//        String url="f:/myfile.log";
        String url="Errores/Log.log";
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(url, true)))) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            out.println(dateFormat.format(date) + ":"+ msg + "\r\n");
            
//            AbrirLog();
            
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            JOptionPane.showMessageDialog(null,"Hubo un error"+e);
        }
    }
    
    public static void AbrirLog(){
        //Archivo Log
        String url="Errores/Log.log";        
        try {
            File directorio = new File(url);
            Desktop.getDesktop().open(directorio);
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CrearCarpetaLog(){
        //Directorio destino para las descargas
//            String folder = "Update/";
            String folder = "Errores/";
            
            //Crea el directorio de destino en caso de que no exista
            File dir = new File(folder);
            
            if (!dir.exists())
                if (!dir.mkdir())
                    // no se pudo crear la carpeta de destino
                    return;
    }
}
