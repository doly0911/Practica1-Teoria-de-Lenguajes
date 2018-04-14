/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


/**
 *
 * @author Usuario externo
 */
public class Estado {
    private String nombre;
    private boolean inicial;
    private String [][] transicionesEstado;
    private String [][] matrizT;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isInicial() {
        return inicial;
    }

    public void setInicial(boolean inicial) {
        this.inicial = inicial;
    }

    public String [][] getTransicionesEstado() {
        return transicionesEstado;
    }

    public void setTransicionesEstado(String [][] transicionesEstado) {
        this.transicionesEstado = transicionesEstado;
    }

    public String[][] getMatrizT() {
        return matrizT;
    }

    public void setMatrizT(String[][] matrizT) {
        this.matrizT = matrizT;
    }
    
    
    
}
