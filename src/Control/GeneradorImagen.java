/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class GeneradorImagen {

    public GeneradorImagen() {
    }
    
    
    public void createImage(JTable table) {

        int w = table.getWidth();
        int h = table.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        table.paint(g);
        saveImage(bi);
    }
    
    public void saveImage (BufferedImage image){
        try {
            ImageIO.write(image, "jpg", new File("Matriz.jpg"));
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }
    }
    
}
