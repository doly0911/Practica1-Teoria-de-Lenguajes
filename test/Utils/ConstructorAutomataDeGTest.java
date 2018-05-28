/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.AutomataPila;
import Modelo.ConstructorDeConjuntos;
import Modelo.Gramatica;
import Modelo.ValidadorGramatica;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ConstructorAutomataDeGTest {

    public ConstructorAutomataDeGTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of construirAutomataDeG method, of class ConstructorAutomataDeG.
     */
//    @Test
//    public void testConstruirAutomataDeGramticaS() {
//        String gramaticaStr = "1.B = bCd\n"
//                + "2.B = aB\n"
//                + "3.C = bCd\n"
//                + "4.C = a\n";
//        ConversorGramatica conversor = new ConversorGramatica();
//        Gramatica g = conversor.convertir(gramaticaStr);
//        ConstructorDeConjuntos constructor = new ConstructorDeConjuntos(g);
//        constructor.construirConjuntos();
//        ConstructorAutomataDeG constructorAutomata = new ConstructorAutomataDeG();
//        AutomataPila automata = constructorAutomata.construirAutomataDeG(g);
//        assertNotNull(automata);
//    }

    @Test
    public void testConstruirAutomataDeGramticaQ() {
        String gramaticaStr
                = "1.A = aC\n"
                + "2.A = bBc\n"
                + "3.B = aA\n"
                + "4.B = bB\n"
                + "2.C = aCc\n"
                + "3.C = bBcc\n"
                + "4.C = |\n";
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);
        ConstructorDeConjuntos constructor = new ConstructorDeConjuntos(g);
        constructor.construirConjuntos();
        ConstructorAutomataDeG constructorAutomata = new ConstructorAutomataDeG();
        AutomataPila automata = constructorAutomata.construirAutomataDeG(g);
        assertNotNull(automata);
    }
//
//    @Test
//    public void testConstruirAutomataDeGramticaLL() {
//        String gramaticaStr = "1. A = BcD\n"
//                + "2. A = aE\n"
//                + "3. B = bAc\n"
//                + "4. B = |\n"
//                + "5. D = dBDc\n"
//                + "6. D = |\n"
//                + "7. E = a\n"
//                + "8. E = BD";
//        ConversorGramatica conversor = new ConversorGramatica();
//        Gramatica g = conversor.convertir(gramaticaStr);
//        ConstructorDeConjuntos constructor = new ConstructorDeConjuntos(g);
//        constructor.construirConjuntos();
//        ConstructorAutomataDeG constructorAutomata = new ConstructorAutomataDeG();
//        AutomataPila automata = constructorAutomata.construirAutomataDeG(g);
//        assertNotNull(automata);
//    }

}
