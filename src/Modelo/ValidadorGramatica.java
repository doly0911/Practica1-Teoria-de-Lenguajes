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
       }else if(esGramaticaQ()){
           return GRAMATICA_Q;
       }else if(esGramaticaLL()){
           return GRAMATICA_LL;
       }
       return GRAMATICA_INVALIDA;
    }
    
    //Gramatica S
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
            //si el primer simbolo del lado derecho no es un terminal o si es la secuenc nula entonces ya deja de ser G tipo S
            if(!terminales.contains(simbolo) || simbolo.equals(Gramatica.VACIO)){
                return false;
            }  
            Character n = produccion.charAt(0); //si cumple los requisitos, obtengo el no terminal al que pertenece, posicion 0 del string
            String noTerminal = n.toString();
            int indice = noTerminales.indexOf(noTerminal); //pido el indice donde esta ubicado el no terminal de mi lista de N de la G
            //en mi arraylist primeros ubico la posicion del N para agregar el terminal con el que esta comenzando su produccion 
            ArrayList<String> primeros = primerosSimbolosNoTerminal.get(indice);
            //si al agregar el terminal ya esta contenido como primer simbolo del lado derecho, ya deja der tipo S la G
            if(primeros.contains(simbolo)){
                return false;
            }else{
                primeros.add(simbolo); //sino lo agrego
                primerosSimbolosNoTerminal.set(indice,primeros); //actualizo mi arraylist(No terminal y su primer caracter de cada produccion)
            }
        }
        return true;
    }
    
    public boolean esGramaticaQ(){
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> terminales = gramatica.getTerminales();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        ArrayList<ArrayList<String>> primerosSimbolosNoTerminal = new ArrayList();
        ArrayList<ArrayList<String>> seleccionesProd = gramatica.getSeleccionProducciones();
        ArrayList<ArrayList<String>> seleccionesNoTerminal = new ArrayList<>();
        //inicializar estructura
        for (int i = 0; i < noTerminales.size(); i++) {
            primerosSimbolosNoTerminal.add(new ArrayList<>());
            seleccionesNoTerminal.add(new ArrayList<>());
        }
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            ArrayList<String> seleccion = seleccionesProd.get(i); // obtengo la produccion i
            Character c = produccion.charAt(1); //obtengo la segunda posicion del string de la produccion
            String simbolo = c.toString();
            //valido sino empieza con un terminal y si no empieza con la secuencia nula, descarto la gramatica 
            if(!terminales.contains(simbolo) && !simbolo.equals(Gramatica.VACIO)){
                return false;
            }  
            Character n = produccion.charAt(0); //si cumple los requisitos obtengo su N
            String noTerminal = n.toString();
            int indice = noTerminales.indexOf(noTerminal);
            ArrayList<String> primeros = primerosSimbolosNoTerminal.get(indice); 
            if(primeros.contains(simbolo)){
                return false;
            }else{
                primeros.add(simbolo);
                primerosSimbolosNoTerminal.set(indice,primeros);
            }
            //recorro el conj de seleccion de cada no terminal, verificando que 
            //las terminales sean diferentes
            ArrayList<String> seleccionNoTerminal = seleccionesNoTerminal.get(indice); 
            for(String s: seleccion){
                if(seleccionNoTerminal.contains(s)){ //
                    return false;
                }else{
                    seleccionNoTerminal.add(s);
                }
            }
            seleccionesNoTerminal.set(indice, seleccionNoTerminal);
        }      
        return true;
        
    }
    
    public boolean esGramaticaLL(){
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        ArrayList<ArrayList<String>> seleccionesProd = gramatica.getSeleccionProducciones();
        ArrayList<ArrayList<String>> seleccionesNoTerminal = new ArrayList<>();
        //inicializar estructura
        for (int i = 0; i < noTerminales.size(); i++) {
            seleccionesNoTerminal.add(new ArrayList<>());
        }
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            ArrayList<String> seleccion = seleccionesProd.get(i);
            Character n = produccion.charAt(0);
            String noTerminal = n.toString();
            int indice = noTerminales.indexOf(noTerminal);
            //recorro las producciones de cada N verificando que las terminales
            //con que empiecen el lado derecho sean diferentes
            ArrayList<String> seleccionNoTerminal = seleccionesNoTerminal.get(indice);
            for(String s: seleccion){
                if(seleccionNoTerminal.contains(s)){
                    return false;
                }else{
                    seleccionNoTerminal.add(s);
                }
            }
            seleccionesNoTerminal.set(indice, seleccionNoTerminal);
        }      
        return true;
    }
    
  
}
