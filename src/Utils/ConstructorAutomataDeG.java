/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.AutomataPila;
import Modelo.Gramatica;
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
    
    public AutomataPila construirAutomataDeG(Gramatica gramatica){
        this.gramatica = gramatica;
        automata = new AutomataPila();
        
        automata.setSimbolosEntrada(crearSimbolosEntrada());
        automata.setSimbolosPila(crearSimbolosPila());
        automata.setConfiguracionInicial(crearConfInicial());
        automata.setTransiciones(crearTransiciones());
        //automata.setPila(pila);
        return automata;
    }
    
    public ArrayList<String> crearSimbolosEntrada(){
        ArrayList<String> simbolosEntrada;
        simbolosEntrada = gramatica.getTerminales();
        Collections.sort(simbolosEntrada);
        simbolosEntrada.add("&");
        return simbolosEntrada;
    }
    
    public ArrayList<String> crearSimbolosPila(){
        ArrayList<String> simbolosPila;
        Character c;
        simbolosPila = new ArrayList<>();
       
        for(String p : gramatica.getProducciones()){
            for(int i=2; i<p.length(); i++){
                c = p.charAt(i);
                if(Character.isLowerCase(c)&&(!simbolosPila.contains(c.toString()))){
                    simbolosPila.add(c.toString());
                }
            }
        }
        Collections.sort(simbolosPila);
        simbolosPila.add("@");
        return simbolosPila;
    }
    
    public ArrayList<String> crearConfInicial(){
        ArrayList<String> confInicial;
        confInicial = new ArrayList<>();
        
        confInicial.add("@");
        confInicial.add(gramatica.getTerminales().get(0));
        Collections.sort(confInicial);
        return confInicial;
    }
    
    public HashMap<String, ArrayList<String>> crearTransiciones(){
        HashMap<String, ArrayList<String>> transiciones;
        transiciones = new HashMap<>();
        
        
        
        return transiciones;
    }
}
