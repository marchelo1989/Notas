/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.FUN;

import Cl.Burgos.Notas.ENT.ClPuntos;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author march
 */
public class Puntos {
    public static List<ClPuntos> nombres = new ArrayList();
    public static void cargarPuntos(){
        String punt ="0;1.0,0.1;1.0,0.2;1.1,0.3;1.1,0.4;1.1,0.5;1.2,0.6;1.2,0.7;1.3,0.8;1.3,0.9;1.3,\n" +
                        "1;1.4,1.1;1.4,1.2;1.4,1.3;1.5,1.4;1.5,1.5;1.6,1.6;1.6,1.7;1.6,1.8;1.7,1.9;1.7,\n" +
                        "2;1.7,2.1;1.8,2.2;1.8,2.3;1.9,2.4;1.9,2.5;1.9,2.6;2.0,2.7;2.0,2.8;2.0,2.9;2.1,\n" +
                        "3;2.1,3.1;2.1,3.2;2.2,3.3;2.2,3.4;2.3,3.5;2.3,3.6;2.3,3.7;2.4,3.8;2.4,3.9;2.4,\n" +
                        "4;2.5,4.1;2.5,4.2;2.6,4.3;2.6,4.4;2.6,4.5;2.7,4.6;2.7,4.7;2.7,4.8;2.8,4.9;2.8,\n" +
                        "5;2.9,5.1;2.9,5.2;2.9,5.3;3.0,5.4;3.0,5.5;3.0,5.6;3.1,5.7;3.1,5.8;3.1,5.9;3.2,\n" +
                        "6;3.2,6.1;3.3,6.2;3.3,6.3;3.3,6.4;3.4,6.5;3.4,6.6;3.4,6.7;3.5,6.8;3.5,6.9;3.6,\n" +
                        "7;3.6,7.1;3.6,7.2;3.7,7.3;3.7,7.4;3.7,7.5;3.8,7.6;3.8,7.7;3.9,7.8;3.9,7.9;3.9,\n" +
                        "8;4.0,8.1;4.0,8.2;4.1,8.3;4.1,8.4;4.2,8.5;4.2,8.6;4.3,8.7;4.3,8.8;4.4,8.9;4.4,\n" +
                        "9;4.5,9.1;4.6,9.2;4.6,9.3;4.7,9.4;4.7,9.5;4.8,9.6;4.8,9.7;4.9,9.8;4.9,9.9;5.0,\n" +
                        "10;5.1,10.1;5.1,10.2;5.2,10.3;5.2,10.4;5.3,10.5;5.3,10.6;5.4,10.7;5.4,10.8;5.5,\n" +
                        "10.9;5.6,11;5.6,11.1;5.7,11.2;5.7,11.3;5.8,11.4;5.8,11.5;5.9,11.6;5.9,11.7;6.0,11.8;6.1,11.9;6.1,\n" +
                        "12;6.2,12.1;6.2,12.2;6.3,12.3;6.3,12.4;6.4,12.5;6.4,12.6;6.5,12.7;6.6,12.8;6.6,12.9;6.7,\n" +
                        "13;6.7,13.1;6.8,13.2;6.8,13.3;6.9,13.4;6.9,13.5;7.0,";
        String[] arrayPuntos = punt.split(",");
        String[] parts = null ;
        // En este momento tenemos un array en el que cada elemento es un color.
        for (int i = 0; i < arrayPuntos.length; i++) {
//                System.out.println(arrayPuntos[i]);
                parts = arrayPuntos[i].split(Pattern.quote(";")); 
                double part1 = Double.parseDouble(parts[0]);
                double part2 = Double.parseDouble(parts[1]);
                ClPuntos p = new ClPuntos(part1, part2);
                nombres.add(p);
//                System.out.println(part1+"-"+part2);
        }
    }
}
