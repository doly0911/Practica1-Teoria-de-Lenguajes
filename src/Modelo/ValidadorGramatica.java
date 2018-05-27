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
public class ValidadorGramatica {
    private final Gramatica gramatica;
    public static final int GRAMATICA_INVALIDA = 0;
    public static final int GRAMATICA_S = 1;
    public static final int GRAMATICA_Q = 2;
    public static final int GRAMATICA_LL = 3;
    
    
    public ValidadorGramatica(Gramatica gramatica){
        this.gramatica = gramatica;
    }
    
    public int validar(){
           
       if(esGramaticaS()){
           return GRAMATICA_S;
       }else{
           return GRAMATICA_INVALIDA;
       }
    }
    
    public boolean esGramaticaS(){
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> terminales = gramatica.getTerminales();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        ArrayList<ArrayList<String>> primerosSimbolosNoTerminal = new ArrayList();
        //inicializar estructura
        for (int i = 0; i < noTerminales.size(); i++) {
            ArrayList<String> simbolos = new ArrayList<>();
            primerosSimbolosNoTerminal.add(simbolos);
        }
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            Character c = produccion.charAt(1);
            String simbolo = c.toString();
            if(!terminales.contains(simbolo) || simbolo.equals(Gramatica.VACIO)){
                return false;
            }  
            Character n = produccion.charAt(0);
            String noTerminal = n.toString();
            int indice = noTerminales.indexOf(noTerminal);
            ArrayList<String> primeros = primerosSimbolosNoTerminal.get(indice);
            if(primeros.contains(simbolo)){
                return false;
            }else{
                primeros.add(simbolo);
                primerosSimbolosNoTerminal.set(indice,primeros);
            }
        }
        return true;
    }
    
  
}
