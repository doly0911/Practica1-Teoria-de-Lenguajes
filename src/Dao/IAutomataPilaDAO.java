/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.AutomataPila;
import Modelo.Estado;
import excepcion.AutomataPilaExcepcion;
import java.util.ArrayList;

/**
 *
 * @author Usuario externo
 */
public interface IAutomataPilaDAO {
    
    public void agregarSimboloPila(AutomataPila automataPila,String simbolosPila) throws AutomataPilaExcepcion;
    
    public void modificarSimboloPila(AutomataPila automataPila,String simboloPila,String nuevoSimboloPila) throws AutomataPilaExcepcion;
    
    public void agregarSimboloEntrada(AutomataPila automataPila,String simbolosEntrada) throws AutomataPilaExcepcion;
    
    public void modificarSimboloEntrada(AutomataPila automataPila,String simboloEntrada,String nuevoSimboloEntrada) throws AutomataPilaExcepcion;
    
    public void agregarEstado(AutomataPila automataPila,Estado estado) throws AutomataPilaExcepcion;
    
    public void modificarEstado(AutomataPila automataPila,Estado estado,int indice) throws AutomataPilaExcepcion;
    
    public Estado consultarEstadoPorNombre(AutomataPila automataPila,String nombreEstado);
    
    public int consultarPosicionEstado(AutomataPila automataPila,String nombreEstado);
    
    public void agregarTransicion (AutomataPila automataPila, String llave, ArrayList<String> valor) throws AutomataPilaExcepcion;
    
    public void modificarTransicion(AutomataPila automataPila, String llave, String nuevaLlave, ArrayList<String> valor)throws AutomataPilaExcepcion;
    
    public Estado consultarEstadoInicial(AutomataPila automataPila);
    
    public String[][] consultarMatrizTransiciones(AutomataPila automataPila, Estado estado);
    
}
