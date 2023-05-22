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

}