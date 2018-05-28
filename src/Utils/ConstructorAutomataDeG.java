/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.AutomataPila;
import Modelo.Estado;
import Modelo.Gramatica;
import Modelo.ValidadorGramatica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author pao_c
 */
public class ConstructorAutomataDeG {

    AutomataPila automata;
    Gramatica gramatica;
    private static final String INSTRUCCION_DESAPILAR = "desapile";
    private static final String INSTRUCCION_RETENGA = "retenga";
    private static final String INSTRUCCION_AVANCE = "avance";
    private static final String INSTRUCCION_ACEPTE = "Acepte";
    private static final String INSTRUCCION_RECHACE = "rechace";
    private static final String INSTRUCCION_REPLACE = "replace(";
    private static final String INSTRUCCION_PERMANEZCA = "permanezca";
    private static final String ESTADO_INICIAL = "SO";

    public AutomataPila construirAutomataDeG(Gramatica gramatica) {
        this.gramatica = gramatica;
        automata = new AutomataPila();

        automata.setSimbolosEntrada(crearSimbolosEntrada());
        automata.setSimbolosPila(crearSimbolosPila());
        automata.setConfiguracionInicial(crearConfInicial());
        automata.setTransiciones(crearTransiciones());
        crearTransicionesEstado();
        //automata.setPila(pila);
        return automata;
    }

    public ArrayList<String> crearSimbolosEntrada() {
        ArrayList<String> simbolosEntrada;
        simbolosEntrada = gramatica.getTerminales();
        Collections.sort(simbolosEntrada);
        simbolosEntrada.add(Gramatica.FIN_DE_SECUENCIA);
        return simbolosEntrada;
    }

    public ArrayList<String> crearSimbolosPila() {
        ArrayList<String> simbolosPila;
        Character c;
        simbolosPila = gramatica.getNoTerminales();

        for (String p : gramatica.getProducciones()) {
            for (int i = 2; i < p.length(); i++) {
                c = p.charAt(i);
                if (Character.isLowerCase(c) && (!simbolosPila.contains(c.toString()))) {
                    simbolosPila.add(c.toString());
                }
            }
        }
        Collections.sort(simbolosPila);
        simbolosPila.add(AutomataPila.PILA_VACIA);
        return simbolosPila;
    }

    public ArrayList<String> crearConfInicial() {
        ArrayList<String> confInicial;
        confInicial = new ArrayList<>();

        confInicial.add(AutomataPila.PILA_VACIA);
        confInicial.add(gramatica.getNoTerminales().get(0));
        Collections.sort(confInicial);
        return confInicial;
    }

    public HashMap<String, ArrayList<String>> crearTransiciones() {
        HashMap<String, ArrayList<String>> transiciones;
        transiciones = new HashMap<>();
        ArrayList<String> instrucciones;

        instrucciones = new ArrayList<>();
        instrucciones.add(INSTRUCCION_DESAPILAR);
        instrucciones.add(INSTRUCCION_PERMANEZCA);
        instrucciones.add(INSTRUCCION_AVANCE);
        transiciones.put("#0", instrucciones);

        instrucciones = new ArrayList<>();
        instrucciones.add(INSTRUCCION_ACEPTE);
        transiciones.put("#A", instrucciones);

        instrucciones = new ArrayList<>();
        instrucciones.add(INSTRUCCION_RECHACE);
        transiciones.put("#R", instrucciones);

        ValidadorGramatica validador = new ValidadorGramatica(gramatica);
        int tipoDeG = validador.validar();
        switch (tipoDeG) {
            case 2: //gramatica Q
                instrucciones = new ArrayList<>();
                instrucciones.add(INSTRUCCION_DESAPILAR);
                instrucciones.add(INSTRUCCION_PERMANEZCA);
                instrucciones.add(INSTRUCCION_RETENGA);
                transiciones.put("#1", instrucciones);
                break;
            case 3: //gramatica Q
                instrucciones = new ArrayList<>();
                instrucciones.add(INSTRUCCION_DESAPILAR);
                instrucciones.add(INSTRUCCION_PERMANEZCA);
                instrucciones.add(INSTRUCCION_RETENGA);
                transiciones.put("#1", instrucciones);
                break;

        }

        return transiciones;
    }

