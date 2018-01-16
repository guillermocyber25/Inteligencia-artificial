/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pregunton;

/**
 *
 * @author GUILLERMO
 */
import java.io.*;
import java.util.*;

public class LeeFichero {
    public LeeFichero(){
       
    }
    public ArrayList textoProcesado() throws java.io.IOException{
        ArrayList listaConceptos = new ArrayList();
        String linea;
        String palabra;
        try{
         // Cargamos el buffer con el contenido del archivo
        BufferedReader br = new BufferedReader (new FileReader ("red2.txt"));
        
        
 
        // Leemos la primera linea
         while ((linea = br.readLine()) != null){
 
        //System.out.println ("La primera linea del archivo es: " + linea);
        //System.out.println ("La linea tiene " + linea.length() + " caracteres");
 
        //System.out.println ();
        //System.out.println ("Separando la linea en trozos tenemos las siguientes palabras:");
        //declaracion de variables para recuperar el objeton columnas
        String concepto = "";
        String atributo = "";
        String valorAtributo = "";
        String sobreEscribir = "";
        String padre = "";
        
        int numTokens = 0;
        StringTokenizer st = new StringTokenizer (linea);
 
        // bucle por todas las palabras
        while (st.hasMoreTokens())
        {
            palabra = st.nextToken();
            numTokens++;
            //System.out.println(numTokens);
            //System.out.println(palabra);
            //System.out.println ("    Palabra " + numTokens + " es: " + palabra);
            //case para determinar a donde pertenece en la clase columnas
            switch(numTokens){
                case 1: concepto = palabra;
                    break;
                case 2: atributo = palabra;
                    break;
                case 3: valorAtributo = palabra;
                    break;
                case 4: sobreEscribir = palabra;
                    break;
                case 5: padre = palabra;
                    break;
            }
            
        }
        Columna c = new Columna(concepto, atributo, valorAtributo, sobreEscribir, padre);
        listaConceptos.add(c);
        }
        } 
        catch(Exception e){
         e.printStackTrace();
      }
        return listaConceptos;
      
    }  
}
