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
    private ArrayList<String> noTerminales;
    private ArrayList<String> terminales;
    private ArrayList<String> producciones;
    private ArrayList<String> noTerminalesAnulables;
    private ArrayList<Integer> produccionesAnulables;
    private ArrayList<ArrayList<String>> primerosNoTerminales;
    private ArrayList<ArrayList<String>> primerosProducciones;
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

    public ArrayList<String> getNoTerminales() {
        return noTerminales;
    }

    public void setNoTerminales(ArrayList<String> noTerminales) {
        this.noTerminales = noTerminales;
    }

    public ArrayList<String> getTerminales() {
        return terminales;
    }

    public void setTerminales(ArrayList<String> terminales) {
        this.terminales = terminales;
    }

    public ArrayList<ArrayList<String>> getPrimerosNoTerminales() {
        return primerosNoTerminales;
    }

    public void setPrimerosNoTerminales(ArrayList<ArrayList<String>> primerosNoTerminales) {
        this.primerosNoTerminales = primerosNoTerminales;
    }

    public ArrayList<ArrayList<String>> getPrimerosProducciones() {
        return primerosProducciones;
    }

    public void setPrimerosProducciones(ArrayList<ArrayList<String>> primerosProducciones) {
        this.primerosProducciones = primerosProducciones;
    }
    
    
    
   
}
