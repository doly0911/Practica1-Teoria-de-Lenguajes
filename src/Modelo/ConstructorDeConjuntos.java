/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Usuario externo
 */
public class ConstructorDeConjuntos {

    private final ArrayList<String> noTerminalesAnulables;
    private final ArrayList<Integer> produccionesAnulables;
    private final Gramatica gramatica;
    private final int dimensionMatriz;
    private final ArrayList<String> indiceMatriz;
    private int[][] comienzaDirectamenteCon;
    private int[][] comienzaCon;
    
    public ConstructorDeConjuntos(Gramatica gramatica) {
        this.noTerminalesAnulables = new ArrayList<>();
        this.produccionesAnulables = new ArrayList<>();
        this.gramatica = gramatica;
        indiceMatriz = new ArrayList<>();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        ArrayList<String> terminales = gramatica.getTerminales();
        for (String noTerminal : noTerminales) {
            indiceMatriz.add(noTerminal);
        }
        for (String terminal : terminales) {
            indiceMatriz.add(terminal);
        }
        dimensionMatriz = indiceMatriz.size();
        this.comienzaDirectamenteCon = new int[dimensionMatriz][dimensionMatriz];
    }

    public void construirAnulables() {
        String noTerminal;
        Character s;
        ArrayList<String> prod = gramatica.getProducciones();
        boolean esAnulable = true;
        for (int i = 0; i < prod.size(); i++) {
            String produccion = prod.get(i);
            s = produccion.charAt(1);
            noTerminal = s.toString(); //Lo ocnvierto a String
            if (noTerminal.equals(Gramatica.FIN_DE_SECUENCIA)) { //busco el fin se secuencia entre el primer valor de cada produccion
                Character n = produccion.charAt(0); //obtengo el primer caracter
                noTerminal = n.toString();
                noTerminalesAnulables.add(noTerminal);
                produccionesAnulables.add(i);

            }
            gramatica.setNoTerminalesAnulables(noTerminalesAnulables);
            gramatica.setProduccionesAnulables(produccionesAnulables);
        }

        for (int i = 0; i < prod.size(); i++) {
            String produccion = prod.get(i); // obtengo el String de cada Produccion
            for (int j = 1; j < produccion.length(); j++) { //Recorro cada String
                s = produccion.charAt(j);
                noTerminal = s.toString();
                if (!noTerminalesAnulables.contains(noTerminal)) {
                    esAnulable = false;
                    break;
                }
            }
            if (esAnulable) {
                s = produccion.charAt(0);
                noTerminalesAnulables.add(s.toString());
                produccionesAnulables.add(i);
            }
            esAnulable = true;

        }

    }

    public void crearMatrizComienzaDirectamenteCon() {
        ArrayList<String> producciones = gramatica.getProducciones();
        int fila = 0, columna = 0;
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            for (int j = 0; j < produccion.length(); j++) {
                Character c = produccion.charAt(j);
                String simbolo = c.toString();
                if (j == 0) {
                    fila = indiceMatriz.indexOf(simbolo);
                } else {
                    if (indiceMatriz.contains(simbolo)) {
                        columna = indiceMatriz.indexOf(simbolo);
                        comienzaDirectamenteCon[fila][columna] = 1;
                        if (!noTerminalesAnulables.contains(simbolo)) {
                            break;
                        }
                    }

                }
            }
        }
    }

    
    public int[][] CalcularCierreTransitivo(int matriz[][]){
        for(int k=0; k<dimensionMatriz; k++){
            for(int i=0; i<dimensionMatriz; i++){
                if(matriz[i][k]==1){
                    for(int j=0; j<dimensionMatriz; j++){
                        if(matriz[k][j]==1){
                            matriz[i][j]=1;
                        }
                    }
                }
            }
        }
        return matriz;
    }
    
    public void crearPrimerosNoTerminales(){
        ArrayList<ArrayList<String>> primeros = new ArrayList<>();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        for (int i = 0; i < noTerminales.size(); i++) {
            ArrayList<String>terminales = new ArrayList<>();
            for (int j = noTerminales.size(); j < dimensionMatriz; j++) {
                if(comienzaDirectamenteCon[i][j]==1){
                    String terminal = indiceMatriz.get(j); //obtenemos el terminal de la columna
                    terminales.add(terminal);
                }
            }
            primeros.add(terminales);
        }
        gramatica.setPrimerosNoTerminales(primeros);
    }
    
    public void crearPrimerosProducciones(){
        ArrayList<ArrayList<String>> primeros = gramatica.getPrimerosNoTerminales(); 
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> terminales = gramatica.getTerminales();
        ArrayList<String> anulables = gramatica.getNoTerminalesAnulables();
       
        for (int i = 0; i < producciones.size(); i++) {
            String prod = producciones.get(i);
            ArrayList<String> primerosProd = new ArrayList<>();
            for (int j = 1; j < prod.length(); j++) {
                Character c = prod.charAt(j);
                String s = c.toString();
                if (terminales.contains(s)){
                    primerosProd.add(s);
                    break;
                }
                if(!anulables.contains(s)){
                    int indice = indiceMatriz.indexOf(s);
                    ArrayList<String> primerosNoTerminal = primeros.get(indice);
                    
                }
                
            }
            
            
        }
         
    
    }
    
    public int[][] CalcularProducto(int matrizA[][], int matrizB[][]){
        int matrizC[][] = new int[dimensionMatriz][dimensionMatriz];
        for(int k=0; k<dimensionMatriz; k++){
            for(int i=0; i<dimensionMatriz; i++){
                if(matrizA[i][k]==1){
                    for(int j=0; j<dimensionMatriz; j++){
                        if(matrizB[k][j] == 1){
                            matrizC[i][j]=1;
                        }
                    }
                }
            }
        }
        return matrizC;
    }

    public int[][] getComienzaDirectamenteCon() {
        return comienzaDirectamenteCon;
    }

    public void setComienzaDirectamenteCon(int[][] comienzaDirectamenteCon) {
        this.comienzaDirectamenteCon = comienzaDirectamenteCon;
    }
    
    

}

