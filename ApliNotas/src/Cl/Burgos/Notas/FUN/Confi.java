/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.FUN;

/**
 *
 * @author march
 * v1.10
 */
public interface Confi {
    
    //Configuracion de Directorio
    static String SO = System.getProperty("os.name");
    
    //Configuraciones para Update
    static String nameArchivo = "ApliNotas.zip";
    static String CarpetaUpdate = "Update/";
    static String Version = "1.7";
    static String UrlVersion = "https://raw.githubusercontent.com/marchelo1989/Notas/main/Update/version.txt";
    static String UrlDescarga = "https://raw.githubusercontent.com/marchelo1989/Notas/main/Update/Descargas.txt";
        
    //Pruebas
    static String Nombre="Prueba 1";
    static String Retraso="0";
    static String intro1="5";
    static String intro2="5";
    static String intro3="5";
    
    static String Desa1="5";
    static String Desa2="5";
    static String Desa3="5";
    
    static String Conclu1="5";
    static String Conclu2="5";
    static String Conclu3="5";
    
    static String Bibli1="5";
    static String Bibli2="5";
    
    static String Apec1="5";
    static String Apec2="";
}
