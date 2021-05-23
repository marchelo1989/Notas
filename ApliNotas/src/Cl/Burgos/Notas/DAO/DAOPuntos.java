/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.DAO;

import Cl.Burgos.Notas.BD.BD;
import Cl.Burgos.Notas.ENT.ClPuntos;
import Cl.Burgos.Notas.FUN.Log;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author march
 */
public class DAOPuntos {
    
    public List<ClPuntos> Buscar(ClPuntos clPuntos) {
        
        List<ClPuntos> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select Id,puntaje,nota from escalanota where puntaje="+clPuntos.getPunto();
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPuntos p = new ClPuntos(rs.getDouble("puntaje"),rs.getDouble("nota"));
              lista.add(p);
         }
         
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
            Log.log("Error en DAO: "+ex.getMessage());
        } catch (Exception ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
            Log.log("Error en DAO: "+ex.getMessage());
        }
        return lista;
    }
}
