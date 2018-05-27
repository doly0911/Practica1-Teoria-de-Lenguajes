/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Stack;

public class ReconocedorDescendente {
    private Gramatica gramatica;
    private Stack<String> pila;
    
    public ReconocedorDescendente(Gramatica gramatica) {
        this.gramatica = gramatica;
        pila = new Stack<>();
        for(String simbolo: gramatica.getAutomataPila().getConfiguracionInicial()){
             pila.push(simbolo);
        }
    }
    
    
    public void iniciar(){
        
    }
}
