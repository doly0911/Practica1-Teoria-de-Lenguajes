/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.AutomataPila;
import Modelo.Estado;
import Utils.ConversorAutomata;
import excepcion.AutomataPilaExcepcion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Usuario externo
 */
public class AutomataPilaDAO implements IAutomataPilaDAO {

    private static final String INSTRUCCION_APILAR = "apile";
    private static final String INSTRUCCION_NINGUNA = "ninguna";
    private static final String INSTRUCCION_CAMBIA = "cambia ";
    ConversorAutomata ca = new ConversorAutomata();

    @Override
    public void agregarEstado(AutomataPila automataPila, Estado estado) throws AutomataPilaExcepcion {
        validarEstado(automataPila, estado);
        automataPila.getEstados().add(estado);
    }

    @Override
    public void modificarEstado(AutomataPila automataPila, Estado estado, int indice) throws AutomataPilaExcepcion {
        ArrayList<Estado> estados = automataPila.getEstados();
        Estado e = estados.get(indice);
        if (e != null) {
            validarEstado(automataPila, estado);
            estados.remove(e);
            estados.add(indice, estado);
        }else{
            throw new AutomataPilaExcepcion("El estado a modificar no existe");
        }
        //actualizar las transiciones 
        HashMap<String, ArrayList<String>> transiciones = automataPila.getTransiciones();
        Iterator it = transiciones.entrySet().iterator(); //recorriendo el HashMap
        while (it.hasNext()) {
            Map.Entry transicion = (Map.Entry) it.next(); // me envie el key y el valor
            ArrayList<String> instrucciones = (ArrayList<String>) transicion.getValue(); //casting para convertir el object en arraylist
            String instruccion = instrucciones.get(1); // obtengo la segunda posición de las transiciones que pertenece a los estados
            if (instruccion.contains(e.getNombre())) {
                instrucciones.add(1, INSTRUCCION_CAMBIA + estado.getNombre()); 
                transiciones.replace(transicion.getKey().toString(), instrucciones);   //actualizo el Hashmap        
            }            
            
        }
        automataPila.setEstados(estados);   //actualizo el AP     
        automataPila.setTransiciones(transiciones);        
    }

