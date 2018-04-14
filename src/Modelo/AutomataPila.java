/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


/**
 *
 * @author Usuario externo
 */
public class AutomataPila {
    ArrayList<String> simbolosEntrada;
    ArrayList<String> simbolosPila;
    ArrayList<Estado> estados;
    ArrayList<String> configuracionInicial;
    HashMap<String, ArrayList<String>> transiciones;
    Stack<String> pila;
   

    public ArrayList<String> getSimbolosEntrada() {
        return simbolosEntrada;
    }

    public void setSimbolosEntrada(ArrayList<String> simbolosEntrada) {
        this.simbolosEntrada = simbolosEntrada;
    }

    public ArrayList<String> getSimbolosPila() {
        return simbolosPila;
    }

    public void setSimbolosPila(ArrayList<String> simbolosPila) {
        this.simbolosPila = simbolosPila;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public ArrayList<String> getConfiguracionInicial() {
        return configuracionInicial;
    }

    public void setConfiguracionInicial(ArrayList<String> configuracionInicial) {
        this.configuracionInicial = configuracionInicial;
    }

    public HashMap<String, ArrayList<String>> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(HashMap<String, ArrayList<String>> transiciones) {
        this.transiciones = transiciones;
    }

    public Stack<String> getPila() {
        return pila;
    }

    public void setPila(Stack<String> pila) {
        this.pila = pila;
    }

    
    
    
    
    
    
    
}
