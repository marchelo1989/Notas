/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.Main;

import Cl.Burgos.Notas.FUN.Archivo;
import Cl.Burgos.Notas.FUN.Log;
import Cl.Burgos.Notas.FUN.Puntos;
import Cl.Burgos.Notas.GUI.FrHome;

/**
 *
 * @author march
 */
public class ApliNotas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Puntos().cargarPuntos();
//        new Archivo().leerARchivo();
        new Log().CrearCarpetaLog();
        new FrHome().setVisible(true);
    }
    
}