    public void crearTransicionesEstado() {
        Character c;
        Estado estado = new Estado();
        estado.setNombre(ESTADO_INICIAL);
        estado.setInicial(true);
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> simbolosPila = automata.getSimbolosPila();
        ArrayList<String> simbolosEntrada = automata.getSimbolosEntrada();
        ArrayList<String> terminales = gramatica.getTerminales();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        ArrayList< ArrayList<String>> seleccionProd = gramatica.getSeleccionProducciones();
        HashMap<String, ArrayList<String>> transiciones = automata.getTransiciones();
        int fila = simbolosPila.size();
        int columna = simbolosEntrada.size();
        int numeroTransiciones = transiciones.size() - 2;
        String[][] transicionesEstado = new String[fila][columna];
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            c = produccion.charAt(0);
            String ladoIzquierdo = c.toString();
            c = produccion.charAt(1);
            String primerSimbolo = c.toString();
            int filaTransicion = simbolosPila.indexOf(ladoIzquierdo);
            int columnaTransicion = simbolosEntrada.indexOf(primerSimbolo);
            if (primerSimbolo.equals(Gramatica.VACIO)) {
                ArrayList<String> seleccion = seleccionProd.get(i);
                for (String simbolo : seleccion) {
                    columnaTransicion = simbolosEntrada.indexOf(simbolo);
                    transicionesEstado[filaTransicion][columnaTransicion] = "#1";
                }
            }
            if (produccion.length() == 2 && terminales.contains(primerSimbolo)) {
                ArrayList<String> instrucciones = new ArrayList<>();
                instrucciones.add(INSTRUCCION_DESAPILAR);
                instrucciones.add(INSTRUCCION_PERMANEZCA);
                instrucciones.add(INSTRUCCION_AVANCE);
                transiciones.put("#" + numeroTransiciones, instrucciones);
                transicionesEstado[filaTransicion][columnaTransicion] = "#" + numeroTransiciones;
                numeroTransiciones++;
            } else if (terminales.contains(primerSimbolo)) {
                transicionesEstado[filaTransicion][columnaTransicion] = "#" + numeroTransiciones;
                crearTransicionReplace(numeroTransiciones, produccion.substring(2), false);
                numeroTransiciones++;
            }
            if (noTerminales.contains(primerSimbolo)) {
                crearTransicionReplace(numeroTransiciones, produccion.substring(1), true);
                ArrayList<String> seleccion = seleccionProd.get(i);
                for (String simbolo : seleccion) {
                    columnaTransicion = simbolosEntrada.indexOf(simbolo);
                    transicionesEstado[filaTransicion][columnaTransicion] = "#" + numeroTransiciones;
                }
                numeroTransiciones++;
            }
        }

        for (int i = 0; i < fila; i++) {
            String simboloPila = simbolosPila.get(i);
            if (simbolosEntrada.contains(simboloPila)) {
                int indice = simbolosEntrada.indexOf(simboloPila);
                transicionesEstado[i][indice] = "#0";
            }
            for (int j = 0; j < columna; j++) {
                String valor = transicionesEstado[i][j];
                if (valor == null || valor.isEmpty()) {
                    transicionesEstado[i][j] = "#R";
                }
            }
        }
        int indicePilaVacia = simbolosPila.indexOf(AutomataPila.PILA_VACIA);
        int indiceFinSecuencia = simbolosEntrada.indexOf(Gramatica.FIN_DE_SECUENCIA);
        transicionesEstado[indicePilaVacia][indiceFinSecuencia] = "#A";

        estado.setTransicionesEstado(transicionesEstado);
        ConversorAutomata ca = new ConversorAutomata();
        estado.setMatrizT(ca.crearMatrizT(automata.getSimbolosEntrada(), automata.getSimbolosPila(), estado.getTransicionesEstado()));
        ArrayList<Estado> estados = new ArrayList<>();
        estados.add(estado);
        automata.setEstados(estados);
    }

    private void crearTransicionReplace(int numeroTransiciones, String hilera, boolean esRetenga) {
        HashMap<String, ArrayList<String>> transiciones = automata.getTransiciones();
        String llave = "#" + numeroTransiciones;
        Character c;
        ArrayList<String> instrucciones = new ArrayList<>();
        String hileraInvertida = new String();
        for (int j = hilera.length() - 1; j >= 0; j--) {
            c = hilera.charAt(j);
            hileraInvertida = hileraInvertida.concat(c.toString());
        }
        instrucciones.add(INSTRUCCION_REPLACE + hileraInvertida + ")");
        instrucciones.add(INSTRUCCION_PERMANEZCA);
        if (esRetenga) {
            instrucciones.add(INSTRUCCION_RETENGA);
        } else {
            instrucciones.add(INSTRUCCION_AVANCE);
        }
        transiciones.put(llave, instrucciones);
        automata.setTransiciones(transiciones);
    }
}
