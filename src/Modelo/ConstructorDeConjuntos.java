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
    private int[][] comienzaCon;
    private int[][] seguidoDirectamente;
    private int[][] esFinDe;

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
        this.comienzaCon = new int[dimensionMatriz][dimensionMatriz];
        this.seguidoDirectamente = new int[dimensionMatriz][dimensionMatriz];
        this.esFinDe = new int[dimensionMatriz][dimensionMatriz];
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
                } else if (indiceMatriz.contains(simbolo)) {
                    columna = indiceMatriz.indexOf(simbolo);
                    comienzaCon[fila][columna] = 1;
                    if (!noTerminalesAnulables.contains(simbolo)) {
                        break;
                    }
                }
            }
        }
    }

    public int[][] CalcularCierreTransitivo(int matriz[][]) {
        for (int k = 0; k < dimensionMatriz; k++) {
            for (int i = 0; i < dimensionMatriz; i++) {
                if (matriz[i][k] == 1) {
                    for (int j = 0; j < dimensionMatriz; j++) {
                        if (matriz[k][j] == 1) {
                            matriz[i][j] = 1;
                        }
                    }
                }
            }
        }
        return matriz;
    }

    public void crearPrimerosNoTerminales() {
        ArrayList<ArrayList<String>> primeros = new ArrayList<>();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        for (int i = 0; i < noTerminales.size(); i++) {
            ArrayList<String> terminales = new ArrayList<>();
            for (int j = noTerminales.size(); j < dimensionMatriz; j++) {
                if (comienzaCon[i][j] == 1) {
                    String terminal = indiceMatriz.get(j); //obtenemos el terminal de la columna
                    terminales.add(terminal);
                }
            }
            primeros.add(terminales);
        }
        gramatica.setPrimerosNoTerminales(primeros);
    }

    public void crearPrimerosProducciones() {
        ArrayList<ArrayList<String>> primeros = gramatica.getPrimerosNoTerminales();
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> terminales = gramatica.getTerminales();
        ArrayList<String> anulables = gramatica.getNoTerminalesAnulables();
        ArrayList<ArrayList<String>> primerosProducciones = new ArrayList<>();

        for (int i = 0; i < producciones.size(); i++) {
            String prod = producciones.get(i);
            ArrayList<String> primerosProd = new ArrayList<>();
            for (int j = 1; j < prod.length(); j++) {
                Character c = prod.charAt(j);
                String s = c.toString();
                if (terminales.contains(s)) {
                    primerosProd.add(s);
                    break;
                }
                if (!s.equals(Gramatica.FIN_DE_SECUENCIA)) {
                    int indice = indiceMatriz.indexOf(s);
                    ArrayList<String> primerosNoTerminal = primeros.get(indice);
                    for (int k = 0; k < primerosNoTerminal.size(); k++) {
                        String letra = primerosNoTerminal.get(k);
                        if (!primerosProd.contains(letra)) {
                            primerosProd.add(letra);
                        }
                    }
                    if (!anulables.contains(s)) {
                        break;
                    }
                }

            }
            primerosProducciones.add(primerosProd);
            gramatica.setPrimerosProducciones(primerosProducciones);

        }

    }

    public void crearEsSeguidoDirectamentePor() {
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> anulables = gramatica.getNoTerminalesAnulables();
        int fila = 0, columna = 0;
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            for (int j = 1; j < produccion.length() - 1; j++) {
                Character c = produccion.charAt(j);
                String simbolo = c.toString();
                if (simbolo.equals(Gramatica.FIN_DE_SECUENCIA)) {
                    break;
                }
                fila = indiceMatriz.indexOf(simbolo);
                for (int k = j + 1; k < produccion.length(); k++) {
                    Character s = produccion.charAt(k);
                    String siguiente = s.toString();
                    if (!siguiente.equals(Gramatica.FIN_DE_SECUENCIA)) {
                        columna = indiceMatriz.indexOf(siguiente);
                        seguidoDirectamente[fila][columna] = 1;
                        if (!anulables.contains(siguiente)) {
                            break;
                        }
                    }

                }

            }
        }

    }

    public void crearEsFinDirectoDe() {
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> anulables = gramatica.getNoTerminalesAnulables();
        int fila = 0, columna = 0;
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            Character n = produccion.charAt(0);
            String ladoIzq = n.toString();
            columna = indiceMatriz.indexOf(ladoIzq);
            for (int j = produccion.length() - 1; j > 0; j--) {
                Character c = produccion.charAt(j);
                String simbolo = c.toString();
                if (!simbolo.equals(Gramatica.FIN_DE_SECUENCIA)) {
                    fila = indiceMatriz.indexOf(simbolo);
                    esFinDe[fila][columna] = 1;
                    if (!anulables.contains(simbolo)) {
                        break;
                    }
                }

            }
        }

    }

    public int[][] CalcularProducto(int matrizA[][], int matrizB[][]) {
        int matrizC[][] = new int[dimensionMatriz][dimensionMatriz];
        for (int k = 0; k < dimensionMatriz; k++) {
            for (int i = 0; i < dimensionMatriz; i++) {
                if (matrizA[i][k] == 1) {
                    for (int j = 0; j < dimensionMatriz; j++) {
                        if (matrizB[k][j] == 1) {
                            matrizC[i][j] = 1;
                        }
                    }
                }
            }
        }
        return matrizC;
    }

    public int[][] getComienzaCon() {
        return comienzaCon;
    }

    public void setComienzaCon(int[][] comienzaCon) {
        this.comienzaCon = comienzaCon;
    }

    public int[][] getSeguidoDirectamente() {
        return seguidoDirectamente;
    }

    public void setSeguidoDirectamente(int[][] seguidoDirectamente) {
        this.seguidoDirectamente = seguidoDirectamente;
    }

    public int[][] getEsFinDe() {
        return esFinDe;
    }

    public void setEsFinDe(int[][] esFinDe) {
        this.esFinDe = esFinDe;
    }
    
    

}
