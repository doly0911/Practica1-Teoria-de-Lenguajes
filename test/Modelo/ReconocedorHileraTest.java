/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utils.ConversorAutomata;
import Utils.ReconocedorHilera;
import java.io.File;
import java.util.ArrayList;
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
     * Test of recorrerCaracter method, of class ReconocedorHilera.
     */
    @Test
    public void testRecorrerCaracter() throws Exception {
        File archivo = new File("C:\\Users\\Doly Jimenez\\Desktop\\Json_valido.txt");
        ConversorAutomata conversorAutomata = new ConversorAutomata();
        AutomataPila automataPila = conversorAutomata.convertir(archivo);
        ReconocedorHilera reconocedorHilera = new ReconocedorHilera(automataPila); //en el frontEnd debe ser global
        String hilera ="01&";
        String c="";
        int i =0;//en el frontEnd debe ser global
        boolean retenerHilera = false; //en el frontEnd debe ser global
       while(i< hilera.length()){
           if(!retenerHilera){
               c =String.valueOf(hilera.charAt(i));
               i++;
               
           }
           retenerHilera =!reconocedorHilera.recorrerCaracter(c); // desde el if va en el btn_avanzar
       }
        assertTrue(!retenerHilera);
       
    }

 
    
}
