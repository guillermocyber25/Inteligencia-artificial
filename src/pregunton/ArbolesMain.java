/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pregunton;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author GUILLERMO
 */
public class ArbolesMain {
    public static void main(String[] args)throws IOException {
        File TextFile = new File("bitacora.txt");
        FileWriter TextOut = new FileWriter(TextFile, true);
        Date date = new Date();
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          
        String convertido = fechaHora.format(date);
        TextOut.write("Inicio = "+convertido+"\n");
       TextOut.close();
        Pregunton pregunton = new Pregunton();
        pregunton.creaArbol();
        pregunton.tam();
        int continuar = 1;
        int bandera = 0;
        int numAleatorio = 0;
        
        Scanner sc = new Scanner(System.in);
        while(continuar == 1){
            Random aleatorio = new Random();
            numAleatorio = aleatorio.nextInt(3);
            switch(numAleatorio){
                case 0:{
            pregunton.reiniciaLista();
            pregunton.preguntasTipo1A();
            }
                break;
                case 1:{
            bandera = 0;        
            while(bandera == 0){
            pregunton.reiniciaLista();    
            bandera = pregunton.preguntasTipo1BC();
            
              }
            }
            break;
                case 2:{
            bandera = 0;
            while(bandera == 0){
            pregunton.reiniciaLista();
            bandera = pregunton.preguntasTipo2();
            }
            }
            break;    
        }
            System.out.println("Quieres que te haga otra pregunta");
            System.out.println("1 si");
            System.out.println("2 no");
            continuar = sc.nextInt();
       
        }
        
        /*Arboles red = new Arboles();
          LeeFichero archivo = new LeeFichero();
          ArrayList lista = archivo.textoProcesado();
          
        
        
         Iterator it = lista.iterator();
          while(it.hasNext()){
            //clase objet es la clase padre
            Columna c = (Columna)it.next();//va al siguiente lo recupera y devuelve
            String concepto = c.getConcepto();
            String atributo = c.getAtributo();
            String valorAtributo = c.getValorAtributo();
            String sobreEscribir = c.getSobreEscribir();
            String padre = c.getPadre();
            red.insertarNodo(concepto, atributo, valorAtributo, sobreEscribir, padre);
          }
       
        red.graficarArbol(red);   
        System.out.println("El tamaño es: " + red.obtenerTamaño(red.raiz, red));
        
        ///prueba para recuperar el nodo}
        Nodo nodo = new Nodo();
        nodo = red.buscarRecursivo("Vajilla");
        System.out.println("¿"+nodo.concepto+" "+nodo.atributo+" "+nodo.valorAtributo+"?");
        */
      File TextFile2 = new File("bitacora.txt");
        FileWriter TextOut2 = new FileWriter(TextFile2, true);
        Date date2 = new Date();
        DateFormat fechaHora2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          
        String convertido2 = fechaHora2.format(date2);
        TextOut2.write("Fin = "+convertido2+"\n");
       TextOut2.close();
    }
}
