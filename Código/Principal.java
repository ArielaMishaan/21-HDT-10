import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        
        Archivo logistica = new Archivo("./logistica.txt");
        ArrayList<String> lineasDatos = logistica.leerArchivo();
        Scanner teclado = new Scanner(System.in);
        int tamanioMatriz = 0;

        boolean opcionIncorrecta = true;
        while(opcionIncorrecta)
            try {
                System.out.println("\n¿Cuántas ciudades tiene su archivo?");
                tamanioMatriz = teclado.nextInt();
                teclado.nextLine();
                opcionIncorrecta = false;

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("\nIngrese algo válido.");
            }
        
        Controladora controladora = new Controladora(tamanioMatriz);
        controladora.leerArchivo(lineasDatos);
        controladora.escogerTiempo(1);

        int opcion = 0;

        String menu = "\n\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°";
        menu = menu + "\nCalculadora de rutas más cortas\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°";
        menu = menu + "\n\n1. Calcular ruta más corta de X hacia Y.\n2. Encontrar centro del grafo.\n3. Modificar el grafo \n4. Salir";

        while (opcion>= 0 && opcion < 4) {
            
            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {

                    case 1://Calcular la ruta más corta de X hacia Y
                        
                    System.out.println(controladora.mostrarCiudades());
                        System.out.println("\n¿Desde qué ciudad quiere ir?");
                        int desde = 0;
                        int hacia = 0;

                        boolean opcionIncorrecta2 = true;
                        while(opcionIncorrecta2){
                            
                            desde = teclado.nextInt();
                            if(desde < tamanioMatriz){
                                opcionIncorrecta2 = false;
                            }
                        }
                        
                        System.out.println("\n¿Hacia qué ciudad quiere ir?");

                        boolean opcionIncorrecta3 = true;
                        while(opcionIncorrecta3){
                            
                            hacia = teclado.nextInt();
                            if(hacia < tamanioMatriz){
                                opcionIncorrecta3 = false;
                            }
                        }
                        System.out.println(controladora.encontrarRecorrido(desde-1, hacia-1));
                        break;

                    case 2: //Encontrar el centro del grafo
                        
                        System.out.println("\nEl centro del grafo es: " + controladora.calcularCentro());
                        break;

                    case 3: //Modificar el grafo

                        System.out.println("\n1. Colocar interrupción de tráfico entre dos ciudades.\n2. Establecer nueva conexión entre 2 ciudades. \n3. Indicar nuevo clima entre un par de ciudades.");
                        int opcion2 = teclado.nextInt();

                        switch (opcion2) {
                            case 1://interrupción de tráfico
                                System.out.println(controladora.mostrarCiudades());
                                System.out.println("\n¿Desde qué ciudad?");
                                int desde2 = 0;
                                int hacia2 = 0;
        
                                boolean opcionIncorrecta4 = true;
                                while(opcionIncorrecta4){
                                    
                                    desde2 = teclado.nextInt();
                                    if(desde2 < tamanioMatriz){
                                        opcionIncorrecta4 = false;
                                    }
                                }
                                
                                System.out.println("\n¿Hacia qué ciudad?");
        
                                boolean opcionIncorrecta5 = true;
                                while(opcionIncorrecta5){
                                    
                                    hacia2 = teclado.nextInt();
                                    if(hacia2 < tamanioMatriz){
                                        opcionIncorrecta5 = false;
                                    }
                                }
                                controladora.interrupcionTrafico(desde2-1, hacia2-1);

                                break;

                            case 2: //nueva conexión

                                System.out.println(controladora.mostrarCiudades());
                                System.out.println("\n¿Desde qué ciudad?");
                                int desde3 = 0;
                                int hacia3 = 0;
        
                                boolean opcionIncorrecta6 = true;
                                while(opcionIncorrecta6){
                                    
                                    desde3 = teclado.nextInt();
                                    if(desde3 < tamanioMatriz){
                                        opcionIncorrecta6 = false;
                                    }
                                }
                                
                                System.out.println("\n¿Hacia qué ciudad?");
        
                                boolean opcionIncorrecta7 = true;
                                while(opcionIncorrecta7){
                                    
                                    hacia3 = teclado.nextInt();
                                    if(hacia3 < tamanioMatriz){
                                        opcionIncorrecta7 = false;
                                    }
                                }

                                System.out.println("\n¿Cuál es la distancia con clima normal?");
                                int distanciaNormal = teclado.nextInt();
                                System.out.println("\n¿Cuál es la distancia con lluvia?");
                                int distanciaLluvia = teclado.nextInt();
                                System.out.println("\n¿Cuál es la distancia con nieve?");
                                int distanciaNieve = teclado.nextInt();
                                System.out.println("\n¿Cuál es la distancia con tormenta?");
                                int distanciaTormenta = teclado.nextInt();

                                controladora.crearNuevaConexion(desde3 -1, hacia3 -1, distanciaNormal, distanciaLluvia, distanciaNieve, distanciaTormenta);                               
                                break;

                            case 3: //nuevo clima

                                System.out.println(controladora.mostrarCiudades());
                                System.out.println("\n¿Desde qué ciudad?");
                                int desde4 = 0;
                                int hacia4 = 0;
        
                                boolean opcionIncorrecta8 = true;
                                while(opcionIncorrecta8){
                                    
                                    desde4 = teclado.nextInt();
                                    if(desde4 < tamanioMatriz){
                                        opcionIncorrecta8 = false;
                                    }
                                }
                                
                                System.out.println("\n¿Hacia qué ciudad?");
        
                                boolean opcionIncorrecta9 = true;
                                while(opcionIncorrecta9){
                                    
                                    hacia4 = teclado.nextInt();
                                    if(hacia4 < tamanioMatriz){
                                        opcionIncorrecta9 = false;
                                    }
                                }

                                System.out.println("\n¿Cuál es el clima?");
                                int clima = teclado.nextInt();

                                controladora.modificarClima(desde4 -1, hacia4 -1, clima);
                                break;

                            default:
                                break;
                        }
                        
                        break;

                    case 4: //salir
                        System.out.println("\nGracias por utilizar el servicio.");
                        break;
                
                    default:
                        break;
                }

            } catch (Exception e) {
                teclado.nextLine();
                System.out.println("\nEntrada inválida.");
                System.out.println();
                // TODO: handle exception
            }
        }

    }
}
