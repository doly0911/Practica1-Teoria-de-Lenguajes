/*
 * N: no terminal
 * T: terminal
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
    private final int dimensionMatriz;
    private final ArrayList<String> indiceMatriz;
    private int[][] comienzaCon;
    private int[][] seguidoDirectamente;
    private int[][] esFinDe;
    private int[][] seguidoPor;

    /**
     * Inicializa las matrices usadas para realizar el proceso que finalmente 
     * lleva a la construcción de los conjuntos de selección de cada producción.
     * Todas las matrices son cuadradas y su dimensión es igual a la cantidad 
     * total de T y N, por lo cual se crea la lista indiceMatriz 
     * cuyo tamaño es dicha dimensión.
     * @param gramatica
     */
    public ConstructorDeConjuntos(Gramatica gramatica) {
        this.noTerminalesAnulables = new ArrayList<>();
        this.produccionesAnulables = new ArrayList<>();
        this.gramatica = gramatica;
        indiceMatriz = new ArrayList<>();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        ArrayList<String> terminales = gramatica.getTerminales();
        for (String noTerminal : noTerminales) {
            indiceMatriz.add(noTerminal);
        }
        for (String terminal : terminales) {
            indiceMatriz.add(terminal);
        }
        //indiceMatriz.add(Gramatica.FIN_DE_SECUENCIA);
        dimensionMatriz = indiceMatriz.size()+1;
        this.comienzaCon = new int[dimensionMatriz][dimensionMatriz];
        this.seguidoDirectamente = new int[dimensionMatriz][dimensionMatriz];
        this.esFinDe = new int[dimensionMatriz][dimensionMatriz];
        this.seguidoPor = new int[dimensionMatriz][dimensionMatriz];
    }

    /**
     * Construye la lista de N anulables y producciones anulables.
     */
    public void construirAnulables() {
        String noTerminal;
        Character s;
        ArrayList<String> prod = gramatica.getProducciones();
        boolean esAnulable = true;
        for (int i = 0; i < prod.size(); i++) {
            String produccion = prod.get(i);
            s = produccion.charAt(1);
            noTerminal = s.toString(); //Lo ocnvierto a String
            if (noTerminal.equals(Gramatica.VACIO)) { //busco el fin se secuencia entre el primer valor de cada produccion
                Character n = produccion.charAt(0); //obtengo el primer caracter
                noTerminal = n.toString();
                noTerminalesAnulables.add(noTerminal);
                produccionesAnulables.add(i);

            }
            gramatica.setNoTerminalesAnulables(noTerminalesAnulables);
            gramatica.setProduccionesAnulables(produccionesAnulables);
        }

        for (int i = 0; i < prod.size(); i++) {
            String produccion = prod.get(i); // obtengo el String de cada Produccion
            for (int j = 1; j < produccion.length(); j++) { //Recorro cada String
                s = produccion.charAt(j);
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

    /**
     * Crea la matriz comienzaDirectamenteCon que relaciona cada N con el 
     * primer elemento (que puede ser T o N) de la producción en el cual N es
     * el lado izquierdo. Si el elemento es un N anulable, también se
     * crea una relación con el elemento que lo sigue y así sucesivamente.
     * Finalmente se crea la matriz comienzaCon que corresponde al cierre 
     * transitivo de comienzaDirectamenteCon.
     */
    public void crearRelacionComienzaCon() {
        int comienzaDirectamenteCon[][] = new int[dimensionMatriz][dimensionMatriz];
        ArrayList<String> producciones = gramatica.getProducciones();
        int fila = 0, columna = 0;
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            for (int j = 0; j < produccion.length(); j++) {
                Character c = produccion.charAt(j);
                String simbolo = c.toString();
                if (j == 0) {
                    fila = indiceMatriz.indexOf(simbolo);
                } else if (indiceMatriz.contains(simbolo)) {
                    columna = indiceMatriz.indexOf(simbolo);
                    comienzaDirectamenteCon[fila][columna] = 1;
                    if (!noTerminalesAnulables.contains(simbolo)) {
                        break;
                    }
                }
            }
        }
        comienzaCon = calcularCierreTransitivo(comienzaDirectamenteCon);
    }


    /**
     * Crea el conjunto de primeros de cada N.
     */
    public void crearPrimerosNoTerminales() {
        ArrayList<ArrayList<String>> primeros = new ArrayList<>();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        for (int i = 0; i < noTerminales.size(); i++) {
            ArrayList<String> terminales = new ArrayList<>();
            for (int j = noTerminales.size(); j < dimensionMatriz; j++) {
                if (comienzaCon[i][j] == 1) {
                    String terminal = indiceMatriz.get(j); //obtenemos el terminal de la columna
                    terminales.add(terminal);
                }
            }
            primeros.add(terminales);
        }
        gramatica.setPrimerosNoTerminales(primeros);
    }

    /**
     * Crea el conjunto de primeros de cada producción.
     */
    public void crearPrimerosProducciones() {
        ArrayList<ArrayList<String>> primeros = gramatica.getPrimerosNoTerminales();
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> terminales = gramatica.getTerminales();
        ArrayList<String> anulables = gramatica.getNoTerminalesAnulables();
        ArrayList<ArrayList<String>> primerosProducciones = new ArrayList<>();

        for (int i = 0; i < producciones.size(); i++) {
            String prod = producciones.get(i);
            ArrayList<String> primerosProd = new ArrayList<>();
            for (int j = 1; j < prod.length(); j++) {
                Character c = prod.charAt(j);
                String s = c.toString();
                if (terminales.contains(s)) {
                    primerosProd.add(s);
                    break;
                }
                if (!s.equals(Gramatica.VACIO)) {
                    int indice = indiceMatriz.indexOf(s);
                    ArrayList<String> primerosNoTerminal = primeros.get(indice);
                    for (int k = 0; k < primerosNoTerminal.size(); k++) {
                        String letra = primerosNoTerminal.get(k);
                        if (!primerosProd.contains(letra)) {
                            primerosProd.add(letra);
                        }
                    }
                    if (!anulables.contains(s)) {
                        break;
                    }
                }

            }
            primerosProducciones.add(primerosProd);
            gramatica.setPrimerosProducciones(primerosProducciones);
        }
    }

    /**
     * Crea la matriz seguidoDirectamente que relaciona cada N o T, del lado
     * derecho de una producción, con el elemento que lo sigue (que puede ser T 
     * o N). Si el elemento que lo sigue es un N anulable, también se
     * crea una relación con el siguiente elemento y así sucesivamente.
     */
    public void crearRelacionEsSeguidoDirectamentePor() {
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> anulables = gramatica.getNoTerminalesAnulables();
        int fila = 0, columna = 0;
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            for (int j = 1; j < produccion.length() - 1; j++) {
                Character c = produccion.charAt(j);
                String simbolo = c.toString();
                if (simbolo.equals(Gramatica.VACIO)) {
                    break;
                }
                fila = indiceMatriz.indexOf(simbolo);
                for (int k = j + 1; k < produccion.length(); k++) {
                    Character s = produccion.charAt(k);
                    String siguiente = s.toString();
                    if (!siguiente.equals(Gramatica.VACIO)) {
                        columna = indiceMatriz.indexOf(siguiente);
                        seguidoDirectamente[fila][columna] = 1;
                        if (!anulables.contains(siguiente)) {
                            break;
                        }
                    }

                }

            }
        }
        seguidoDirectamente[0][dimensionMatriz-1] = 1;
    }

    /**
     * Crea la matriz esFinDirectoDe que relaciona cada N con el elemento 
     * (que puede ser T o N) con el cual termina la producción en el cual N es
     * el lado izquierdo. Si el elemento es un N anulable, también se
     * crea una relación con el elemento que lo precede y así sucesivamente.
     * Finalmente se crea la matriz esFinDe que corresponde al cierre 
     * transitivo de esFinDirectoDe.
     */
    public void crearRelacionEsFinDe() {
        int esFinDirectoDe[][] = new int[dimensionMatriz][dimensionMatriz];
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<String> anulables = gramatica.getNoTerminalesAnulables();
        int fila = 0, columna = 0;
        for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);
            Character n = produccion.charAt(0);
            String ladoIzq = n.toString();
            columna = indiceMatriz.indexOf(ladoIzq);
            for (int j = produccion.length() - 1; j > 0; j--) {
                Character c = produccion.charAt(j);
                String simbolo = c.toString();
                if (!simbolo.equals(Gramatica.VACIO)) {
                    fila = indiceMatriz.indexOf(simbolo);
                    esFinDirectoDe[fila][columna] = 1;
                    if (!anulables.contains(simbolo)) {
                        break;
                    }
                }

            }
        }
        esFinDe = calcularCierreTransitivo(esFinDirectoDe);
    }
    
     /**
     *
     * @param matriz
     * @return
     */
    public int[][] calcularCierreTransitivo(int matriz[][]) {
        for (int k = 0; k < dimensionMatriz; k++) {
            for (int i = 0; i < dimensionMatriz; i++) {
                if(k==i){
                    matriz[i][k] = 1;
                }
                if (matriz[i][k] == 1) {
                    for (int j = 0; j < dimensionMatriz; j++) {
                        if (matriz[k][j] == 1) {
                            matriz[i][j] = 1;
                        }
                    }
                }
            }
        }
        
        return matriz;
    }
    
     /**
     * Calcula la matriz seguidoPor que corresponde asl producto de las matrices
     * comienzaCon, seguidoDirectamente y esFinDe.
     */
    public void crearRelacionEsSeguidoPor(){
        seguidoPor = CalcularProducto(esFinDe, seguidoDirectamente);
        seguidoPor = CalcularProducto(seguidoPor, comienzaCon);
    }
        
    /**
     *
     * @param matrizA
     * @param matrizB
     * @return
     */
    public int[][] CalcularProducto(int matrizA[][], int matrizB[][]) {
        int matrizC[][] = new int[dimensionMatriz][dimensionMatriz];
        for (int k = 0; k < dimensionMatriz; k++) {
            for (int i = 0; i < dimensionMatriz; i++) {
                if (matrizA[i][k] == 1) {
                    for (int j = 0; j < dimensionMatriz; j++) {
                        if (matrizB[k][j] == 1) {
                            matrizC[i][j] = 1;
                        }
                    }
                }
            }
        }
        return matrizC;
    }
    
    public void crearSiguientesNoTerminales() {
        indiceMatriz.add(Gramatica.FIN_DE_SECUENCIA);
        ArrayList<ArrayList<String>> siguientes = new ArrayList<>();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        for (int i = 0; i < noTerminales.size(); i++) {
            ArrayList<String> terminales = new ArrayList<>();
            for (int j = noTerminales.size(); j < dimensionMatriz; j++) {
                if (seguidoPor[i][j] == 1) {
                    String terminal = indiceMatriz.get(j); //obtenemos el terminal de la columna
                    terminales.add(terminal);
                }
            }
            siguientes.add(terminales);
        }
        gramatica.setSiguientesNoTerminales(siguientes);
    }
    
    /**
     * Construye el conjunto de selección de cada producción a partir de los 
     * conjuntos de siguientes y primeros.
     */
    public void construirSeleccionProducciones(){
        ArrayList<ArrayList<String>> primerosP = gramatica.getPrimerosProducciones();
        ArrayList<ArrayList<String>> siguientes = gramatica.getSiguientesNoTerminales();
        ArrayList<String> producciones = gramatica.getProducciones();
        ArrayList<Integer> anulables = gramatica.getProduccionesAnulables();
        ArrayList<String> noTerminales = gramatica.getNoTerminales();
        ArrayList<ArrayList<String>> seleccionProducciones = new ArrayList<>();
        ArrayList<String> seleccion;
        String produccion;
        
        
        for (int i = 0; i < primerosP.size(); i++) { 
            seleccion = primerosP.get(i);
            produccion = producciones.get(i);
            Character c = produccion.charAt(0);
            String noTerminal = c.toString();
            if(anulables.contains(i)){
                int indice = noTerminales.indexOf(noTerminal);
                ArrayList<String> siguientesNoTerminal = siguientes.get(indice);
                for(String s : siguientesNoTerminal){
                    if(!seleccion.contains(s)){
                        seleccion.add(s);
                    }
                }
                
            }
            seleccionProducciones.add(seleccion);
        }
        gramatica.setSeleccionProducciones(seleccionProducciones);
    }
    
    public void crearTerminalesEnPila(){
        ArrayList<String> tEnPila;
        Character c;
        tEnPila = new ArrayList<>();
        
        for(String p : gramatica.getProducciones()){
            for(int i=2; i<p.length(); i++){
                c = p.charAt(i);
                if(Character.isLowerCase(c)&&(!tEnPila.contains(c.toString()))){
                    tEnPila.add(c.toString());
                }
            }
        }
        gramatica.setTerminalesEnPila(tEnPila);
    }
    
    public void construirConjuntos(){
        this.construirAnulables();
        this.crearRelacionComienzaCon();
        this.crearPrimerosNoTerminales();
        this.crearPrimerosProducciones();
        this.crearRelacionEsSeguidoDirectamentePor();
        this.crearRelacionEsFinDe();
        this.crearRelacionEsSeguidoPor();
        this.crearSiguientesNoTerminales();
        this.construirSeleccionProducciones();
        this.crearTerminalesEnPila();
    }
    

    public int[][] getComienzaCon() {
        return comienzaCon;
    }

    public void setComienzaCon(int[][] comienzaCon) {
        this.comienzaCon = comienzaCon;
    }

    public int[][] getSeguidoDirectamente() {
        return seguidoDirectamente;
    }

    public void setSeguidoDirectamente(int[][] seguidoDirectamente) {
        this.seguidoDirectamente = seguidoDirectamente;
    }

    public int[][] getEsFinDe() {
        return esFinDe;
    }

    public void setEsFinDe(int[][] esFinDe) {
        this.esFinDe = esFinDe;
    }

    public int[][] getSeguidoPor() {
        return seguidoPor;
    }

    public void setSeguidoPor(int[][] seguidoPor) {
        this.seguidoPor = seguidoPor;
    }
    
    

}
