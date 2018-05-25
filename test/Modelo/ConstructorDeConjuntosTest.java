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
 * @author Usuario externo
 */
public class ConstructorDeConjuntosTest {

    public ConstructorDeConjuntosTest() {
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
     * Test of construirAnulables method, of class ConstructorDeConjuntos.
     */
    @Test
    public void testConstruirAnulables() {
        System.out.println("construirAnulables");
        String gramaticaStr = "1. A = BcD\n"
                + "2. A = aE\n"
                + "3. B = bAc\n"
                + "4. B = |\n"
                + "5. D = dBDc\n"
                + "6. D = |\n"
                + "7. E = a\n"
                + "8. E = BD";
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);

        ConstructorDeConjuntos instance = new ConstructorDeConjuntos(g);
        instance.construirAnulables();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
