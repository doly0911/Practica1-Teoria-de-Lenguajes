/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utils.ConversorGramatica;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pao_c
 */
public class ValidadorGramaticaTest {
    private String gramaticaStr;
    
    public ValidadorGramaticaTest() {
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
     * Test of validar method, of class ValidadorGramatica.
     */
    /**
     * Test of esGramaticaS method, of class ValidadorGramatica.
     */
    @Test
    public void testEsGramaticaS() {
        gramaticaStr = "1.A = cC\n"
                + "2.A = eDB\n"
                + "3.B = f\n"
                + "4.B = bCDE\n"
                + "5.C = aB\n"
                + "6.C = ca\n"
                + "7.D = e\n"
                + "8.D = dD\n"
                + "9.E = eAf\n"
                + "10.E = c";
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);
        ValidadorGramatica instance = new ValidadorGramatica(g);
        int expResult = ValidadorGramatica.GRAMATICA_S;
        int result = instance.validar();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGramaticaConSimboloVacio(){
        gramaticaStr = "1.A = cC\n"
                + "2.A = eDB\n"
                + "3.B = f\n"
                + "4.B = bCDE\n"
                + "5.C = aB\n"
                + "6.C = |\n"
                + "7.D = e\n"
                + "8.D = dD\n"
                + "9.E = eAf\n"
                + "10.E = c";
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);
        ValidadorGramatica instance = new ValidadorGramatica(g);
        int expResult = ValidadorGramatica.GRAMATICA_INVALIDA;
        int result = instance.validar();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGramaticaConPrimerosTerminalesRepetidos(){
        gramaticaStr = "1.A = cC\n"
                + "2.A = eDB\n"
                + "3.B = f\n"
                + "4.B = bCDE\n"
                + "5.C = aB\n"
                + "6.C = ca\n"
                + "7.D = d\n"
                + "8.D = dD\n"
                + "9.E = eAf\n"
                + "10.E = c";
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);
        ValidadorGramatica instance = new ValidadorGramatica(g);
        int expResult = ValidadorGramatica.GRAMATICA_INVALIDA;
        int result = instance.validar();
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testGramaticaConNoTerminal(){
        gramaticaStr = "1.A = cC\n"
                + "2.A = eDB\n"
                + "3.B = f\n"
                + "4.B = bCDE\n"
                + "5.C = AB\n"
                + "6.C = ca\n"
                + "7.D = e\n"
                + "8.D = dD\n"
                + "9.E = eAf\n"
                + "10.E = c";
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);
        ValidadorGramatica instance = new ValidadorGramatica(g);
        int expResult = ValidadorGramatica.GRAMATICA_INVALIDA;
        int result = instance.validar();
        assertEquals(expResult, result);
        
    }
    
    
    
}
