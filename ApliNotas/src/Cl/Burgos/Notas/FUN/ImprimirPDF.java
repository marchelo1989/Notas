/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Notas.FUN;
//
//import Cl.Burgos.RepararPC.ENT.ClCliente;
//import Cl.Burgos.RepararPC.ENT.ClComputador;
//import Cl.Burgos.RepararPC.ENT.ClLogin;

import Cl.Burgos.Notas.ENT.ClNota;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author march
 */
public class ImprimirPDF {
    
    //Variables del Log4j
//    static  org.apache.log4j.Logger log =org.apache.log4j.Logger.getLogger(ImprimirPDF.class);
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
    private static Font blackFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.BLACK);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
    
    private Document doc = new Document();
    static String SO = System.getProperty("os.name");
    //Imprimir las tabla en PDF
    public void ImprimirPDF(JTable table,String nombreArc){
        javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter("Adobe Acrobat PDF", "pdf");
        final JFileChooser miArchivo=new JFileChooser();
        miArchivo.setFileFilter(filtroWord);
        miArchivo.setDialogTitle("Guardar archivo");
        miArchivo.setMultiSelectionEnabled(false);
        miArchivo.setAcceptAllFileFilterUsed(false);
        String userDir = System.getProperty("user.home");
        //preferencia de ubicacion
        if(SO.startsWith("Windows")){
            miArchivo.setCurrentDirectory(new File(userDir +"/Desktop"));
        }else{
            miArchivo.setCurrentDirectory(new File(userDir +"/Escritorio"));
        }
        //El nombre del Archivo
        miArchivo.setSelectedFile(new File("Reporte "+nombreArc));
        int aceptar=miArchivo.showSaveDialog(null);
        miArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(aceptar==JFileChooser.APPROVE_OPTION){
            File fileWord=miArchivo.getSelectedFile();
            String nombre=fileWord.getName();
        try {    if(nombre.indexOf('.')==-1){
                nombre+=".pdf";
                PdfWriter.getInstance(doc, new FileOutputStream(fileWord.getParentFile()+"/"+nombre));
            }
            doc.open();
            
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            //Agregando encabezados de tabla
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }
            //Extraer datos de la JTable e insertarlo en PdfPTable
            for (int rows = 0; rows < table.getRowCount() - 1; rows++) {
                for (int cols = 0; cols < table.getColumnCount(); cols++) {
                    pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());

                }
            }
            Paragraph preface = new Paragraph();
                // Añadimos una línea vacía
                addEmptyLine(preface, 1);
                // Permite escribir un encabezado grande
                preface.add(new Paragraph("Título del documento", catFont));
                addEmptyLine(preface, 1);
                
                // Creará: Informe generado por: _name, _date
                preface.add(new Paragraph("Informe generado " + new Date(),smallBold));
                addEmptyLine(preface, 1);
                
                preface.add(new Paragraph("Este documento describe algo que es muy importante ",smallBold));
                addEmptyLine(preface, 1);

                preface.add(new Paragraph("Este documento es una versión preliminar y no está sujeto a su contrato de licencia o cualquier otro acuerdo.",redFont));
            addEmptyLine(preface, 1);
            doc.add(preface);
            
            doc.add(pdfTable);
            doc.close();
//            System.out.println("done");
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null,"Hubo un error"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//            log.info(ex.getMessage());
            Log.log("Error en Clase ImprimirPDF: "+ex.getMessage());
           
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Hubo un error"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//            log.info(ex.getMessage());
            Log.log("Error en Clase ImprimirPDF: "+ex.getMessage());
        }finally{
                try {
                    if(System.getProperty("os.name").equals("Linux")){
//                        Runtime.getRuntime().exec("libreoffice"+fileWord.getAbsolutePath());
                        File objetofile = new File (fileWord.getAbsolutePath()+".pdf");
                        Desktop.getDesktop().open(objetofile);
                    }
                    else{
//                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+fileWord.getAbsolutePath());
                        File objetofile = new File (fileWord.getAbsolutePath()+".pdf");
                        Desktop.getDesktop().open(objetofile);
                    }
                }
                catch (IOException ex) {
                    Logger.getLogger(ImprimirPDF.class.getName()).log(Level.SEVERE, null, ex);
//                    log.info(ex.getMessage());
                    Log.log("Error en Clase ImprimirPDF: "+ex.getMessage());
                }
            }

    }
    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
                for (int i = 0; i < number; i++) {
                        paragraph.add(new Paragraph(" "));
                }
        }
    //Imprimir informacion de Clase
    public void Crear(ClNota clNota){
//        Directorio d = new Directorio();
        javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter("Adobe Acrobat PDF", "pdf");
        final JFileChooser miArchivo=new JFileChooser();
        miArchivo.setFileFilter(filtroWord);
        miArchivo.setDialogTitle("Guardar archivo");
        miArchivo.setMultiSelectionEnabled(false);
        miArchivo.setAcceptAllFileFilterUsed(false);
        String userDir = System.getProperty("user.home");
        //preferencia de ubicacion
        if(SO.startsWith("Windows")){
            miArchivo.setCurrentDirectory(new File(userDir +"/Desktop"));
        }else{
            miArchivo.setCurrentDirectory(new File(userDir +"/Escritorio"));
        }
        //El nombre del Archivo
        String nomArchivo=clNota.getNombre();
        miArchivo.setSelectedFile(new File(nomArchivo));
        int aceptar=miArchivo.showSaveDialog(null);
        miArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(aceptar==JFileChooser.APPROVE_OPTION){
            File fileWord=miArchivo.getSelectedFile();
            String nombre=fileWord.getName();
        try {    
            if(nombre.indexOf('.')==-1){
                nombre+=".pdf";
                PdfWriter.getInstance(doc, new FileOutputStream(fileWord.getParentFile()+"/"+nombre));
            }else{
                nombre+=".pdf";
                PdfWriter.getInstance(doc, new FileOutputStream(fileWord.getParentFile()+"/"+nombre));
            }
            doc.open();
            Paragraph preface = new Paragraph();
                // Añadimos una línea vacía
                addEmptyLine(preface, 1);
                // Permite escribir un encabezado grande
                preface.add(new Paragraph("Notas Ensayo", catFont));
                addEmptyLine(preface, 1);
                
                // Creará: Informe generado por: _name, _date
//                preface.add(new Paragraph("Informe generado por: Nombre de usuario " + new Date(),smallBold));
//                addEmptyLine(preface, 1);
                
//                preface.add(new Paragraph("El documento es una versión preliminar y describe sobre el servicio prestado al cliente.",smallBold));
//                addEmptyLine(preface, 1);

//                preface.add(new Paragraph("Por lo cual Documento contiene información sobre la reparación efectuada al dispositivo "
//                        + "entregado por el cliente en la cual se definen algunos datos sobre el cliente y dispositivo para "
//                        + "demostrar mayor caridad sobre la reparación efectuada.",blackFont));
//                addEmptyLine(preface, 1);
                
            doc.add(preface);
            
//                // a table with three columns
//                PdfPTable table = new PdfPTable(4);
//                // the cell object
//                PdfPCell cell;
//                // we add a cell with colspan 3
//                cell = new PdfPCell(new Phrase("Datos del Cliente",redFont));
//                cell.setColspan(4);
//                table.addCell(cell);
////                // now we add a cell with rowspan 2
////                cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
////                cell.setRowspan(2);
////                table.addCell(cell);
//                // we add the four remaining cells with addCell()
//                table.addCell("NOMBRE");
//                table.addCell("CORREO");
//                table.addCell("CELULAR");
//                
//                table.addCell(clNota.getNombre());
//                table.addCell(clNota.getNombre());
//                table.addCell(clNota.getNombre());
//            doc.add(table);
            
//            Paragraph preface2 = new Paragraph();
//            addEmptyLine(preface2, 1);
//            preface2.add(new Paragraph("Descripción del Servicio",redFont));
//                addEmptyLine(preface2, 1);
//            doc.add(preface2);
            
            // a table with three columns
                PdfPTable table2 = new PdfPTable(2);
                // the cell object
//                PdfPCell cell2;
                // we add a cell with colspan 3
//                cell2 = new PdfPCell(new Phrase("Datos del PC o Notebook",redFont));
//                cell2 = new PdfPCell();
//                cell2.setColspan(2);
//                table2.addCell(cell2);
                float[] medidaCeldas = {150f, 50f};
                table2.setWidths(medidaCeldas);
                table2.setFooterRows(3);
                table2.addCell("Nombre");
                table2.addCell("Retraso");
                table2.addCell(clNota.getNombre());
                table2.addCell(Integer.toString(clNota.getRetraso()));
                table2.addCell(" ");
                table2.addCell(" ");
                
                table2.addCell("Criterios de evaluación");
                table2.addCell(" ");        
                table2.addCell("INTRODUCCIÓN (20%)");
                table2.addCell(" ");
                table2.addCell("Planteo del problema");
                table2.addCell(Integer.toString(clNota.getPlanteoPro()));
                table2.addCell("Definición de términos relevantes");
                table2.addCell(Integer.toString(clNota.getDefinicion()));
                table2.addCell("Presentación de argumento central");
                table2.addCell(Integer.toString(clNota.getPresentacion()));
                
                table2.addCell("DESARROLLO (30%)");
                table2.addCell(" ");
                table2.addCell("Estructura y orden en la presentación");
                table2.addCell(Integer.toString(clNota.getEstructura()));
                table2.addCell("Calidad de los análisis");
                table2.addCell(Integer.toString(clNota.getCalidad()));
                table2.addCell("Relación con argumento central");
                table2.addCell(Integer.toString(clNota.getRelacionCentral()));
                
                table2.addCell("CONCLUSIONES (20%)");
                table2.addCell(" ");
                table2.addCell("Relación con el argumento inicial");
                table2.addCell(Integer.toString(clNota.getRelacionInicial()));
                table2.addCell("Aportación personal");
                table2.addCell(Integer.toString(clNota.getAportacion()));
                table2.addCell("Relevancia para la discusión");
                table2.addCell(Integer.toString(clNota.getRelevancia()));
                
                table2.addCell("BIBLIOGRAFÍA  (10%)");
                table2.addCell(" ");
                table2.addCell("Bibliografía");
                table2.addCell(Integer.toString(clNota.getBibliografia()));
                table2.addCell("Citas en texto");
                table2.addCell(Integer.toString(clNota.getCitas()));
                
                table2.addCell("ASPECTOS FORMALES (20%)");
                table2.addCell(" ");
                table2.addCell("Ortografía");
                table2.addCell(Integer.toString(clNota.getOrtografia()));
                table2.addCell("Extensión");
                table2.addCell(Integer.toString(clNota.getExtencion()));
                table2.addCell("Puntaje");
                table2.addCell(Double.toString(clNota.getPuntaje()));
                table2.addCell("Nota Parcial");
                table2.addCell(Double.toString(clNota.getNotaP()));
                table2.addCell("Descuento por retraso");
                table2.addCell(Integer.toString(clNota.getDescuenhtoRetraso()));
                table2.addCell("Nota Ensayo");
                table2.addCell(Double.toString(clNota.getNotaE()));
                table2.addCell(" ");
                table2.addCell(" ");
                table2.addCell("Comentario:"+clNota.getComentario());
                table2.addCell(" ");
                
                
            doc.add(table2);
            
//            Paragraph preface3 = new Paragraph();
//            addEmptyLine(preface3, 1);
//            doc.add(preface3);
//            
//            // a table with three columns
//                PdfPTable table3 = new PdfPTable(3);
//                // the cell object
//                PdfPCell cell3;
//                // we add a cell with colspan 3
//                cell3 = new PdfPCell(new Phrase("Sistema Operativo",redFont));
//                cell3.setColspan(3);
//                table3.addCell(cell3);
//                table3.addCell("SO");
//                table3.addCell("ARQUITECTURA DEL SO");
//                table3.addCell("VERSIÓN");
//                
////                table3.addCell(computador.getSistemaOpe());
////                table3.addCell(computador.getArquitectura());
////                table3.addCell(computador.getVersion());
//            doc.add(table3);
            
//            Paragraph preface4 = new Paragraph();
//            addEmptyLine(preface4, 1);
//            preface4.add(new Paragraph("Este documento contiene toda la información sobre la reparación efectuada en su dispositivo, "
//                    + "por lo cual se le otorgara 1 mes de garantía sobre dicha reparación.",blackFont));
//            
            
//            doc.add(preface4);
            
            doc.close();
            
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null,"Hubo un error"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//            log.info(ex.getMessage());
            Log.log("Error en Clase ImprimirPDF: "+ex.getMessage());
        }   catch (FileNotFoundException ex) {
            Logger.getLogger(ImprimirPDF.class.getName()).log(Level.SEVERE, null, ex);
//            log.info(ex.getMessage());
            Log.log("Error en Clase ImprimirPDF: "+ex.getMessage());
        }
        finally{
                try {
                    if(System.getProperty("os.name").equals("Linux")){
//                        Runtime.getRuntime().exec("libreoffice"+fileWord.getAbsolutePath());
                        File objetofile = new File (fileWord.getAbsolutePath()+".pdf");
                        Desktop.getDesktop().open(objetofile);
                    }
                    else{
//                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+fileWord.getAbsolutePath());
                        File objetofile = new File (fileWord.getAbsolutePath()+".pdf");
                        Desktop.getDesktop().open(objetofile);
                    }
                }
                catch (IOException ex) {
                    Logger.getLogger(ImprimirPDF.class.getName()).log(Level.SEVERE, null, ex);
//                    log.info(ex.getMessage());
                    Log.log("Error en Clase ImprimirPDF: "+ex.getMessage());
                }
            }

    }
    }
    
    //
    public void CrearTablaPDF(ClNota clNota){
        try {
            javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter("Adobe Acrobat PDF", "pdf");
            final JFileChooser miArchivo=new JFileChooser();
            miArchivo.setFileFilter(filtroWord);
            miArchivo.setDialogTitle("Guardar archivo");
            miArchivo.setMultiSelectionEnabled(false);
            miArchivo.setAcceptAllFileFilterUsed(false);
            String userDir = System.getProperty("user.home");
            //preferencia de ubicacion
            if(SO.startsWith("Windows")){
                miArchivo.setCurrentDirectory(new File(userDir +"/Desktop"));
            }else{
                miArchivo.setCurrentDirectory(new File(userDir +"/Escritorio"));
            }
            //El nombre del Archivo
            String nomArchivo=clNota.getNombre();
            miArchivo.setSelectedFile(new File(nomArchivo));
            int aceptar=miArchivo.showSaveDialog(null);
            miArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(aceptar==JFileChooser.APPROVE_OPTION){
            File fileWord=miArchivo.getSelectedFile();
            String nombre=fileWord.getName();
            
            com.itextpdf.kernel.pdf.PdfWriter pdfWriter;
            
            if(nombre.indexOf('.')==-1){
                nombre+=".pdf";
                pdfWriter = new com.itextpdf.kernel.pdf.PdfWriter(fileWord.getParentFile()+"/"+nombre);
            }else{
                nombre+=".pdf";
                pdfWriter = new com.itextpdf.kernel.pdf.PdfWriter(fileWord.getParentFile()+"/"+nombre);
            }
            
//            // Creamos un documento pdf con iText
//            com.itextpdf.kernel.pdf.PdfWriter pdfWriter = new com.itextpdf.kernel.pdf.PdfWriter(dest+"./Ejemplo Excel.pdf");
            
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            com.itextpdf.layout.Document doc = new com.itextpdf.layout.Document(pdfDoc, PageSize.LETTER);
            //Arriba,Abajo,derecha,izqueda
            doc.setMargins(20, 20, 50, 50);
            
            PdfFont font1 = PdfFontFactory.createFont(FontConstants.COURIER);
//            PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
            
            com.itextpdf.layout.element.Paragraph parrafo1 = new com.itextpdf.layout.element.Paragraph("Notas Ensayo 1").setFont(font1);
            // Cambiamos el tamaño de fuente del parrafo 2 lo hacemos mas pequeño
            parrafo1.setFontSize(12f);
            
            // Creamos unas tablas
            float[] anchos = {300f, 50f,300f};
            Table tabla1 = new Table(anchos);
            Table tabla2 = new Table(anchos);
            
            // Agregamos contenido a las tablas
            tabla1.addCell("Nombre");
            tabla1.addCell("Retraso");
            tabla1.addCell("Comentario en rubrica");
            tabla1.addCell(clNota.getNombre());
            tabla1.addCell(Integer.toString(clNota.getRetraso()));
            tabla1.addCell("El trabajo fue entregado con "+Integer.toString(clNota.getRetraso())+" día de retraso!");
//            tabla1.addCell("\n");
//            tabla1.addCell("\n");
            com.itextpdf.layout.element.Paragraph parrafo2 = new com.itextpdf.layout.element.Paragraph("");
            // Cambiamos el tamaño de fuente del parrafo 2 lo hacemos mas pequeño
            parrafo2.setFontSize(20f);
            
            tabla2.addCell("Criterios de evaluación");
            tabla2.addCell(" ");
            tabla2.addCell(" ");
            tabla2.addCell("INTRODUCCIÓN (20%)");
            tabla2.addCell(" ");
            tabla2.addCell(" ");
            tabla2.addCell("Planteo del problema");
            tabla2.addCell(Integer.toString(clNota.getPlanteoPro()));
            if(clNota.getPlanteoPro()==1){
                tabla2.addCell("No se plantea problema");
            }
            if(clNota.getPlanteoPro()==2){
                tabla2.addCell("Se plantea el problema irrelevante para los objetivos del curso");
            }
            if(clNota.getPlanteoPro()==3){
                tabla2.addCell("Se plantea un problema relevante, aunque de manera confusa y desordenada");
            }
            if(clNota.getPlanteoPro()==4){
                tabla2.addCell("Se plantea un problema con claridad y concisión y sustentado en la literatura y lo discutido en clases");
            }
            if(clNota.getPlanteoPro()==5){
                tabla2.addCell("Se plantea un  problema original con claridad y concisión y bien sustentado en la literatura y lo discutido en clases");
            }
//            tabla2.addCell(" ");
            tabla2.addCell("Definición de términos relevantes");
            tabla2.addCell(Integer.toString(clNota.getDefinicion()));
            if(clNota.getDefinicion()==1){
                tabla2.addCell("No hay definiciones");
            }
            if(clNota.getDefinicion()==2){
                tabla2.addCell("Hay definiciones equivocadas, confusión conceptual, mala atribución a autores");
            }
            if(clNota.getDefinicion()==3){
                tabla2.addCell("Se muestra cierto conocimiento de definiciones, pero no se presentan correctamente");
            }
            if(clNota.getDefinicion()==4){
                tabla2.addCell("Se presentan definiciones correctas y atribuidas a sus autores");
            }
            if(clNota.getDefinicion()==5){
                tabla2.addCell("Se define correctamente de acuerdo a literatura y se compara con otras definiciones pertinentes");
            }
//            tabla2.addCell("");
            tabla2.addCell("Presentación de argumento central");
            tabla2.addCell(Integer.toString(clNota.getPresentacion()));
            if(clNota.getPresentacion()==1){
                tabla2.addCell("No hay argumento");
            }
            if(clNota.getPresentacion()==2){
                tabla2.addCell("Se presenta un argumento, pero basado en definiciones equivocadas");
            }
            if(clNota.getPresentacion()==3){
                tabla2.addCell("Se esboza un argumento, aunque confuso y desordenado");
            }
            if(clNota.getPresentacion()==4){
                tabla2.addCell("Se presenta clara y concisamente un argumento ya discutido en la literatura o lo discutido en clases");
            }
            if(clNota.getPresentacion()==5){
                tabla2.addCell("Se presenta un argumento original, claro, conciso y bien sustentado en la literatura o lo discutido en clases");
            }
//            tabla2.addCell("");
            tabla2.addCell("DESARROLLO (30%)");
            tabla2.addCell("");
            tabla2.addCell("");
            tabla2.addCell("Estructura y orden en la presentación");
            tabla2.addCell(Integer.toString(clNota.getEstructura()));
            if(clNota.getEstructura()==1){
                tabla2.addCell("Presentación desorganizada que dificulta la lectura");
            }
            if(clNota.getEstructura()==2){
                tabla2.addCell("Presentación impide detectar la presencia de una introducción, desarrollo y conclusión");
            }
            if(clNota.getEstructura()==3){
                tabla2.addCell("Hay introducción, desarrollo y conclusión, pero están confusos y desordenados");
            }
            if(clNota.getEstructura()==4){
                tabla2.addCell("Se reconocen introducción, desarrollo y conclusión y se organizan ordenadamente");
            }
            if(clNota.getEstructura()==5){
                tabla2.addCell("Se reconocen introducción, desarrollo y conclusión y se organizan de manera original");
            }
//            tabla2.addCell("");
            tabla2.addCell("Calidad de los análisis");
            tabla2.addCell(Integer.toString(clNota.getCalidad()));
            if(clNota.getCalidad()==1){
                tabla2.addCell("No hay análisis");
            }
            if(clNota.getCalidad()==2){
                tabla2.addCell("Hay análisis, pero basado en premisas o definiciones equivocadas");
            }
            if(clNota.getCalidad()==3){
                tabla2.addCell("La evidencia y/o la literatura se presentan desorganizadas");
            }
            if(clNota.getCalidad()==4){
                tabla2.addCell("Hay un correcto análisis de la literatura y/o la evidencia");
            }
            if(clNota.getCalidad()==5){
                tabla2.addCell("Hay análisis crítico y original de la literatura y/o la evidencia");
            }
//            tabla2.addCell("4");
            tabla2.addCell("Relación con argumento central");
            tabla2.addCell(Integer.toString(clNota.getRelacionCentral()));
            if(clNota.getRelacionCentral()==1){
                tabla2.addCell("No hay relación con el argumento inicial");
            }
            if(clNota.getRelacionCentral()==2){
                tabla2.addCell("Se esboza alguna relación con el argumento inicial , pero es confusa o forzada");
            }
            if(clNota.getRelacionCentral()==3){
                tabla2.addCell("Se presentan algunas relaciones con el argumento inicial, aunque con definiciones informales");
            }
            if(clNota.getRelacionCentral()==4){
                tabla2.addCell("El argumento inicial se relaciona con el análisis presentado");
            }
            if(clNota.getRelacionCentral()==5){
                tabla2.addCell("El argumento central y el análisis presentado se relacionan de manera original y creativa");
            }
//            tabla2.addCell("4");
            tabla2.addCell("CONCLUSIONES (20%)");
            tabla2.addCell("");
            tabla2.addCell("");
            tabla2.addCell("Relación con el argumento inicial");
            tabla2.addCell(Integer.toString(clNota.getRelacionInicial()));
            if(clNota.getRelacionInicial()==1){
                tabla2.addCell("No hay relación con el argumento inicial");
            }
            if(clNota.getRelacionInicial()==2){
                tabla2.addCell("Se esboza alguna relación con el argumento inicial , pero es confusa o forzada");
            }
            if(clNota.getRelacionInicial()==3){
                tabla2.addCell("Se presentan algunas relaciones con el argumento inicial, aunque con definiciones informales");
            }
            if(clNota.getRelacionInicial()==4){
                tabla2.addCell("El argumento inicial se relaciona con el análisis presentado");
            }
            if(clNota.getRelacionInicial()==5){
                tabla2.addCell("El argumento central y el análisis presentado se relacionan de manera original y creativa");
            }
//            tabla2.addCell("4");
            tabla2.addCell("Aportación personal");
            tabla2.addCell(Integer.toString(clNota.getAportacion()));
            if(clNota.getAportacion()==1){
                tabla2.addCell("No se presenta contribución");
            }
            if(clNota.getAportacion()==2){
                tabla2.addCell("Se presentan ideas ajenas como propias");
            }
            if(clNota.getAportacion()==3){
                tabla2.addCell("Hay contribución, pero confusa o basadas en definiciones  informales");
            }
            if(clNota.getAportacion()==4){
                tabla2.addCell("La contribución está clara y correctamente relacionada con la literatura o lo discutido en clases");
            }
            if(clNota.getAportacion()==5){
                tabla2.addCell("Hay contribuciones críticas y originales sustentadas en la literatura o lo discutido en clases");
            }
//            tabla2.addCell("4");
            tabla2.addCell("Relevancia para la discusión");
            tabla2.addCell(Integer.toString(clNota.getRelevancia()));
            if(clNota.getRelevancia()==1){
                tabla2.addCell("No se presentan conclusiones");
            }
            if(clNota.getRelevancia()==2){
                tabla2.addCell("Las conclusiones no se relacionan con el objetivo del curso");
            }
            if(clNota.getRelevancia()==3){
                tabla2.addCell("Se sugiere relevancia, pero sustentada en definiciones informales");
            }
            if(clNota.getRelevancia()==4){
                tabla2.addCell("Las conclusiones son relevantes para el curso.");
            }
            if(clNota.getRelevancia()==5){
                tabla2.addCell("Las conclusiones expanden el conocimiento presentado en el curso");
            }
//            tabla2.addCell("4");
            tabla2.addCell("BIBLIOGRAFÍA  (10%)");
            tabla2.addCell("");
            tabla2.addCell("");
            tabla2.addCell("Bibliografía");
            tabla2.addCell(Integer.toString(clNota.getBibliografia()));
            if(clNota.getBibliografia()==1){
                tabla2.addCell("No hay bibliografía");
            }
            if(clNota.getBibliografia()==2){
                tabla2.addCell("La bibliografía está desordenada, faltan datos del material consultado y cuando están presentes, no respetan el formato requerido");
            }
            if(clNota.getBibliografia()==3){
                tabla2.addCell("Se presentan algunos autores y material consultado y con errores de acuerdo al formato requerido");
            }
            if(clNota.getBibliografia()==4){
                tabla2.addCell("La mayoría de los autores están incluidos, pero algunos datos están ausentes o no se presentan de acuerdo al formato requerido");
            }
            if(clNota.getBibliografia()==5){
                tabla2.addCell("Toda la bibliografía se presenta por orden alfabético y de acuerdo al formato requerido");
            }
//            tabla2.addCell("2");
            tabla2.addCell("Citas en texto");
            tabla2.addCell(Integer.toString(clNota.getCitas()));
            if(clNota.getCitas()==1){
                tabla2.addCell("No hay citaciones");
            }
            if(clNota.getCitas()==2){
                tabla2.addCell("Se cita material equivocado. Se atribuyen autores o fechas de publicaciones errados ");
            }
            if(clNota.getCitas()==3){
                tabla2.addCell("Se cita a los autores y sus años de publicación en algunos casos, con errores de acuerdo con el formato requerido");
            }
            if(clNota.getCitas()==4){
                tabla2.addCell("Se cita correctamente a autores y años de publicación en la mayoría de los casos");
            }
            if(clNota.getCitas()==5){
                tabla2.addCell("En todo el texto se presentan las citas a los materiales consultados de acuerdo con el formato requerido");
            }
//            tabla2.addCell("2");
            tabla2.addCell("ASPECTOS FORMALES (20%)");
            tabla2.addCell("");
            tabla2.addCell("");
            tabla2.addCell("Ortografía");
            tabla2.addCell(Integer.toString(clNota.getOrtografia()));
            if(clNota.getOrtografia()==1){
                tabla2.addCell("52-70+ faltas de ortografía acentual, literal o puntual.");
            }
            if(clNota.getOrtografia()==2){
                tabla2.addCell("25-52 faltas de ortografía acentual, literal o puntual.");
            }
            if(clNota.getOrtografia()==3){
                tabla2.addCell("18-35 faltas de ortografía acentual, literal o puntual.");
            }
            if(clNota.getOrtografia()==4){
                tabla2.addCell("1-17 faltas de ortografía acentual, literal o puntual.");
            }
            if(clNota.getOrtografia()==5){
                tabla2.addCell("0 faltas de ortografía acentual, literal o puntual.");
            }
//            tabla2.addCell("5");
            tabla2.addCell("Extensión");
            tabla2.addCell(Integer.toString(clNota.getExtencion()));
            if(clNota.getExtencion()==1){
                tabla2.addCell("Supera o no alcanza la extensión requerida.");
            }
            if(clNota.getExtencion()==2){
                tabla2.addCell("");
            }
            if(clNota.getExtencion()==3){
                tabla2.addCell("");
            }
            if(clNota.getExtencion()==4){
                tabla2.addCell("");
            }
            if(clNota.getExtencion()==5){
                tabla2.addCell("Dentro de la extensión requerida.");
            }
//            tabla2.addCell("4");
            tabla2.addCell("Puntaje");
            tabla2.addCell(Double.toString(clNota.getPuntaje()));
            tabla2.addCell("");
            tabla2.addCell("Nota Parcial");
            tabla2.addCell(Double.toString(clNota.getNotaP()));
            tabla2.addCell("");
            tabla2.addCell("Descuento por retraso");
            tabla2.addCell(Integer.toString(clNota.getDescuenhtoRetraso()));
            tabla2.addCell("");
            tabla2.addCell("Nota Ensayo");
            tabla2.addCell(Double.toString(clNota.getNotaE()));
            tabla2.addCell(" ");
            
            com.itextpdf.layout.element.Paragraph parrafo3 = new com.itextpdf.layout.element.Paragraph("Comentario:"+clNota.getComentario());
            // Cambiamos el tamaño de fuente del parrafo 2 lo hacemos mas pequeño
            parrafo3.setFontSize(12f);
            
            
            // Cambiamos el tamaño de fuente de la tabla 1, lo hacemos mas pequeño
            tabla1.setFontSize(10f);
            // Cambiamos el tamaño de fiente de la tabla 2, lo hacemos mas grande
            tabla2.setFontSize(10f);
            
            doc.add(parrafo1);
            
            doc.add(tabla1);
            doc.add(parrafo2);
            doc.add(tabla2);
            doc.add(parrafo3);
//            doc.add(tabla3);
            
            doc.close();
        }
            
        } catch (IOException ex) {
            Log.log("Error en Clase ImprimirPDF: "+ex.getMessage());
            Logger.getLogger(ImprimirPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
