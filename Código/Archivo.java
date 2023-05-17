/*
 * Alina Carías (22539) y Ariela Mishaan (22052)
 * Algoritmos y Estructuras de Datos Sección 40
 * Hoja de Trabajo 8
 * 27-03-2023
 * Clase Archivo: escribe y lee un archivo nuevo.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Archivo {
    
    private File archivo;

    /**
     * Consstructor de esta clase
     * @param nombreArchivo
     */
    public Archivo (String nombreArchivo){
        archivo = new File(nombreArchivo);
        try {
            archivo.createNewFile();
        } catch (IOException e) {
            // El archivo ya existe o está en uso
            e.printStackTrace();
        }
    }
    
    
    /** 
     * @param linea
     * Escribe un archivo nuevo con un String
     */
    public void escribirArchivo(String linea){
        try {
            FileWriter miEscritor = new FileWriter(archivo);
            miEscritor.write(linea);
            miEscritor.close();
        } catch (IOException e) {
            // Ocurrió un error en la escritura
            e.printStackTrace();
        }
    }

    
    /** 
     * @param linea
     * Con un String escribe un archivo
     */
    public void escribirArchivo2(String linea){
        try {
            FileWriter miEscritor = new FileWriter(archivo, true);
            miEscritor.write(linea);
            miEscritor.close();
        } catch (IOException e) {
            // Ocurrió un error en la escritura
            e.printStackTrace();
        }
    }

    
    /** 
     * @return ArrayList<String>
     * Lee el archivo y guarda las líneas en un array list
     */
    public ArrayList<String> leerArchivo(){
        Scanner miLector;
        ArrayList<String> lineas = new ArrayList<String>();
        try {
            miLector = new Scanner(archivo);        
            while (miLector.hasNextLine()){
                lineas.add(miLector.nextLine());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lineas;
    }
    
    /** 
     * @return String
     * lee el archivo y guarda las líneas en un string
     */
    public String leerArchivoString(){
        String texto = "";
        try {
            Scanner miLector = new Scanner(archivo);
            while (miLector.hasNextLine()){
                texto = texto + miLector.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texto;
    }

    
    public void eliminarArchivo(){
        archivo.delete();
    }
}
