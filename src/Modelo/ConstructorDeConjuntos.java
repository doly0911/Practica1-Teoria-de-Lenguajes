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
                boolean esMinuscula = noTerminal.equals(noTerminal.toLowerCase());
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
}

