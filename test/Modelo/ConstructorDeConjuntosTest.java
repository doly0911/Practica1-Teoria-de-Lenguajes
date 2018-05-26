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
    private String gramaticaStr;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gramaticaStr = "1.A = BCc\n"
                + "2.A = eDB\n"
                + "3.B = |\n"
                + "4.B = bCDE\n"
                + "5.C = DaB\n"
                + "6.C = ca\n"
                + "7.D = |\n"
                + "8.D = dD\n"
                + "9.E = eAf\n"
                + "10.E = c";
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
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);

        ConstructorDeConjuntos instance = new ConstructorDeConjuntos(g);
        instance.construirAnulables();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testConstruirCierreTransitivo() {
        System.out.println("construirAnulables");
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);

        ConstructorDeConjuntos instance = new ConstructorDeConjuntos(g);
        instance.construirAnulables();
        instance.crearMatrizComienzaDirectamenteCon();
        int[][] cierreTransitivo = instance.CalcularCierreTransitivo(instance.getComienzaCon());
        instance.crearPrimerosNoTerminales();
        instance.crearPrimerosProducciones();
        instance.crearEsSeguidoDirectamentePor();
        instance.crearEsFinDirectoDe();
        instance.CalcularCierreTransitivo(instance.getEsFinDe());
        fail("The test case is a prototype.");
    }

    @Test
    public void testConstruirMatrizComienzaDirectamenteCon() {
        ConversorGramatica conversor = new ConversorGramatica();
        Gramatica g = conversor.convertir(gramaticaStr);

        ConstructorDeConjuntos instance = new ConstructorDeConjuntos(g);
        instance.construirAnulables();
        instance.crearMatrizComienzaDirectamenteCon();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