    @Override
    public Estado consultarEstadoPorNombre(AutomataPila automataPila, String nombreEstado) {
        ArrayList<Estado> estados = automataPila.getEstados();
        for (Estado e : estados) {
            if (e.getNombre().equalsIgnoreCase(nombreEstado)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public int consultarPosicionEstado(AutomataPila automataPila, String nombreEstado) {
        ArrayList<Estado> estados = automataPila.getEstados();
        for (Estado e : estados) {
            if (e.getNombre().equalsIgnoreCase(nombreEstado)) {
                return estados.indexOf(e);
            }
        }
        return -1;
    }

    protected void validarMatriz(String[][] matrizTransiciones, AutomataPila automataPila,
            int numeroFilas, int numeroColumnas) throws AutomataPilaExcepcion {
        int filas = numeroFilas;
        int columnas = numeroColumnas;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String idTransicion = matrizTransiciones[i][j];
                if (automataPila.getTransiciones().get(idTransicion) == null) {
                    throw new AutomataPilaExcepcion("La transición en la posición" + "(" + i + "," + j + ") no esta definida");
                }
            }
        }
    }

    protected void validarEstado(AutomataPila automataPila, Estado estado) throws AutomataPilaExcepcion {
        if (estado.getNombre() == null) {
            throw new AutomataPilaExcepcion("El nombre del estado no puede ser nulo");
        }
        if (estado.isInicial()) {
            ArrayList<Estado> estados = automataPila.getEstados();
            for (Estado e : estados) {
                if (e.isInicial()) {
                    e.setInicial(false);
                }
            }
        }
        int columnas = automataPila.getSimbolosEntrada().size();
        int filas = automataPila.getSimbolosPila().size();
        validarMatriz(estado.getTransicionesEstado(), automataPila, filas, columnas);
    }

    @Override
    public AutomataPila agregarTransicion(AutomataPila automataPila, String llave, ArrayList<String> valor) throws AutomataPilaExcepcion {
        validarTransicion(llave, valor);
        if (automataPila.getTransiciones().get(llave) == null) {
            HashMap<String, ArrayList<String>> transiciones = automataPila.getTransiciones();
            transiciones.put(llave, valor);
            automataPila.setTransiciones(transiciones);
        }
        return automataPila;
    }

    @Override
    public AutomataPila modificarTransicion(AutomataPila automataPila, String llave, String nuevaLlave, ArrayList<String> valor) throws AutomataPilaExcepcion {
        validarTransicion(llave, valor);
        HashMap<String, ArrayList<String>> transiciones = automataPila.getTransiciones();
        if (llave.equalsIgnoreCase(nuevaLlave)) {
            transiciones.replace(nuevaLlave, valor);
        } else {
            transiciones.remove(llave);
            transiciones.put(nuevaLlave, valor);
        }
        automataPila.setTransiciones(transiciones);
        int filas = automataPila.getSimbolosPila().size();
        int columnas = automataPila.getSimbolosEntrada().size();
        ArrayList<Estado> estados = automataPila.getEstados();
        String[][] transicionesEstado;
        String[][] transicionesMatrizT;
        for (Estado e : estados) {
            transicionesEstado = e.getTransicionesEstado();
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (transicionesEstado[i][j].equalsIgnoreCase(llave)) {
                        transicionesEstado[i][j] = nuevaLlave;
                    }
                }
            }
            
            transicionesMatrizT = e.getMatrizT();
           for (int i = 1; i < filas+1; i++) {
               for (int j = 1; j < columnas+1; j++) {
                   if (transicionesMatrizT[i][j].equalsIgnoreCase(llave)) {
                       transicionesMatrizT[i][j] = nuevaLlave;
                   }
               }                
           }
            e.setTransicionesEstado(transicionesEstado);
            e.setMatrizT(transicionesMatrizT);
        }
        return automataPila;
    }

    protected void validarTransicion(String llave, ArrayList<String> valor) throws AutomataPilaExcepcion {
        if (llave == null || llave.isEmpty()) {
            throw new AutomataPilaExcepcion("¡El nombre de la transición no puede estar vacía!");
        }
        if (valor == null || valor.isEmpty()) {
            throw new AutomataPilaExcepcion("¡Las transiciones no pueden estar vacías!");
        }
    }

    @Override
    public AutomataPila agregarSimboloPila(AutomataPila automataPila, String simbolosPila) throws AutomataPilaExcepcion {
        ArrayList simbolos = automataPila.getSimbolosPila();
        if (simbolos.contains(simbolosPila)) {
            throw new AutomataPilaExcepcion("El simbolo de la pila ya esta definido");
        }
        simbolos.add(simbolos.size(), simbolosPila);
        validarSimbolos(automataPila, simbolosPila);
        int filas = automataPila.getSimbolosPila().size();
        int columnas = automataPila.getSimbolosEntrada().size();
        String[][] nuevaTransicionesEstado = new String[filas][columnas];
        String[][] transicionesEstado;
        ArrayList<Estado> estados = automataPila.getEstados();
        int k;
        for ( k = 0; k < estados.size(); k++) {
            Estado e = estados.get(k);
            transicionesEstado = e.getTransicionesEstado();
            for (int i = 0; i < filas-1; i++) {
                for (int j = 0; j < columnas ; j++) {
                    nuevaTransicionesEstado[i][j] = transicionesEstado[i][j];
                }
            }
            e.setTransicionesEstado(nuevaTransicionesEstado);
            e.setMatrizT(crearMatrizT(automataPila.getSimbolosEntrada(), simbolos, e.getTransicionesEstado()));
            estados.set(k, e);
        }
        automataPila.setEstados(estados);
        automataPila.setSimbolosEntrada(simbolos);
        return automataPila;
    }

    @Override
    public AutomataPila eliminarSimboloPila(AutomataPila automataPila, String simboloPila) throws AutomataPilaExcepcion {
        ArrayList<String> simbolosPila = automataPila.getSimbolosPila();
        if (simbolosPila.contains(simboloPila)) {
            //Modifico la matriz de estados,eliminando una fila
            int indice = simbolosPila.indexOf(simboloPila); //posicion del simbolo a eliminar
            int filas = automataPila.getSimbolosPila().size();
            int columnas = automataPila.getSimbolosEntrada().size();
            simbolosPila.remove(simboloPila);
            String[][] nuevaTransicionesEstado = new String[filas - 1][columnas]; // creo mi nueva matriz de estados
            String[][] transicionesEstado; // la usare para recorrer la antigua matriz
            ArrayList<Estado> estados = automataPila.getEstados();  // obtengo todos mis estados

            int k = 0; // posicion [0] para filas de mi nueva matriz
            for (Estado e : estados) {
                transicionesEstado = e.getTransicionesEstado(); // me trae los estados de la antigua matriz                
                for (int i = 0; i < filas; i++) {
                    //si es diferente a la posicion que busco, que la mapee a la nueva matriz
                    k = 0;
                    if (i != indice) {
                        for (int j = 0; j < columnas; j++) {
                            nuevaTransicionesEstado[k][j] = transicionesEstado[i][j];
                        }
                        k++;
                    }
                }
                e.setTransicionesEstado(nuevaTransicionesEstado);
                e.setMatrizT(crearMatrizT(automataPila.getSimbolosEntrada(), simbolosPila, nuevaTransicionesEstado));
            }

            automataPila.setSimbolosPila(simbolosPila);
            //actualizar las transiciones 
            HashMap<String, ArrayList<String>> transiciones = automataPila.getTransiciones();
            Iterator it = transiciones.entrySet().iterator(); //recorriendo el HashMap
            while (it.hasNext()) {
                Map.Entry transicion = (Map.Entry) it.next(); // me envie el key y el valor
                ArrayList<String> instrucciones = (ArrayList<String>) transicion.getValue(); //casting para convertir el object en arraylist
                String instruccion = instrucciones.get(0); // obtengo la primera posición de las transiciones = operacion pila
                if (instruccion.contains(simboloPila)) {
                    instrucciones.add(0, INSTRUCCION_NINGUNA);
                }
            }

        } else {
            throw new AutomataPilaExcepcion("El simbolo de la pila que desea eliminar no existe");
        }
        return automataPila;
    }

    @Override
    public AutomataPila modificarSimboloPila(AutomataPila automataPila, String simboloPila, String nuevoSimboloPila) throws AutomataPilaExcepcion {
        validarSimbolos(automataPila, nuevoSimboloPila);
        ArrayList<String> simbolosPila = automataPila.getSimbolosPila();
        if (simbolosPila.contains(simboloPila)) {
            int indice = simbolosPila.indexOf(simboloPila);
            simbolosPila.set(indice, nuevoSimboloPila);
            automataPila.setSimbolosPila(simbolosPila);
            
            String [][] matrizT;
            ArrayList<Estado> estados = automataPila.getEstados();
            for (int k = 0; k < estados.size(); k++) {
                Estado e = estados.get(k); 
                matrizT = e.getMatrizT();
                matrizT[indice+1][0] = nuevoSimboloPila; 
                e.setMatrizT(matrizT);
                estados.set(k, e);
            }
            automataPila.setEstados(estados); 
        } else {
            throw new AutomataPilaExcepcion("El simbolo de la pila que quiere modificar no existe");
        }
        //Agregar la configuracion inicial de la pila
        ArrayList<String> configuracionInicial = automataPila.getConfiguracionInicial();
        if (configuracionInicial.contains(simboloPila)) {
            int indice = configuracionInicial.indexOf(simboloPila);
            configuracionInicial.set(indice, nuevoSimboloPila);
            automataPila.setConfiguracionInicial(configuracionInicial);
        }
        //actualizar las instrucciones de las transiciones
//        HashMap<String, ArrayList<String>> transiciones = automataPila.getTransiciones();
//        Iterator it = transiciones.entrySet().iterator(); //recorreindo el HashMap
//        while (it.hasNext()) {
//            Map.Entry transicion = (Map.Entry) it.next(); // me envie el key y el valor
//            ArrayList<String> instrucciones = (ArrayList<String>) transicion.getValue(); //casting para convertir el objevt en arraylist
//            String instruccion = instrucciones.get(0); // obtengo la primera posición de las transiciones = operac pila
//            if (instruccion.contains(INSTRUCCION_APILAR)) {
//                String simbolo = instruccion.substring(instruccion.indexOf("(") + 1, instruccion.indexOf(")")); //subtraigo el simbolo a apilar
//                if (simbolo.equalsIgnoreCase(simboloPila)) { // si es igual al valor viejo (antes de la modificacion)
//                    instrucciones.add(0, INSTRUCCION_APILAR + "(" + nuevoSimboloPila + ")");   // reemplacelo apile(valor nuevo)
//                }
//                transiciones.replace(transicion.getKey().toString(), instrucciones); //actualiza el hashmap
//            }
//        }
//        automataPila.setTransiciones(transiciones); //actualiza el AP 
        return automataPila;
    }

    public void validarSimbolos(AutomataPila automataPila, String simbolos) throws AutomataPilaExcepcion {
        if (simbolos == null || simbolos.isEmpty()) {
            throw new AutomataPilaExcepcion("El simbolo no puede ser vacio");
        }

    }

    @Override
    public AutomataPila agregarSimboloEntrada(AutomataPila automataPila, String simboloEntrada) throws AutomataPilaExcepcion {
        ArrayList simbolos = automataPila.getSimbolosEntrada();
        if (simbolos.contains(simboloEntrada)) {
            throw new AutomataPilaExcepcion("El simbolo de entrada ya esta definido");
        }
        simbolos.add(simbolos.size(), simboloEntrada);
        validarSimbolos(automataPila, simboloEntrada);
        int filas = automataPila.getSimbolosPila().size();
        int columnas = automataPila.getSimbolosEntrada().size();
        String[][] nuevaTransicionesEstado = new String[filas][columnas];
        String[][] transicionesEstado;
        ArrayList<Estado> estados = automataPila.getEstados();
        int k;
        for ( k = 0; k < estados.size(); k++) {
            Estado e = estados.get(k);
            transicionesEstado = e.getTransicionesEstado();
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas-1 ; j++) {
                    nuevaTransicionesEstado[i][j] = transicionesEstado[i][j];
                }
            }
            e.setTransicionesEstado(nuevaTransicionesEstado);
            e.setMatrizT(crearMatrizT(simbolos, automataPila.getSimbolosPila(), e.getTransicionesEstado()));
            estados.set(k, e);
        }
        automataPila.setEstados(estados);
        automataPila.setSimbolosEntrada(simbolos);
        return automataPila;
    }

    @Override
    public AutomataPila modificarSimboloEntrada(AutomataPila automataPila, String simboloEntrada, String nuevoSimboloEntrada) throws AutomataPilaExcepcion {
        validarSimbolos(automataPila, nuevoSimboloEntrada);
        ArrayList<String> simbolosEntrada = automataPila.getSimbolosEntrada();
        if (simbolosEntrada.contains(simboloEntrada)) {
            int indice = simbolosEntrada.indexOf(simboloEntrada);
            simbolosEntrada.set(indice, nuevoSimboloEntrada);
            automataPila.setSimbolosEntrada(simbolosEntrada);
            String [][] matrizT;
            ArrayList<Estado> estados = automataPila.getEstados();
            for (int k = 0; k < estados.size(); k++) {
                Estado e = estados.get(k); 
                matrizT = e.getMatrizT();
                matrizT[0][indice+1] = nuevoSimboloEntrada; 
                e.setMatrizT(matrizT);
                estados.set(k, e);
            }
            automataPila.setEstados(estados);   
        } else {
            throw new AutomataPilaExcepcion("El simbolo de entrada que quiere modificar no existe");
        }
        return automataPila;
    }

    @Override
    public AutomataPila eliminarSimboloEntrada(AutomataPila automataPila, String simboloEntrada) throws AutomataPilaExcepcion {
        ArrayList<String> simbolosEntrada = automataPila.getSimbolosEntrada();
        if (simbolosEntrada.contains(simboloEntrada)) {
            //Modifico la matriz de estados,eliminando una columna
            int indice = simbolosEntrada.indexOf(simboloEntrada); //posicion del simbolo a eliminar
            int filas = automataPila.getSimbolosPila().size();
            int columnas = automataPila.getSimbolosEntrada().size();
            simbolosEntrada.remove(simboloEntrada);
            String[][] nuevaTransicionesEstado = new String[filas][columnas - 1]; // creo mi nueva matriz de estados
            String[][] transicionesEstado; // la usare para recorrer la antigua matriz
            ArrayList<Estado> estados = automataPila.getEstados();  // obtengo todos mis estados

            int k = 0; // posicion [0] para columnas de mi nueva matriz
            for (Estado e : estados) {
                transicionesEstado = e.getTransicionesEstado(); // me trae los estados de la antigua matriz                
                for (int i = 0; i < filas; i++) {
                    k = 0;
                    for (int j = 0; j < columnas; j++) {
                        if (j != indice) {
                            nuevaTransicionesEstado[i][k] = transicionesEstado[i][j];
                            k++;
                        }
                    }
                }
                e.setTransicionesEstado(nuevaTransicionesEstado);
                e.setMatrizT(crearMatrizT(simbolosEntrada, automataPila.getSimbolosPila(), nuevaTransicionesEstado));
            }
            automataPila.setSimbolosEntrada(simbolosEntrada);
        }
        return automataPila;
    }

    @Override
    public Estado consultarEstadoInicial(AutomataPila automataPila) {
        ArrayList<Estado> estados = automataPila.getEstados();
        for (Estado estado : estados) {
            if (estado.isInicial()) {
                return estado;
            }
        }
        return null;
    }

    @Override
    public String[][] consultarMatrizTransiciones(AutomataPila automataPila, Estado estado) {
        ArrayList<Estado> estados = automataPila.getEstados();
        for (Estado e : estados) {
            if (e.getNombre().equalsIgnoreCase(estado.getNombre())) {
                return e.getTransicionesEstado();
            }
        }
        return null;
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
