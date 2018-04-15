/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.AutomataPila;
import Utils.ReconocedorHilera;
import java.util.Stack;

/**
 *
 * @author pao
 */
public class CtrlVentanaPrincipal {
    private Stack<String> pila;
    private ReconocedorHilera reconocedorHilera;

    public Stack<String> cargarPila(AutomataPila automataPils){
    reconocedorHilera = new ReconocedorHilera(automataPils);
    this.setPila(reconocedorHilera.getPila());
    return reconocedorHilera.getPila();
    }

    public void setPila(Stack<String> pila) {
    this.pila = pila;
    }

    public void setReconocedorHilera(ReconocedorHilera reconocedorHilera) {
    this.reconocedorHilera = reconocedorHilera;
    }
}
