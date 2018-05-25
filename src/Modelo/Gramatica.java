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
    private ArrayList<String> noTerminalesAnulables;
    private ArrayList<Integer> produccionesAnulables;
    public static final String FIN_DE_SECUENCIA = "|";

    public ArrayList<String> getProducciones() {
        return producciones;
    }

    public void setProducciones(ArrayList<String> producciones) {
        this.producciones = producciones;
    }

    public ArrayList<String> getNoTerminalesAnulables() {
        return noTerminalesAnulables;
    }

    public void setNoTerminalesAnulables(ArrayList<String> noTerminalesAnulables) {
        this.noTerminalesAnulables = noTerminalesAnulables;
    }

    public ArrayList<Integer> getProduccionesAnulables() {
        return produccionesAnulables;
    }

    public void setProduccionesAnulables(ArrayList<Integer> produccionesAnulables) {
        this.produccionesAnulables = produccionesAnulables;
    }

   
}
