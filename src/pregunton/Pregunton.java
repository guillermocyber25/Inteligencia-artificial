/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pregunton;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author GUILLERMO
 */
public class Pregunton {
    public Pregunton()throws IOException{
        
    }
         Arboles red = new Arboles();
         LeeFichero archivo = new LeeFichero();
         ArrayList lista = archivo.textoProcesado();
         Iterator it = lista.iterator();
         int tamaño;
         int valor = 0;
    public void creaArbol(){
         while(it.hasNext()){
            //clase objet es la clase padre
            Columna c = (Columna)it.next();//va al siguiente lo recupera y devuelve
            String concepto = c.getConcepto();
            String atributo = c.getAtributo();
            String valorAtributo = c.getValorAtributo();
            String sobreEscribir = c.getSobreEscribir();
            String padre = c.getPadre();
            red.insertarNodo(concepto, atributo, valorAtributo, sobreEscribir, padre, valor, padre);
            valor++;
          }
       
        red.graficarArbol(red);   
        //System.out.println("El tamaño es: " + red.obtenerTamaño(red.raiz, red));
    }
     public void tam(){
        tamaño = red.obtenerTamaño(red.raiz, red);
        
       // System.out.println("El tamaño es->"+ tamaño);
    }
    public String nodoRandom(){
        Nodo nodo = new Nodo();
        Random aleatorio = new Random();
        int num = aleatorio.nextInt(tamaño);
        //System.out.println("->>"+num);
        nodo = red.buscarRecursivo2(num);
        //System.out.println("Son sus hijos"+nodo.hijos.get(0).concepto);
        //System.out.println("¿"+nodo.concepto+" "+nodo.atributo+" "+nodo.valorAtributo+"?");
        return nodo.concepto;
    }
    ArrayList listaAncestros = new ArrayList();
    
