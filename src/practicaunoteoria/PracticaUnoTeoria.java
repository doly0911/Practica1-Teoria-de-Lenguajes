/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaunoteoria;

import java.io.IOException;

/**
 *
 * @author Usuario externo
 */
public class PracticaUnoTeoria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        ConversorAutomata conversorAutomata= new ConversorAutomata();
        AutomataPila a =conversorAutomata.convertir("C:\\Users\\Doly Jimenez\\Desktop\\Json_valido.txt");
       
        // TODO code application logic here
    }
    
}
