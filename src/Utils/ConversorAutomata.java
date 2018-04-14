/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.AutomataPila;
import Modelo.Estado;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Usuario externo
 */
public class ConversorAutomata {

    private static final String SIMBOLOS_ENTRADAS = "Simbolos de entrada";
    private static final String SIMBOLOS_PILA = "simbolos en la pila";  
    private static final String CONFIGURACION = "configuracion inicial";
    private static final String TRANSICION = "transicion";
    private int esLineaDeEstado;
    private int esLineaDeTransicion;
    private ArrayList<String> lineasEstado;
    private ArrayList<String> lineasTransicion;

    public AutomataPila convertir(File rutaArchivo) throws FileNotFoundException, IOException {
//      
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea = br.readLine();
        AutomataPila automataPila = new AutomataPila();
        esLineaDeEstado = 0;
        lineasEstado = new ArrayList<>();
        esLineaDeTransicion = 0;
        lineasTransicion = new ArrayList<>();
        ArrayList<String> simbolos;
        while (linea != null) {
            linea = linea.trim();
            String[] partes = linea.split(":");    //separo mi linea de texto donde encuentre :
            if (partes.length != 1) {
                simbolos = crearSimbolos(partes, SIMBOLOS_ENTRADAS);
                if (simbolos != null) {
                    automataPila.setSimbolosEntrada(simbolos);
                }
                simbolos = crearSimbolos(partes, SIMBOLOS_PILA);
                if (simbolos != null) {
                    automataPila.setSimbolosPila(simbolos);
                }
                simbolos = crearSimbolos(partes, CONFIGURACION);
                if (simbolos != null) {
                    automataPila.setConfiguracionInicial(simbolos);
                }
            }
            crearLineaEstado(linea, partes.length);
            if (esLineaDeEstado == 2) {
                automataPila.setEstados(crearEstados(automataPila.getSimbolosEntrada(), automataPila.getSimbolosPila()));
            }
            crearLineaTransicion(linea, partes.length);
            if (esLineaDeTransicion == 2) {
                automataPila.setTransiciones(crearTransiciones());
            }
            linea = br.readLine();   
        }
        br.close();
        return automataPila;
    }

    public ArrayList<String> crearSimbolos(String[] partes, String identificador) {

        String nombreVariable = partes[0];
        nombreVariable = nombreVariable.replace("\"", "");
        if (!nombreVariable.equalsIgnoreCase(identificador)) {
            return null;
        }
        ArrayList<String> simbolos = new ArrayList<>();
        String valoresStr = partes[1];
        valoresStr = valoresStr.replaceAll("\\[", "").replaceAll("\\]", "").trim();//eliminar corchetes y espacios en blanco
        String[] valores = valoresStr.split(",");
        for (int i = 0; i < valores.length; i++) {
            valores[i] = valores[i].replace("\"", "");
            valores[i] = valores[i].trim();
            simbolos.add(valores[i]);
            
        }
        return simbolos;
    }

    public void crearLineaEstado(String linea, int longitudPartes) {
        if (linea.contains("Estados")) {
            esLineaDeEstado = 1;
            return;
        }
        if (esLineaDeEstado == 1 && longitudPartes > 1) {
            lineasEstado.add(linea);
        }
        if (linea.equals("],")) {
            esLineaDeEstado = 2;
        }
    }

    public void crearLineaTransicion(String linea, int longitudPartes) {
        if (linea.contains("Transiciones")) {
            esLineaDeTransicion = 1;
            return;
        }
        if (esLineaDeTransicion == 1 && longitudPartes > 1) {
            lineasTransicion.add(linea);
        }
        if (linea.equals("}")) {
            esLineaDeTransicion = 2;
        }
    }

    public HashMap<String, ArrayList<String>> crearTransiciones() {
        HashMap<String, ArrayList<String>> transiciones = new HashMap<>();
        if (esLineaDeTransicion == 2) {
            for (int i = 0; i < lineasTransicion.size(); i++) {
                String[] partes = lineasTransicion.get(i).split(":");
                String key = partes[0].replace("\"", "");
                partes[0] = TRANSICION;
                ArrayList<String> valor = crearSimbolos(partes, TRANSICION);
                transiciones.put(key, valor);
            }
            return transiciones;

        }
        return null;
    }

    public ArrayList<Estado> crearEstados(ArrayList<String> SimEntrada, ArrayList<String> SimPila) {
        int numSimEntrada = SimEntrada.size();
        int numSimPila = SimPila.size();
        int n = lineasEstado.size();
        ArrayList<Estado> estados = new ArrayList<>();
        if (esLineaDeEstado == 2) {
            Estado estado = new Estado();
            for (int i = 0; i < n; i++) {
                int modulo = i % 3;
                String[] partes = lineasEstado.get(i).split(":");
                partes[1] = partes[1].replace("\"", "");
                if (partes[1].endsWith(",")) {
                    partes[1] = partes[1].substring(0, partes[1].length() - 1);
                }
                switch (modulo) {
                    case 0:
                        estado.setNombre(partes[1]);
                        break;
                    case 1:
                        estado.setInicial(Boolean.parseBoolean(partes[1]));
                        break;
                    case 2:
                        estado.setTransicionesEstado(crearTransicionesEstado(partes[1], numSimEntrada, numSimPila));
                        estado.setMatrizT(crearMatrizT(SimEntrada, SimPila, estado.getTransicionesEstado()));
                        estados.add(estado);
                        estado = new Estado();
                        break;
                }
            }
            esLineaDeEstado = 0;
            return estados;
        }
        return null;
    }

    public String[][] crearTransicionesEstado(String linea, int simbolosEntrada, int simbolosPilas) {

        String valoresStr = linea;
        String[][] matrizEstado = new String[simbolosPilas][simbolosEntrada];
        valoresStr = valoresStr.replaceAll("\\[", "").replaceAll("\\]", "");//eliminar corchetes
        String[] valores = valoresStr.split(",");
        int fila = 0;
        int columna = 0;
        for (int i = 0; i < valores.length; i++) {
            if (valores[i].contains(";")) {
                String[] separadorFilas = valores[i].split(";");
                String ultimoValorFila = separadorFilas[0];
                matrizEstado[fila][columna] = ultimoValorFila;
                fila++;
                columna = 0;
                String primerValorColumna = separadorFilas[1];
                matrizEstado[fila][columna] = primerValorColumna;
                columna++;
            } else {
                valores[i] = valores[i].replace("\"", "");
                matrizEstado[fila][columna] = valores[i];
                columna++;
            }
        }
        return matrizEstado;
    }
    
    public String[][] crearMatrizT(ArrayList<String> SimEntrada, ArrayList<String> SimPila, String [][] transicionesEstado){
        String[][] matrizT = new String[SimPila.size()+1][SimEntrada.size()+1];
        int i = 1;
        for(String s : SimEntrada){
            matrizT[0][i] = s;
            i++;
        }
        i = 1;
        for(String s : SimPila){
            matrizT[i][0] = s;
            i++;
        }
        
        for(i = 1; i <= SimPila.size(); i++){
            for(int j = 1; j <= SimEntrada.size(); j++){
                matrizT[i][j] = transicionesEstado[i-1][j-1];
            }
        }
        return matrizT;
    }

}
