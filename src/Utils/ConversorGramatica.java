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

    private final Gramatica gramatica = new Gramatica();
    private ArrayList<String> noTerminales;
    private ArrayList<String> terminales;

    public Gramatica convertir(String producciones) {
        ArrayList<String> p = new ArrayList<>();
        //producciones = producciones.replaceAll("[^w]", ""); //[^w]: todo menos letras
        String[] lineas = producciones.split("\n");
        for (int i = 0; i < lineas.length; i++) {
            p.add(lineas[i].replaceAll("[^a-zA-Z_^|]", "")); //no elimina letras a-z A-Z y |, el resto si            
        }
        gramatica.setProducciones(p);
        crearSimbolos(p);
        return gramatica;
    }

    public void crearSimbolos(ArrayList<String> produccion) { // cambiar nombre
        noTerminales = new ArrayList<>();
        terminales = new ArrayList<>();

        for (int i = 0; i < produccion.size(); i++) {
            String s = produccion.get(i);
            for (int j = 0; j < s.length(); j++) {
                Character c = s.charAt(j);
                String letra = c.toString();
                boolean esMinuscula = letra.equals(letra.toLowerCase());
                if (!letra.equals(Gramatica.FIN_DE_SECUENCIA)) {
                    if (esMinuscula && !terminales.contains(letra)) {
                        terminales.add(letra);
                    } else if (!esMinuscula && !noTerminales.contains(letra)) {
                        noTerminales.add(letra);
                    }
                }

            }
        }
        gramatica.setTerminales(terminales);
        gramatica.setNoTerminales(noTerminales);

    }
    
    

}
