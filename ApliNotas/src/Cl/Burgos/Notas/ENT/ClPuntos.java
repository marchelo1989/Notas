/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.ENT;

/**
 *
 * @author march
 */
public class ClPuntos {
    private double punto;
    private double nota;

    public double getPunto() {
        return punto;
    }

    public void setPunto(double punto) {
        this.punto = punto;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public ClPuntos(double punto, double nota) {
        this.punto = punto;
        this.nota = nota;
    }
    
    
}
