/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Dao.IAutomataPilaDAO;
import Modelo.AutomataPila;
import Modelo.ConstructorDeConjuntos;
import Modelo.Estado;
import Modelo.Gramatica;
import java.util.ArrayList;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario externo
 */
public class ReconocedorHileraTest {
    
    public ReconocedorHileraTest() {
    }



    /**
     * Test of ejecutarOperacion method, of class ReconocedorHilera.
     */
    @Test
    public void testReconocerHileraGramaticaS() {
       String gramaticaStr = "1.B = bCd\n"
                + "2.B = aB\n"
                + "3.C = bCd\n"
                + "4.C = a\n";
        String hilera = "bbaddd&";
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);
        ConstructorDeConjuntos constructor = new ConstructorDeConjuntos(g);
        constructor.construirConjuntos();
        ConstructorAutomataDeG constructorAutomata = new ConstructorAutomataDeG();
        AutomataPila automata = constructorAutomata.construirAutomataDeG(g);
        ReconocedorHilera reconocedor = new ReconocedorHilera(automata);
        int valor = 0;
        for (int i = 0; i < hilera.length(); i++) {
            Character c = hilera.charAt(i);
            String s = c.toString();
            valor = reconocedor.recorrerCaracter(s);
            if(valor==0){
                break;
            }
        }
        assertEquals(1, valor);
    }


    
}
