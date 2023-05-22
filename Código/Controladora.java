import java.util.ArrayList;
import java.util.Collections;

public class Controladora {
    
    //Atributos
    private int[][] distancias;
    private String[][] recorridos;
    private int tamanioMatriz;
    private FloydWarshall floydwarshall;
    private int centroMatriz;
    private String centroMatrizString;
    private ArrayList<String> ciudades;
    
    //Matrices temporales (no son las que se usan para realizar el algoritmo FW, sino solo para tener guardados los datos del archivo)
    int [][] distanciasNormal;
    int [][] distanciasNieve;
    int [][] distanciasLluvia;
    int [][] distanciasTormenta;

    //Constructores

    /**
     * Constructora
     * @param tamanioMatriz
     */
    public Controladora(int tamanioMatriz) {
        this.distancias = new int[tamanioMatriz][tamanioMatriz];
        this.recorridos = new String[tamanioMatriz][tamanioMatriz];
        this.tamanioMatriz = tamanioMatriz;

        this.floydwarshall = new FloydWarshall(distancias, recorridos, tamanioMatriz);
        
        this.distanciasNormal = new int[tamanioMatriz][tamanioMatriz];
        this.distanciasNieve = new int[tamanioMatriz][tamanioMatriz];
        this.distanciasLluvia = new int[tamanioMatriz][tamanioMatriz];
        this.distanciasTormenta = new int[tamanioMatriz][tamanioMatriz];

        this.ciudades = new ArrayList<String>();
        this.centroMatriz = 0;
        this.centroMatrizString = "";
    }

    /**
     * Constructora con parámetros
     * @param distancias
     * @param recorridos
     * @param tamanioMatriz
     * @param calculadorCentro
     * @param floydwarshall
     * @param distanciasNormal
     * @param distanciasNieve
     * @param distanciasLluvia
     * @param distanciasTormenta
     * @param ciudades
     */
    public Controladora(int[][] distancias, String[][] recorridos, int tamanioMatriz, FloydWarshall floydwarshall, int[][] distanciasNormal, int[][] distanciasNieve, int[][] distanciasLluvia, int[][] distanciasTormenta, ArrayList<String> ciudades) {
        this.distancias = distancias;
        this.recorridos = recorridos;
        this.tamanioMatriz = tamanioMatriz;
        this.floydwarshall = floydwarshall;
        this.distanciasNormal = distanciasNormal;
        this.distanciasNieve = distanciasNieve;
        this.distanciasLluvia = distanciasLluvia;
        this.distanciasTormenta = distanciasTormenta;
        this.ciudades = ciudades;
    }    
    
    //Métodos

    /**
     * Lee el archivo.
     * @param lineasDatos
     * @throws Exception
     */
    public void leerArchivo(ArrayList<String> lineasDatos) throws Exception{

        for(int i = 0; i<tamanioMatriz; i++){
            for(int j = 0; j < tamanioMatriz; j++){
                distanciasNormal[i][j] = 1000000000;
                distanciasLluvia[i][j] = 1000000000;
                distanciasNieve[i][j] = 1000000000;
                distanciasTormenta[i][j] = 1000000000;
            }
        }
        for(int i = 0; i<tamanioMatriz; i++){
            for(int j = 0; j < tamanioMatriz; j++){
                if(i == j){
                    distanciasNormal[i][j] = 0;
                    distanciasLluvia[i][j] = 0;
                    distanciasNieve[i][j] = 0;
                    distanciasTormenta[i][j] = 0;
                }
            }
        }

        ArrayList<String> unaLinea = new ArrayList<String>();
        ArrayList<ArrayList<String>> dos = new ArrayList<ArrayList<String>>();

        for (String fila : lineasDatos) {
            String [] lineaSeparada = fila.split(" ");
            unaLinea = new ArrayList<String>();
            for (String caracter : lineaSeparada) {
                unaLinea.add(caracter.trim());
            }
            dos.add(unaLinea);
        }

        for (ArrayList<String> linea : dos) {
            String ciudad1 = linea.get(0);
            String ciudad2 = linea.get(1);

            if(! ciudades.contains(ciudad1)){
                ciudades.add(ciudad1);
            }
            if(! ciudades.contains(ciudad2)){
                ciudades.add(ciudad2);
            }
        }

        for(int i = 0; i<tamanioMatriz; i++){
            for(int j = 0; j < tamanioMatriz; j++){
                recorridos[i][j] = ciudades.get(j);
            }
        }

        for (ArrayList<String> linea : dos) {
            
            int i = 0;
            int j = 0;
            String ciudad1 = linea.get(0);
            String ciudad2 = linea.get(1);

            for (int k = 0; k<ciudades.size();k++) {
                if(ciudad1.equals(ciudades.get(k))){
                    i = k;
                }
                if(ciudad2.equals(ciudades.get(k))){
                    j = k;
                }
            }

            distanciasNormal[i][j] = Integer.parseInt(linea.get(2));
            distanciasLluvia[i][j] = Integer.parseInt(linea.get(3));
            distanciasNieve[i][j] = Integer.parseInt(linea.get(4));
            distanciasTormenta[i][j] = Integer.parseInt(linea.get(5));
        }

    }

    
    /**
     * Escoge el clima
     * @param tiempo
     */
    public void escogerTiempo(int tiempo){
        
        switch (tiempo) {
            case 1: //normal
                distancias = distanciasNormal;
                break;

            case 2: //lluvia
                distancias = distanciasLluvia;
                break;

            case 3: //nieve
                distancias = distanciasNieve;
                break;

            case 4: //tormenta
                distancias = distanciasTormenta;
                break;
        
            default:
                break;
        }
    }

    
    /**
     * Calcula la ruta más corta con el algoritmo de FW
     */
    public void calcularRutasCortas(){
                
        //aplicar el algoritmo de Floyd a las matrices.
        this.floydwarshall = new FloydWarshall(distancias, recorridos, tamanioMatriz);
        floydwarshall.CalcularRutas();
        this.distancias = floydwarshall.getDistancias();
        this.recorridos = floydwarshall.getRecorridos();
    }

