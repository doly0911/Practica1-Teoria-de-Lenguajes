/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.AutomataPila;
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
        simbolosEntrada.add(Gramatica.FIN_DE_SECUENCIA);
        return simbolosEntrada;
    }
    
    public ArrayList<String> crearSimbolosPila(){
        ArrayList<String> simbolosPila;
        Character c;
        simbolosPila = gramatica.getNoTerminales();
        
        for(String p : gramatica.getProducciones()){
            for(int i=2; i<p.length(); i++){
                c = p.charAt(i);
                if(Character.isLowerCase(c)&&(!simbolosPila.contains(c.toString()))){
                    simbolosPila.add(c.toString());
                }
            }
        }
        Collections.sort(simbolosPila);
        simbolosPila.add(AutomataPila.PILA_VACIA);
        return simbolosPila;
    }
    
    public ArrayList<String> crearConfInicial(){
        ArrayList<String> confInicial;
        confInicial = new ArrayList<>();
        
        confInicial.add(AutomataPila.PILA_VACIA);
        confInicial.add(gramatica.getTerminales().get(0));
        Collections.sort(confInicial);
        return confInicial;
    }
    
    public HashMap<String, ArrayList<String>> crearTransiciones(){
        HashMap<String, ArrayList<String>> transiciones;
        transiciones = new HashMap<>();
        
        //se identifica el tipo de gramática para poder construir las transiciones
        ValidadorGramatica validador = new ValidadorGramatica(gramatica); 
        int tipoDeG = validador.validar();
        
        switch(tipoDeG){
            
            case 0:
                break;
                
            case 1: //gramatica S
                break;
                
            case 2: //gramatica Q
                break;
            
            case 3: //gramatica LL
                break;
        }
        
        return transiciones;
    }
}
