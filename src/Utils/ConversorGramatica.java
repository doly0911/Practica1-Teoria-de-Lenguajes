/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.Gramatica;
import java.util.ArrayList;

/**
 *
 * @author pao_c
 */
public class ConversorGramatica {
    
    public Gramatica convertir(String producciones) {
        ArrayList<String> p = new ArrayList<>();
        Gramatica gramatica = new Gramatica();
        //producciones = producciones.replaceAll("[^w]", ""); //[^w]: todo menos letras
        String [] lineas = producciones.split("\n");
        for(int i=0;i<lineas.length;i++){
            p.add(lineas[i].replaceAll("[^a-zA-Z_^|]", ""));
        }
        gramatica.setProducciones(p);
        return gramatica;
    }
    
    
}