    /**
     * Encuentra el recorrido luego de calcular la ruta más corta.
     * @param de
     * @param hacia
     * @return el recorrido para llegar con la ruta más corta
     */
    public String encontrarRecorrido(int de, int hacia){
        
        calcularRutasCortas();
        int valor = distancias[de][hacia];
                
        ArrayList<String> pasos = new ArrayList<String>();

        String ciudadIntermedia = recorridos[de][hacia];
        pasos.add(ciudadIntermedia);
        int indexCiudadIntermedia = 0;

        while (! ciudadIntermedia.equals(ciudades.get(hacia))) {
            for (int i = 0; i < ciudades.size(); i++) {
                String ciudad = ciudades.get(i);
                if(ciudadIntermedia.equals(ciudad)){
                    indexCiudadIntermedia = i;
                }
            }
            ciudadIntermedia = recorridos[de][indexCiudadIntermedia];
            pasos.add(ciudadIntermedia);
        }
        
        String resultado = "\nLa ruta más corta es: \nvalor: " + valor + "\n";

        for(int j = pasos.size()-1; j>=0; j--){
            int k = 1;
            resultado = resultado + k + ". " + pasos.get(j) + "\n";
            k++;
        }
        return resultado;
    }

    /**
     * Encuetra el centro del grafo
     * @return el centro del grafo
     */
    public String calcularCentro(){

        //Primero aplicar el algoritmo de Floyd a las matrices.
        calcularRutasCortas();

        //construir unArrayList con los valores máximos
        ArrayList<Integer> maximosColumnas = new ArrayList<Integer>();

        //Encontrar el valor máximo de cada columna
        for(int i = 0; i<tamanioMatriz; i++){

            //Inicializar max en 0 al inicio
            int max = distancias[0][i];

            for(int j = 1; j < distancias[i].length; j++){
                if(distancias[j][i] > max){
                    max = distancias[j][i];
                }
            }
            maximosColumnas.add(max);
        }

        //Ya con los máximos, encontrar el valor mínimo --> centro
        centroMatriz = Collections.min(maximosColumnas);

        for (int i = 0; i< maximosColumnas.size(); i++) {
            if(i == centroMatriz){
                centroMatrizString = ciudades.get(i);
            }
        }

        return centroMatrizString;
    }

    /**
     * Modifica la matriz de acuerdo a la interrupción que diga el usuario
     * @param de
     * @param hacia
     */
    public void interrupcionTrafico(int de, int hacia){
        distanciasNormal[de][hacia] = 10000;
        distanciasLluvia[de][hacia] = 10000;
        distanciasNieve[de][hacia] = 10000;
        distanciasTormenta[de][hacia] = 10000;
        calcularRutasCortas();
    }

