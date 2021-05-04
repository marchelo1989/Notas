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
public class ClNota {
    private String nombre;
    private int retraso;
    
    private int planteoPro;
    private int definicion;
    private int presentacion;
    
    private int estructura;
    private int calidad;
    private int relacionCentral;
    
    private int relacionInicial;
    private int aportacion;
    private int relevancia;
    
    private int bibliografia;
    private int citas;
    
    private int ortografia;
    private int extencion;
    
    private double puntaje;
    private double notaP;
    private int descuenhtoRetraso;
    private double notaE;
    
    private String comentario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRetraso() {
        return retraso;
    }

    public void setRetraso(int retraso) {
        this.retraso = retraso;
    }

    public int getPlanteoPro() {
        return planteoPro;
    }

    public void setPlanteoPro(int planteoPro) {
        this.planteoPro = planteoPro;
    }

    public int getDefinicion() {
        return definicion;
    }

    public void setDefinicion(int definicion) {
        this.definicion = definicion;
    }

    public int getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(int presentacion) {
        this.presentacion = presentacion;
    }

    public int getEstructura() {
        return estructura;
    }

    public void setEstructura(int estructura) {
        this.estructura = estructura;
    }

    public int getCalidad() {
        return calidad;
    }

    public void setCalidad(int calidad) {
        this.calidad = calidad;
    }

    public int getRelacionCentral() {
        return relacionCentral;
    }

    public void setRelacionCentral(int relacionCentral) {
        this.relacionCentral = relacionCentral;
    }

    public int getRelacionInicial() {
        return relacionInicial;
    }

    public void setRelacionInicial(int relacionInicial) {
        this.relacionInicial = relacionInicial;
    }

    public int getAportacion() {
        return aportacion;
    }

    public void setAportacion(int aportacion) {
        this.aportacion = aportacion;
    }

    public int getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(int relevancia) {
        this.relevancia = relevancia;
    }

    public int getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(int bibliografia) {
        this.bibliografia = bibliografia;
    }

    public int getCitas() {
        return citas;
    }

    public void setCitas(int citas) {
        this.citas = citas;
    }

    public int getOrtografia() {
        return ortografia;
    }

    public void setOrtografia(int ortografia) {
        this.ortografia = ortografia;
    }

    public int getExtencion() {
        return extencion;
    }

    public void setExtencion(int extencion) {
        this.extencion = extencion;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public double getNotaP() {
        return notaP;
    }

    public void setNotaP(double notaP) {
        this.notaP = notaP;
    }

    public int getDescuenhtoRetraso() {
        return descuenhtoRetraso;
    }

    public void setDescuenhtoRetraso(int descuenhtoRetraso) {
        this.descuenhtoRetraso = descuenhtoRetraso;
    }

    public double getNotaE() {
        return notaE;
    }

    public void setNotaE(double notaE) {
        this.notaE = notaE;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ClNota(String nombre, int retraso, int planteoPro, int definicion, int presentacion, int estructura, int calidad, int relacionCentral, int relacionInicial, int aportacion, int relevancia, int bibliografia, int citas, int ortografia, int extencion, double puntaje, double notaP, int descuenhtoRetraso, double notaE, String comentario) {
        this.nombre = nombre;
        this.retraso = retraso;
        this.planteoPro = planteoPro;
        this.definicion = definicion;
        this.presentacion = presentacion;
        this.estructura = estructura;
        this.calidad = calidad;
        this.relacionCentral = relacionCentral;
        this.relacionInicial = relacionInicial;
        this.aportacion = aportacion;
        this.relevancia = relevancia;
        this.bibliografia = bibliografia;
        this.citas = citas;
        this.ortografia = ortografia;
        this.extencion = extencion;
        this.puntaje = puntaje;
        this.notaP = notaP;
        this.descuenhtoRetraso = descuenhtoRetraso;
        this.notaE = notaE;
        this.comentario = comentario;
    }
    
    
}
