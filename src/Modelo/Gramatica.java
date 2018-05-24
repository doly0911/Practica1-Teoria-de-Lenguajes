/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author pao_c
 */
public class Gramatica {
    private ArrayList<String> producciones;
    private String[] noTerminalesAnulables;
    private int[] produccionesAnulables;

    public ArrayList<String> getProducciones() {
        return producciones;
    }

    public void setProducciones(ArrayList<String> producciones) {
        this.producciones = producciones;
    }
    
    
}
