/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author pao_c
 */
public class GeneradorPDF {
    private Font fuenteBold = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
    private Font fuenteNormal = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
    private Font fuenteItalic = new Font(Font.FontFamily.COURIER, 8, Font.ITALIC);
    
    public void generarPDF(String header, String info, String rutaImagen, String salida){
        try {
            Document document = new Document(PageSize.A7, 36, 36, 10, 10);
            PdfWriter.getInstance(document, new FileOutputStream(salida));
            document.open();
            document.add(getHeader(header));
            Image imagen = Image.getInstance(rutaImagen);
            imagen.scaleAbsolute(100, 100);
            imagen.setAlignment(Element.ALIGN_CENTER);
            document.add(imagen);
            document.add(getInfo(info));
            document.close();
        } catch (Exception e) {
        }
                
    }
    
    private Paragraph getHeader(String texto){
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteBold);
        p.add(c);
        return p;
    }
    
    private Paragraph getInfo(String texto){
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    
}
