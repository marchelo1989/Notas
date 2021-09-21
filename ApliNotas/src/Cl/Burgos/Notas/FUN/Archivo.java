/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.FUN;

import Cl.Burgos.Notas.ENT.ClNota;
import Cl.Burgos.Notas.ENT.ClPuntos;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author march
 */
public class Archivo {
    public static List<ClPuntos> nombres2 = new ArrayList();
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
                nombres2.add(p);
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
    
    public void CrearArchivoWEB(ClNota clNota) {
        try {

            PrintWriter writer = new PrintWriter("nota.html", "UTF-8");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<title>notas</title>");
            writer.println("</head>");
            
            writer.println("<body>");
            writer.println("<h1>Notas Ensayo</h1>");
            
            writer.println("<table border=\"1\">");
            writer.println("<tr>");
            writer.println("<th width=\"20%\" scope=\"col\">Nombre</th>");
            writer.println("<th width=\"10%\" scope=\"col\">Retraso</th>");
            writer.println("<th width=\"70%\" scope=\"col\"></th>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>"+clNota.getNombre()+"</td>");
            writer.println("<td align=\"center\">"+clNota.getRetraso()+"</td>");
            writer.println("<td>El trabajo fue entregado con "+clNota.getRetraso()+" día de retraso!</td>");
            writer.println("</tr>");
            writer.println("</table>");
            
            writer.println("<p></p>");
            
            writer.println("<table border=\"1\">");
            writer.println("<tr>");
            writer.println("<th colspan=\"3\">Criterios de evaluación</th>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<th colspan=\"2\">INTRODUCCIÓN (20%)</th>");
            writer.println("<th>Comentario en rubrica</th>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td width=\"30%\" >Planteo del problema</td>");
            writer.println("<td width=\"10%\" align=\"center\">"+clNota.getPlanteoPro()+"</td>");
            String PlanteoPro="";
            if(clNota.getPlanteoPro()==1){
                PlanteoPro="No se plantea problema";
            }
            if(clNota.getPlanteoPro()==2){
                PlanteoPro="Se plantea el problema irrelevante para los objetivos del curso";
            }
            if(clNota.getPlanteoPro()==3){
                PlanteoPro="Se plantea un problema relevante, aunque de manera confusa y desordenada";
            }
            if(clNota.getPlanteoPro()==4){
                PlanteoPro="Se plantea un problema con claridad y concisión y sustentado en la literatura y lo discutido en clases";
            }
            if(clNota.getPlanteoPro()==5){
                PlanteoPro="Se plantea un  problema original con claridad y concisión y bien sustentado en la literatura y lo discutido en clases";
            }
            writer.println("<td width=\"60%\">"+PlanteoPro+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Definición de términos relevantes</td>");
            writer.println("<td align=\"center\">"+clNota.getDefinicion()+"</td>");
            String Definicion="";
            if(clNota.getDefinicion()==1){
                Definicion="No hay definiciones";
            }
            if(clNota.getDefinicion()==2){
                Definicion="Hay definiciones equivocadas, confusión conceptual, mala atribución a autores";
            }
            if(clNota.getDefinicion()==3){
                Definicion="Se muestra cierto conocimiento de definiciones, pero no se presentan correctamente";
            }
            if(clNota.getDefinicion()==4){
                Definicion="Se presentan definiciones correctas y atribuidas a sus autores";
            }
            if(clNota.getDefinicion()==5){
                Definicion="Se define correctamente de acuerdo a literatura y se compara con otras definiciones pertinentes";
            }
            writer.println("<td>"+Definicion+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Presentación de argumento central</td>");
            writer.println("<td align=\"center\">"+clNota.getPresentacion()+"</td>");
            String Presentacion="";
            if(clNota.getPresentacion()==1){
                Presentacion="No hay argumento";
            }
            if(clNota.getPresentacion()==2){
                Presentacion="Se presenta un argumento, pero basado en definiciones equivocadas";
            }
            if(clNota.getPresentacion()==3){
                Presentacion="Se esboza un argumento, aunque confuso y desordenado";
            }
            if(clNota.getPresentacion()==4){
                Presentacion="Se presenta clara y concisamente un argumento ya discutido en la literatura o lo discutido en clases";
            }
            if(clNota.getPresentacion()==5){
                Presentacion="Se presenta un argumento original, claro, conciso y bien sustentado en la literatura o lo discutido en clases";
            }
            writer.println("<td>"+Presentacion+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<th colspan=\"2\">DESARROLLO (30%)</th>");
            writer.println("<th></th>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Estructura y orden en la presentación</td>");
            writer.println("<td align=\"center\">"+clNota.getEstructura()+"</td>");
            String Estructura="";
            if(clNota.getEstructura()==1){
                Estructura="Presentación desorganizada que dificulta la lectura";
            }
            if(clNota.getEstructura()==2){
                Estructura="Presentación impide detectar la presencia de una introducción, desarrollo y conclusión";
            }
            if(clNota.getEstructura()==3){
                Estructura="Hay introducción, desarrollo y conclusión, pero están confusos y desordenados";
            }
            if(clNota.getEstructura()==4){
                Estructura="Se reconocen introducción, desarrollo y conclusión y se organizan ordenadamente";
            }
            if(clNota.getEstructura()==5){
                Estructura="Se reconocen introducción, desarrollo y conclusión y se organizan de manera original";
            }
            writer.println("<td>"+Estructura+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Calidad de los análisis</td>");
            writer.println("<td align=\"center\">"+clNota.getCalidad()+"</td>");
            String Calidad="";
            if(clNota.getCalidad()==1){
                Calidad="No hay análisis";
            }
            if(clNota.getCalidad()==2){
                Calidad="Hay análisis, pero basado en premisas o definiciones equivocadas";
            }
            if(clNota.getCalidad()==3){
                Calidad="La evidencia y/o la literatura se presentan desorganizadas";
            }
            if(clNota.getCalidad()==4){
                Calidad="Hay un correcto análisis de la literatura y/o la evidencia";
            }
            if(clNota.getCalidad()==5){
                Calidad="Hay análisis crítico y original de la literatura y/o la evidencia";
            }
            writer.println("<td>"+Calidad+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Relación con argumento central</td>");
            writer.println("<td align=\"center\">"+clNota.getRelacionCentral()+"</td>");
            String RelacionCentral="";
            if(clNota.getRelacionCentral()==1){
                RelacionCentral="No hay relación con el argumento inicial";
            }
            if(clNota.getRelacionCentral()==2){
                RelacionCentral="Se esboza alguna relación con el argumento inicial , pero es confusa o forzada";
            }
            if(clNota.getRelacionCentral()==3){
                RelacionCentral="Se presentan algunas relaciones con el argumento inicial, aunque con definiciones informales";
            }
            if(clNota.getRelacionCentral()==4){
                RelacionCentral="El argumento inicial se relaciona con el análisis presentado";
            }
            if(clNota.getRelacionCentral()==5){
                RelacionCentral="El argumento central y el análisis presentado se relacionan de manera original y creativa";
            }
            writer.println("<td>"+RelacionCentral+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<th colspan=\"2\">CONCLUSIONES (20%)</th>");
            writer.println("<th></th>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Relación con el argumento inicial</td>");
            writer.println("<td align=\"center\">"+clNota.getRelacionInicial()+"</td>");
            String RelacionInicial="";
            if(clNota.getRelacionInicial()==1){
                RelacionInicial="No hay relación con el argumento inicial";
            }
            if(clNota.getRelacionInicial()==2){
                RelacionInicial="Se esboza alguna relación con el argumento inicial , pero es confusa o forzada";
            }
            if(clNota.getRelacionInicial()==3){
                RelacionInicial="Se presentan algunas relaciones con el argumento inicial, aunque con definiciones informales";
            }
            if(clNota.getRelacionInicial()==4){
                RelacionInicial="El argumento inicial se relaciona con el análisis presentado";
            }
            if(clNota.getRelacionInicial()==5){
                RelacionInicial="El argumento central y el análisis presentado se relacionan de manera original y creativa";
            }
            writer.println("<td>"+RelacionInicial+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Aportación personal</td>");
            writer.println("<td align=\"center\">"+clNota.getAportacion()+"</td>");
            String Aportacion="";
            if(clNota.getAportacion()==1){
                Aportacion="No se presenta contribución";
            }
            if(clNota.getAportacion()==2){
                Aportacion="Se presentan ideas ajenas como propias";
            }
            if(clNota.getAportacion()==3){
                Aportacion="Hay contribución, pero confusa o basadas en definiciones  informales";
            }
            if(clNota.getAportacion()==4){
                Aportacion="La contribución está clara y correctamente relacionada con la literatura o lo discutido en clases";
            }
            if(clNota.getAportacion()==5){
                Aportacion="Hay contribuciones críticas y originales sustentadas en la literatura o lo discutido en clases";
            }
            writer.println("<td>"+Aportacion+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Relevancia para la discusión</td>");
            writer.println("<td align=\"center\">"+clNota.getRelevancia()+"</td>");
            String Relevancia="";
            if(clNota.getRelevancia()==1){
                Relevancia="No se presentan conclusiones";
            }
            if(clNota.getRelevancia()==2){
                Relevancia="Las conclusiones no se relacionan con el objetivo del curso";
            }
            if(clNota.getRelevancia()==3){
                Relevancia="Se sugiere relevancia, pero sustentada en definiciones informales";
            }
            if(clNota.getRelevancia()==4){
                Relevancia="Las conclusiones son relevantes para el curso.";
            }
            if(clNota.getRelevancia()==5){
                Relevancia="Las conclusiones expanden el conocimiento presentado en el curso";
            }
            writer.println("<td>"+Relevancia+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<th colspan=\"2\">BIBLIOGRAFÍA (10%)</th>");
            writer.println("<th></th>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Bibliografía</td>");
            writer.println("<td align=\"center\">"+clNota.getBibliografia()+"</td>");
            String Bibliografia="";
            if(clNota.getBibliografia()==1){
                Bibliografia="No hay bibliografía";
            }
            if(clNota.getBibliografia()==2){
                Bibliografia="La bibliografía está desordenada, faltan datos del material consultado y cuando están presentes, no respetan el formato requerido";
            }
            if(clNota.getBibliografia()==3){
                Bibliografia="Se presentan algunos autores y material consultado y con errores de acuerdo al formato requerido";
            }
            if(clNota.getBibliografia()==4){
                Bibliografia="La mayoría de los autores están incluidos, pero algunos datos están ausentes o no se presentan de acuerdo al formato requerido";
            }
            if(clNota.getBibliografia()==5){
                Bibliografia="Toda la bibliografía se presenta por orden alfabético y de acuerdo al formato requerido";
            }
            writer.println("<td>"+Bibliografia+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Citas en texto</td>");
            writer.println("<td align=\"center\">"+clNota.getCitas()+"</td>");
            String Citas="";
            if(clNota.getCitas()==1){
                Citas="No hay citaciones";
            }
            if(clNota.getCitas()==2){
                Citas="Se cita material equivocado. Se atribuyen autores o fechas de publicaciones errados ";
            }
            if(clNota.getCitas()==3){
                Citas="Se cita a los autores y sus años de publicación en algunos casos, con errores de acuerdo con el formato requerido";
            }
            if(clNota.getCitas()==4){
                Citas="Se cita correctamente a autores y años de publicación en la mayoría de los casos";
            }
            if(clNota.getCitas()==5){
                Citas="En todo el texto se presentan las citas a los materiales consultados de acuerdo con el formato requerido";
            }
            writer.println("<td>"+Citas+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<th colspan=\"2\">ASPECTOS FORMALES (20%)</th>");
            writer.println("<th></th>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Ortografía</td>");
            writer.println("<td align=\"center\">"+clNota.getOrtografia()+"</td>");
            String Ortografia="";
            if(clNota.getOrtografia()==1){
                Ortografia="52-70+ faltas de ortografía acentual, literal o puntual.";
            }
            if(clNota.getOrtografia()==2){
                Ortografia="25-52 faltas de ortografía acentual, literal o puntual.";
            }
            if(clNota.getOrtografia()==3){
                Ortografia="18-35 faltas de ortografía acentual, literal o puntual.";
            }
            if(clNota.getOrtografia()==4){
                Ortografia="1-17 faltas de ortografía acentual, literal o puntual.";
            }
            if(clNota.getOrtografia()==5){
                Ortografia="0 faltas de ortografía acentual, literal o puntual.";
            }
            writer.println("<td>"+Ortografia+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Extensión</td>");
            writer.println("<td align=\"center\">"+clNota.getExtencion()+"</td>");
            String Extencion="";
            if(clNota.getExtencion()==1){
                Extencion="Supera o no alcanza la extensión requerida.";
            }
            if(clNota.getExtencion()==2){
                Extencion="";
            }
            if(clNota.getExtencion()==3){
                Extencion="";
            }
            if(clNota.getExtencion()==4){
                Extencion="";
            }
            if(clNota.getExtencion()==5){
                Extencion="Dentro de la extensión requerida.";
            }
            writer.println("<td>"+Extencion+"</td>");
            writer.println("</tr>");
            
            
            writer.println("<tr>");
            writer.println("<td>Puntaje</td>");
            writer.println("<td align=\"center\">"+clNota.getPuntaje()+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Nota Parcial</td>");
            writer.println("<td align=\"center\">"+clNota.getNotaP()+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Descuento por retraso</td>");
            writer.println("<td align=\"center\">"+clNota.getDescuenhtoRetraso()+"</td>");
            writer.println("</tr>");
            
            writer.println("<tr>");
            writer.println("<td>Nota Ensayo</td>");
            writer.println("<td align=\"center\">"+clNota.getNotaE()+"</td>");
            writer.println("</tr>");
            
            writer.println("</table>");
            
            writer.println("<p>Comentario: </p>"+clNota.getComentario());
            
            writer.println("</body>");
            writer.println("</html>");
            writer.close();
            
        } catch (Exception e1) {
            System.out.println("Mensaje error: " + e1.getMessage());
        }finally{
            try {
                File objetofile = new File ("nota.html");
                Desktop.getDesktop().open(objetofile);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
