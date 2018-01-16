/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pregunton;

import java.io.IOException;
import java.io.*;
//Biblioteca para crar la bitacora
import java.io.FileWriter;
import java.util.*;
/**
 *
 * @author GUILLERMO
 */
public class ArchivoMain {
    public static void main(String[] args) throws IOException {
        
        LeeFichero archivo = new LeeFichero();
        ArrayList lista = archivo.textoProcesado();
        System.out.println(lista);
        System.out.println("------------------------------------");
        Iterator it = lista.iterator();
         while(it.hasNext()){
            //clase objet es la clase padre
            Columna c = (Columna)it.next();//va al siguiente lo recupera y devuelve
            System.out.println(c);
        }
         
    }
}
