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
//            Log.log(ex.getMessage());
           
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Hubo un error"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//            log.info(ex.getMessage());
//            Log.log(ex.getMessage());
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
//                    Log.log(ex.getMessage());
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
//            Log.log(ex.getMessage());
        }   catch (FileNotFoundException ex) {
            Logger.getLogger(ImprimirPDF.class.getName()).log(Level.SEVERE, null, ex);
//            log.info(ex.getMessage());
//            Log.log(ex.getMessage());
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
//                    Log.log(ex.getMessage());
                }
            }

    }
    }
    
}
