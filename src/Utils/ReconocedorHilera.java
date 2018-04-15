/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dao.AutomataPilaDAO;
import Dao.IAutomataPilaDAO;
import Modelo.AutomataPila;
import Modelo.Estado;
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
    
    public int recorrerCaracter(String caracter) {
        ArrayList<String> simbolosEntrada = this.automataPila.getSimbolosEntrada();
        ArrayList<String> simbolosPila = this.automataPila.getSimbolosPila();
        HashMap<String, ArrayList<String>> transiciones = this.automataPila.getTransiciones();
        int col = simbolosEntrada.indexOf(caracter);
        String simboloPila = pila.peek();
        int fila = simbolosPila.indexOf(simboloPila);
        matrizTransicionesActual = automataPilaDAO.consultarMatrizTransiciones(automataPila, estadoActual);
        String nombreTransicion = matrizTransicionesActual[fila][col];
        if(nombreTransicion == null || nombreTransicion.isEmpty()){
            return 2;
        }
        ArrayList<String> instrucciones = transiciones.get(nombreTransicion);
        return ejecutarOperacion(instrucciones);
        
    }
    
    
    protected int ejecutarOperacion(ArrayList<String> instrucciones) {
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
                        return 1;
                    }else if(instruccion.equalsIgnoreCase(INSTRUCCION_RECHACE)){
                        return 2;
                    }
                    break;
                case 1:
                    if(instruccion.contains(INSTRUCCION_CAMBIA)){
                       String partes[] = instruccion.split(" ");
                       String estado = partes[1];
                       estadoActual = automataPilaDAO.consultarEstadoPorNombre(automataPila, estado);
                    }
                    break;
                case 2:
                    if(instruccion.equalsIgnoreCase(INSTRUCCION_AVANCE)){
                        return 0;
                    }else if(instruccion.equalsIgnoreCase(INSTRUCCION_RETENGA)){
                        return 3;
                    }
                    break;                   
            }
        }
        return 2;
    }

    public AutomataPila getAutomataPila() {
        return automataPila;
    }

    public void setAutomataPila(AutomataPila automataPila) {
        this.automataPila = automataPila;
    }

    public Stack<String> getPila() {
        return pila;
    }

    public void setPila(Stack<String> pila) {
        this.pila = pila;
    }

    public IAutomataPilaDAO getAutomataPilaDAO() {
        return automataPilaDAO;
    }

    public void setAutomataPilaDAO(IAutomataPilaDAO automataPilaDAO) {
        this.automataPilaDAO = automataPilaDAO;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String[][] getMatrizTransicionesActual() {
        return matrizTransicionesActual;
    }

    public void setMatrizTransicionesActual(String[][] matrizTransicionesActual) {
        this.matrizTransicionesActual = matrizTransicionesActual;
    }
    
    
}