    public void reiniciaLista(){
        listaAncestros.clear();
        valorN = 0;
              
    }
    public ArrayList ancestrosLista(String conceptoI){
        Nodo nodo = new Nodo();
        //System.out.println("***"+conceptoI);
        if(conceptoI.equals("Raíz"))
            return listaAncestros;
        else
        {
        nodo = red.buscarRecursivo(conceptoI);
        Columna c = new Columna(nodo.concepto,nodo.atributo,nodo.valorAtributo,nodo.sobreEscribir,nodo.padreRef);
            //System.out.println(nodo.padreRef);
        
        listaAncestros.add(c);
        }
        
        return ancestrosLista(nodo.padreRef);
        
    }
    int valorN;
    int sizeLista;
    public int regresaAleatorio(){
      Random aleatorio = new Random();
       int num = aleatorio.nextInt(sizeLista);
       return num;
    }
   public void preguntasTipo1A() throws IOException{
       File TextFile = new File("bitacora.txt");
       FileWriter TextOut = new FileWriter(TextFile, true);
       TextOut.write("Creando Pregunta de Tipo 1 \n");
       TextOut.write("Seleccionando un concepto de forma aletoria\n");
       String nombreNodo = nodoRandom();
       TextOut.write("Concepto ="+nombreNodo+"\n");
       TextOut.write("Se hace una busqueda de los ancentros y se ingresa en una lista\n");
       ArrayList listaN = ancestrosLista(nombreNodo);
       TextOut.write("Lista creada = "+listaN+"\n");
       
       sizeLista = listaN.size();
       //esta variable define el valor en donde se encuentra la lista actualmente
       
       //System.out.println(listaN+"OK");
       //System.out.println("Elementos en la lista"+numLista);
       //Secrean dos iteradores para ver si el elemento se sobre escribe
       Iterator it = listaN.iterator();
       Iterator it2 = listaN.iterator();
        
        //este random es para generar las preguntas
        int num = regresaAleatorio();
       //este tercer iterador es para crear la pregunta que se genera de manera aleatoria
       
       
       Columna c2 = (Columna)it2.next();
       
       TextOut.write("Se verifica que el atributo se sobreescriba respecto a la herencia\n");
       // en este ciclo se verifica que el atributo se sobre escriba respecto a la herencia
       while(it.hasNext() && it2.hasNext()){
            Columna c = (Columna)it.next();//va al siguiente lo recupera y devuelve
            if(c.getSobreEscribir().equals("s")){
               String atributo = c.getAtributo();
                //System.out.println("Entra");
               String valorAtributo = c.getValorAtributo();
               c2 = (Columna)it2.next();
               valorN++;
               if(c2.getAtributo().equals(atributo)){
                   //System.out.println("Entra->");
                   Columna col = new Columna(c2.getConcepto(), c.getAtributo(), c.getValorAtributo(), c2.getSobreEscribir(), c2.getPadre());
                   TextOut.write("Conceptos Actualizados"+col+"\n");
                   listaN.set(valorN, col);
               }
            }
            
        }
       
       Iterator it3 = listaN.iterator();
       //System.out.println(listaN);
        int cont = 0;
        while(it3.hasNext()){
           Columna c3 = (Columna)it3.next();
           if(cont == num){
           //se genera la pregunta
               TextOut.write("Se genera la pregunta\n");
               System.out.println();
               System.out.println("¿"+nombreNodo+" "+c3.getAtributo()+" "+c3.getValorAtributo()+"?");
               TextOut.write("¿"+nombreNodo+" "+c3.getAtributo()+" "+c3.getValorAtributo()+"?"+"\n");
               System.out.println();
               System.out.println("1----> Sí");
               System.out.println("2----> No");
               System.out.println();
               Scanner sc = new Scanner(System.in);
               int continuar;
               continuar = sc.nextInt();
               if(continuar == 1){
                   System.out.println();
                   System.out.println("Es correcto");
                   System.out.println();
               }
               else
                   if(continuar == 2){
                       System.out.println();
                       System.out.println("Es incorrecto");
                       System.out.println("La respuesta correcta es No");
                       System.out.println();
                   }
           }
           cont ++;
        }     
        TextOut.write("Pregunta Terminada\n\n");
        TextOut.close();  
   }
   
   
   public int preguntasTipo1BC() throws IOException{
       File TextFile = new File("bitacora.txt");
       FileWriter TextOut = new FileWriter(TextFile, true);
       TextOut.write("Creando Pregunta de Tipo 1 \n");
       TextOut.write("Obteniendo concepto de forma aleatoria\n");
       String nombreNodo = nodoRandom();
       TextOut.write("Concepto = "+nombreNodo+"\n");
       TextOut.write("Se crea una bandera ya que esta pregunta puede ser tipo B o C\n");
       TextOut.write("y puede o no crearse por la aleatoriedad\n");
       int bandera2 = 0;
       TextOut.write("Se buscan los ancestros del concepto\n");
       ArrayList listaN = ancestrosLista(nombreNodo);
       TextOut.write("Ancestros = "+listaN+"\n");
       TextOut.write("Se obtiene el tamño de la lista\n");
       sizeLista = listaN.size();
       TextOut.write("Tamaño lista = "+sizeLista+"\n");
       //esta variable define el valor en donde se encuentra la lista actualmente
       
       //System.out.println(listaN+"OK");
       //System.out.println("Elementos en la lista"+numLista);
       //Secrean dos iteradores para ver si el elemento se sobre escribe
       TextOut.write("Se verifica si el elemento se sobreescribe\n");
       Iterator it = listaN.iterator();
       Iterator it2 = listaN.iterator();
        
        //este random es para generar las preguntas
        int num = regresaAleatorio();
       //este tercer iterador es para crear la pregunta que se genera de manera aleatoria
       TextOut.write("Se genera la pregunta de forma aleatoria\n");
       
       Columna c2 = (Columna)it2.next();
       
       
       // en este ciclo se verifica que el atributo se sobre escriba respecto a la herencia
       while(it.hasNext() && it2.hasNext()){
            Columna c = (Columna)it.next();//va al siguiente lo recupera y devuelve
            if(c.getSobreEscribir().equals("s")){
               String atributo = c.getAtributo();
                //System.out.println("Entra");
               String valorAtributo = c.getValorAtributo();
               c2 = (Columna)it2.next();
               valorN++;
               if(c2.getAtributo().equals(atributo)){
                   //System.out.println("Entra->");
                   Columna col = new Columna(c2.getConcepto(), c.getAtributo(), c.getValorAtributo(), c2.getSobreEscribir(), c2.getPadre());
                   listaN.set(valorN, col);
               }
            }
            
        }
       Iterator it3 = listaN.iterator();
       //System.out.println(listaN);
        int cont = 0;
        Nodo n = new Nodo();
        n = red.buscarRecursivo(nombreNodo);
        if(n.padreRef.equals("Raíz")){
        while(it3.hasNext()){
           Columna c3 = (Columna)it3.next();
           if(cont == num){
           //se genera la pregunta
               System.out.println();
               System.out.println("¿"+nombreNodo+" "+c3.getAtributo()+" "+c3.getValorAtributo()+"?");
               TextOut.write("¿"+nombreNodo+" "+c3.getAtributo()+" "+c3.getValorAtributo()+"?"+"\n");
               
               System.out.println();
               System.out.println("1----> Sí");
               System.out.println("2----> No");
               System.out.println();
               Scanner sc = new Scanner(System.in);
               int continuar;
               continuar = sc.nextInt();
               if(continuar == 1){
                   System.out.println();
                   System.out.println("Es correcto");
                   System.out.println();
               }
               else
                   if(continuar == 2){
                       System.out.println();
                       System.out.println("Es incorrecto");
                       System.out.println("La respuesta correcta es Sí");
                       System.out.println();
                   }
               bandera2 = 1;
           }
           cont ++;
        }
        }
        else{
            n = red.buscarRecursivo(n.padreRef);
            if(n.hijos != null){
            Iterator it4 = n.hijos.iterator();
                String nombreN="";
                String atributoN="";
                String valorAtributoN="";
                int bandera = 0;
            while(it4.hasNext()){
            Nodo n2 = (Nodo) it4.next();
                //System.out.println("Comparaciion "+n.concepto+"=="+n2.concepto);
            if((nombreNodo.compareTo(n2.concepto)!= 0) && (n.padreRef.compareTo('-'+n2.padreRef)!=0)){
                //creo la estructura de una pegunta que se respóndera con no
                 nombreN = nombreNodo;
                 atributoN = n2.atributo;
                 valorAtributoN = n2.valorAtributo;
                bandera = 1;
            
            
           
            }
            }
            if(bandera == 1){
                System.out.println();
            System.out.println("¿"+nombreNodo+" "+atributoN+" "+valorAtributoN+"?");
            TextOut.write("¿"+nombreNodo+" "+atributoN+" "+valorAtributoN+"?"+"\n");
            
            System.out.println();
            System.out.println("1----> Sí");
            System.out.println("2----> No");
            System.out.println();
            Scanner sc = new Scanner(System.in);   
            int continuar;
               continuar = sc.nextInt();
               
               if(continuar == 1){
                       System.out.println();
                       System.out.println("Es incorrecto");
                       System.out.println("La respuesta correcta es No");
                       System.out.println();
               }
                       
               else
                   if(continuar == 2){
                       System.out.println();
                       System.out.println("Es correcto");
                       System.out.println();
                   }
               bandera2 = 1;
            }
        
       }
     }  
        TextOut.write("Pregunta Terminada\n\n");
        TextOut.close();  
        return bandera2;
  }
   
