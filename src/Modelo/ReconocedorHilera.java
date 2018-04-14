/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Dao.AutomataPilaDAO;
import Dao.IAutomataPilaDAO;
import excepcion.AutomataPilaExcepcion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Usuario externo
 */
public class ReconocedorHilera {
    private AutomataPila automataPila;
    private Stack<String> pila;
    private IAutomataPilaDAO automataPilaDAO;
    private Estado estadoActual;
    private String[][] matrizTransicionesActual;
    
    private static final String INSTRUCCION_APILAR = "apile(";
    private static final String INSTRUCCION_DESAPILAR = "desapile";
    private static final String INSTRUCCION_NINGUNA = "ninguna";
    private static final String INSTRUCCION_PERMANEZCA = "permanezca";
    private static final String INSTRUCCION_RETENGA = "retenga";
    private static final String INSTRUCCION_AVANCE = "avance";
    private static final String INSTRUCCION_CAMBIA = "cambia";
    private static final String INSTRUCCION_ACEPTE = "Acepte";
    private static final String INSTRUCCION_RECHACE = "rechace";
    
    
    public ReconocedorHilera(AutomataPila automataPila){
        this.automataPila = automataPila;
        automataPilaDAO = new AutomataPilaDAO();
        estadoActual = automataPilaDAO.consultarEstadoInicial(automataPila);
        pila = new Stack<>();
        for(String simbolo: automataPila.getConfiguracionInicial()){
             pila.push(simbolo);
        }
    }
    
    public boolean recorrerCaracter(String caracter) throws AutomataPilaExcepcion{
        ArrayList<String> simbolosEntrada = this.automataPila.getSimbolosEntrada();
        ArrayList<String> simbolosPila = this.automataPila.getSimbolosPila();
        HashMap<String, ArrayList<String>> transiciones = this.automataPila.getTransiciones();
        int col = simbolosEntrada.indexOf(caracter);
        String simboloPila = pila.peek();
        int fila = simbolosPila.indexOf(simboloPila);
        matrizTransicionesActual = automataPilaDAO.consultarMatrizTransiciones(automataPila, estadoActual);
        String nombreTransicion = matrizTransicionesActual[fila][col];
        if(nombreTransicion== null || nombreTransicion.isEmpty()){
            throw new AutomataPilaExcepcion("Hilera rechazada"); //Falta especificar el error
        }
        ArrayList<String> instrucciones = transiciones.get(nombreTransicion);
        return ejecutarOperacion(instrucciones);
        
    }
    
    
    protected boolean ejecutarOperacion(ArrayList<String> instrucciones) throws AutomataPilaExcepcion{
        for(String instruccion: instrucciones){
            int operacion = instrucciones.indexOf(instruccion);
            switch(operacion){
                case 0:
                    if(instruccion.contains(INSTRUCCION_NINGUNA)){
                        break;
                    }
                    if(instruccion.contains(INSTRUCCION_APILAR)){
                        String simbolo = instruccion.substring(instruccion.indexOf("(")+1,instruccion.indexOf(")"));
                        pila.push(simbolo);
                    }else if(instruccion.equalsIgnoreCase(INSTRUCCION_DESAPILAR)){
                        pila.pop();
                    }else if(instruccion.equalsIgnoreCase(INSTRUCCION_ACEPTE)){
                        return true;
                    }else if(instruccion.equalsIgnoreCase(INSTRUCCION_RECHACE)){
                        throw new AutomataPilaExcepcion("Hilera rechazada");
                    }
                    break;
                case 1:
                    if(instruccion.contains(INSTRUCCION_CAMBIA)){
                       String partes[] = instruccion.split(" ");
                       String estado = partes[1];
                       estadoActual = automataPilaDAO.consultarEstadoPorNombre(automataPila, estado);
                       //matrizTransicionesActual = automataPilaDAO.consultarMatrizTransiciones(automataPila, estadoActual);
                    }
                    break;
                case 2:
                    if(instruccion.equalsIgnoreCase(INSTRUCCION_AVANCE)){
                        return true;
                    }else if(instruccion.equalsIgnoreCase(INSTRUCCION_RETENGA)){
                        return false;
                    }
                    break;                   
            }
        }
        return false;
    }
}