    /**
     * Crea una nueva conexión en la matriz, de acuerdo a lo que diga el usuario. 
     * @param de
     * @param hacia
     * @param distanciaNormal
     * @param distanciaLluvia
     * @param distanciaNieve
     * @param distanciaTormenta
     */
    public void crearNuevaConexion(int de, int hacia, int distanciaNormal, int distanciaLluvia, int distanciaNieve, int distanciaTormenta){
        distanciasNormal[de][hacia] = distanciaNormal;
        distanciasLluvia[de][hacia] = distanciaLluvia;
        distanciasNieve[de][hacia] = distanciaNieve;
        distanciasTormenta[de][hacia] = distanciaTormenta;
        calcularRutasCortas();
    }

    /**
     * Modifica el clima entre una ciudad y otra en la matriz
     * @param de
     * @param hacia
     * @param clima
     */
    public void modificarClima(int de, int hacia, int clima){

        switch (clima) {

            case 1: //normal
                distancias[de][hacia] = distanciasNormal[de][hacia];
                break;

            case 2: //lluvia
                distancias[de][hacia] = distanciasLluvia[de][hacia];
                break;

            case 3: //nieve
                distancias[de][hacia] = distanciasNieve[de][hacia];
                break;

            case 4: //tormenta
                distancias[de][hacia] = distanciasTormenta[de][hacia];
                break;
        
            default:
                break;
        }
    }

    /**
     * Muestra las ciudades
     * @return las ciudades enlistadas
     */
    public String mostrarCiudades(){
        String resultado = "";
        int i = 1;
        for (String ciudad : ciudades) {
            resultado = resultado + "\n" + i + ". " + ciudad;
            i++;
        }
        return resultado;
    }

    //Gets y Sets

    /**
     * Get de la matriz distancias
     * @return la matriz distancias
     */
    public int[][] getDistancias() {
        return this.distancias;
    }

    /**
     * Set de la matriz distancias
     * @param distancias
     */
    public void setDistancias(int[][] distancias) {
        this.distancias = distancias;
    }

    /**
     * Get de la matriz recorridos
     * @return la matriz recorridos
     */
    public String[][] getRecorridos() {
        return this.recorridos;
    }

    /**
     * Set de la matriz recorridos
     * @param recorridos
     */
    public void setRecorridos(String[][] recorridos) {
        this.recorridos = recorridos;
    }

    /**
     * Get del tamaño de la matriz
     * @return el tamaño de la matriz
     */
    public int getTamanioMatriz() {
        return this.tamanioMatriz;
    }

    /**
     * Set del tamaño de la matriz
     * @param tamanioMatriz
     */
    public void setTamanioMatriz(int tamanioMatriz) {
        this.tamanioMatriz = tamanioMatriz;
    }


    /**
     * Get de la clase que hace el algoritmo FW
     * @return el objeto de la clase que hace el algoritmo FW
     */
    public FloydWarshall getFloydwarshall() {
        return this.floydwarshall;
    }

    /**
     * Set del objeto de la clase que hace el algoritmo FW
     * @param floydwarshall
     */
    public void setFloydwarshall(FloydWarshall floydwarshall) {
        this.floydwarshall = floydwarshall;
    }

    /**
     * Get de la matriz de distancias con clima normal
     * @return la matriz de distancias con clima normal
     */
    public int[][] getDistanciasNormal() {
        return this.distanciasNormal;
    }

    /**
     * Set de la matriz de distancias con clima normal
     * @param distanciasNormal
     */
    public void setDistanciasNormal(int[][] distanciasNormal) {
        this.distanciasNormal = distanciasNormal;
    }

    /**
     * Get de la matriz de distancias con clima de nieve
     * @return la matriz de distancias con clima de nieve
     */
    public int[][] getDistanciasNieve() {
        return this.distanciasNieve;
    }

    /**
     * set de la matriz de distancias con clima de nieve
     * @param distanciasNieve
     */
    public void setDistanciasNieve(int[][] distanciasNieve) {
        this.distanciasNieve = distanciasNieve;
    }

    /**
     * get de la matriz de distancias con clima de lluvia
     * @return la matriz de distancias con clima de lluvia
     */
    public int[][] getDistanciasLluvia() {
        return this.distanciasLluvia;
    }

    /**
     * set de la matriz de distancias con clima de lluvia
     * @param distanciasLluvia
     */
    public void setDistanciasLluvia(int[][] distanciasLluvia) {
        this.distanciasLluvia = distanciasLluvia;
    }

    /**
     * get de la matriz de distancias con clima de tormenta
     * @return la matriz de distancias con clima de tormenta
     */
    public int[][] getDistanciasTormenta() {
        return this.distanciasTormenta;
    }

    /**
     * set de la matriz de distancias con clima de tormenta
     * @param distanciasTormenta
     */
    public void setDistanciasTormenta(int[][] distanciasTormenta) {
        this.distanciasTormenta = distanciasTormenta;
    }

}