   public int preguntasTipo2() throws IOException{
       Nodo n = new Nodo();
       File TextFile = new File("bitacora.txt");
       FileWriter TextOut = new FileWriter(TextFile, true);
       TextOut.write("Se inicia pregunta de tipo 2\n");
       TextOut.write("Se incia la bandera ya que por aleatoriedad puede que no se genere la pregunta\n");
       int bandera2 = 0;
       String nombreNodo = nodoRandom();
       TextOut.write("Se obtiene un concepto de forma aleatoria\n");
       TextOut.write("Concepto = "+nombreNodo+"\n");
       TextOut.write("Se busca el nodo actual de forma recursiva\n");
       n = red.buscarRecursivo(nombreNodo);
       TextOut.write("Se buscan sus ancestros\n");
       ArrayList listaN2 = ancestrosLista(nombreNodo);
       TextOut.write("Ancestros ="+listaN2+"\n");
       TextOut.write("Se crea una lista para guardar las respuestas falsas\n");
       ArrayList<String> respuestasFalsas = new ArrayList<String>();
       Iterator it = listaN2.iterator();
       TextOut.write("Se verifica si el concepto sobreescribe y se guardan los valores omitiendo la sobreescritura en la lista de respuestas Falsas\n");
       if(n.sobreEscribir.equals("s"))
       {   while(it.hasNext()){
           Columna c = (Columna)it.next();
                   //System.out.println("COL  "+ c);
           if((n.atributo.compareTo(c.getAtributo())==0)&&(n.valorAtributo.compareTo(c.getValorAtributo())!=0)){
               respuestasFalsas.add(c.getAtributo()+" "+c.getValorAtributo());
               //System.out.println("Condicion");
           }        
       }
       }
       int bandera = 1;
       Iterator it4 = n.hijos.iterator();
       
           //String nodoName = nodoRandom();
          if(n.padreRef.equals("Raíz")){
              
              Nodo n2 = (Nodo) it4.next();
              Iterator it5 =n2.hijos.iterator();
              while(it5.hasNext()){
              Nodo n3 = (Nodo) it5.next();
                 if(n.concepto != n3.concepto){
                     respuestasFalsas.add(n3.atributo+" "+n3.valorAtributo);
                 }
              }
               
          }
          else
          {
              Nodo n5 = new Nodo();
              n5 = red.buscarRecursivo(n.padreRef);
              Iterator it6 = n5.hijos.iterator();
              while(it6.hasNext()){
              Nodo n4 = (Nodo) it6.next();
                 if(n.concepto != n4.concepto){
                     respuestasFalsas.add(n4.atributo+" "+n4.valorAtributo);
                 }
              }
          }
        TextOut.write("Se buscan Valores de Atributos en otras ramas para agregar a las respuestas falsas\n");  
       //if(n.padreRef.equals("Raíz"))
       //System.out.println(respuestasFalsas);
        TextOut.write("Se crea otra lista para ingresas las respuestas finales\n");
        
       ArrayList<String> respuestasFinales = new ArrayList<String>();
       int indicadorP = 0;
        Random aleatorio = new Random();
         indicadorP = aleatorio.nextInt(3);
         TextOut.write("Se vrifica que la lista de respuestas falsas temga al menos 2 elementos\n");
       if(respuestasFalsas.size()>=2){
           TextOut.write("Respuestas Falsas ="+respuestasFalsas+"\n");
           TextOut.write("De estas se sellecionan 2\n");
       int tam = respuestasFalsas.size();
       for(int i=0; i<2; i++){
           respuestasFinales.add(respuestasFalsas.get(i));
       }
       respuestasFinales.add(n.atributo+" "+n.valorAtributo);
       TextOut.write("Se genera inserta a la lsita de respuestas finales\n");
       TextOut.write("Respuestas Finales = "+respuestasFinales+"\n");
       TextOut.write("Se genera la pregunta con las respuestas en orden aleatorio\n");
       if(indicadorP == 0){
           System.out.println();
           TextOut.write(n.concepto+"\n");
           
           System.out.println(n.concepto);
           System.out.println();
           System.out.println("I)"+respuestasFinales.get(2));
           TextOut.write("I)"+respuestasFinales.get(2)+"\n");
           
           System.out.println("II)"+respuestasFinales.get(1));
           TextOut.write("II)"+respuestasFinales.get(1)+"\n");
           System.out.println("III)"+respuestasFinales.get(0));
           TextOut.write("III)"+respuestasFinales.get(0)+"\n");
           System.out.println();
           System.out.println("¿Cuál es la respuesta correcta?");
           Scanner sc = new Scanner(System.in);   
           int continuar;
           continuar = sc.nextInt();
           if(continuar == 1){
               System.out.println();
               System.out.println("Tu respuesta es CORRECTA");
           }
           else{
               System.out.println();
               System.out.println("Tu respuesta es INCORRECTA");
               System.out.println("La respuesta correcta es "+respuestasFinales.get(2));
               System.out.println();
           }
           bandera2 = 1;
       }
       if(indicadorP == 1){
           System.out.println();
           System.out.println(n.concepto);
           TextOut.write(n.concepto+"\n");
           
           System.out.println();
           System.out.println("I)"+respuestasFinales.get(0));
           TextOut.write("I)"+respuestasFinales.get(0)+"\n");
           
           System.out.println("II)"+respuestasFinales.get(2));
           TextOut.write("II)"+respuestasFinales.get(2)+"\n");
           
          
           System.out.println("III)"+respuestasFinales.get(1));
           TextOut.write("III)"+respuestasFinales.get(1)+"\n");
           
           System.out.println();
           System.out.println("¿Cuál es la respuesta correcta?");
           
           Scanner sc = new Scanner(System.in);   
           int continuar;
           continuar = sc.nextInt();
           if(continuar == 2){
               System.out.println();
               System.out.println("Tu respuesta es CORRECTA");
               System.out.println();
           }
           else{
               System.out.println();
               System.out.println("Tu respuesta es INCORRECTA");
               System.out.println("La respuesta correcta es "+respuestasFinales.get(2));
               System.out.println();
           }
           bandera2 = 1;
       }
        if(indicadorP == 2){
           System.out.println();
           
           System.out.println(n.concepto);
           TextOut.write(n.concepto+"\n");
           
           System.out.println("I)"+respuestasFinales.get(0));
           TextOut.write("I)"+respuestasFinales.get(0)+"\n");
           System.out.println("II)"+respuestasFinales.get(1));
           TextOut.write("II)"+respuestasFinales.get(1)+"\n");
           System.out.println("III)"+respuestasFinales.get(2));
           System.out.println();
           TextOut.write("III)"+respuestasFinales.get(2)+"\n");
           System.out.println("¿Cuál es la respuesta correcta?");
           Scanner sc = new Scanner(System.in);   
           int continuar;
           continuar = sc.nextInt();
           if(continuar == 3){
               System.out.println();
               System.out.println("Tu respuesta es CORRECTA");
               System.out.println();
           }
           else{
                System.out.println();   
               System.out.println("Tu respuesta es INCORRECTA");
               System.out.println("La respuesta correcta es "+respuestasFinales.get(2));
               System.out.println();
           }
           bandera2 = 1;
       }
       }
       //System.out.println(respuestasFinales);
       
       TextOut.write("Pregunta Terminada\n\n");
        TextOut.close(); 
       return bandera2;
   }
}