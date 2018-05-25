/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Usuario externo
 */
public class ConstructorDeConjuntos {

    private final ArrayList<String> noTerminalesAnulables;
    private final ArrayList<Integer> produccionesAnulables;
    private final Gramatica gramatica;

    public ConstructorDeConjuntos(Gramatica gramatica) { 
        this.noTerminalesAnulables = new ArrayList<>();
        this.produccionesAnulables = new ArrayList<>();
        this.gramatica = gramatica;
    }

    public void construirAnulables() {
        String noTerminal;
        Character s;
        ArrayList<String> prod = gramatica.getProducciones();
        boolean esAnulable = true;
        for (int i = 0; i < prod.size(); i++) {
            String produccion = prod.get(i);
            s = produccion.charAt(1);
            noTerminal = s.toString(); //Lo ocnvierto a String
            if (noTerminal.equals(Gramatica.FIN_DE_SECUENCIA)) { //busco el fin se secuencia entre el primer valor de cada produccion
                Character n = produccion.charAt(0); //obtengo el primer caracter
                noTerminal = n.toString();
                noTerminalesAnulables.add(noTerminal);
                produccionesAnulables.add(i);

            }
            gramatica.setNoTerminalesAnulables(noTerminalesAnulables);
            gramatica.setProduccionesAnulables(produccionesAnulables);
        }

        for (int i = 0; i < prod.size(); i++) {
            String produccion = prod.get(i);
            for (int j = 0; j < produccion.length(); j++) {
                s = produccion.charAt(1);
                noTerminal = s.toString();
                if (!noTerminalesAnulables.contains(noTerminal)) {
                    esAnulable = false;
                    break;
                }
            }
            if (esAnulable) {
                s = produccion.charAt(0);
                noTerminalesAnulables.add(s.toString());
                produccionesAnulables.add(i);
            }
            esAnulable = true;

        }

    }

}
