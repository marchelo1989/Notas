/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.Main;

import Cl.Burgos.Notas.FUN.Actualizacion;
import Cl.Burgos.Notas.FUN.Confi;
import Cl.Burgos.Notas.GUI.FrHome;
import javax.swing.JOptionPane;

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
//        new FrHome().setVisible(true);
        if(buscarUpdate()==false){
            new FrHome().setVisible(true);
        }
    }
    public static boolean buscarUpdate(){
        boolean resp;
        if(Actualizacion.verificarConexion()){
            if(Actualizacion.obtenerVersion().equals(Confi.Version)){
                resp=false;
            }else{
                resp=true;
                int respu = JOptionPane.showConfirmDialog(null, "Version "+Actualizacion.obtenerVersion()+ " Diponible \n¿Desea Descargar?");
                if(respu==0){
                    JOptionPane.showMessageDialog(null, "Descargando Update \nEspere Mensaje");
                    Actualizacion.descargarUpdate();
                    resp=true;
                }else{
                    resp=false;
                }
            }
        }else{
            resp=false;
        }
        return resp;
    }
    
}
