/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Francisco Daniel Hernández Rodríguez
 * Institución: ServIT México
 * Descripción: Implementación básica de un árbol N-ario
 */

class Nodo {
    
    int valor;    
    ArrayList<Nodo> hijos;

    Nodo(int newData) {
      hijos = new ArrayList<>();
      valor = newData;
    } 
  } 

public class Arboles {        

    /**
     * @param args the command line arguments
     */      
    Nodo raiz; 
    GraphViz graficador = new GraphViz();      
        
  public Arboles() {
    raiz = null; 
    graficador.addln(graficador.start_graph());
  } 
    
  public Nodo buscarRecursivo(int data) { 
    return(buscarRecursivo(raiz, data)); 
  }     
  
  //Sobre carga de funciones/métodos
  Nodo resultado = null;
  private Nodo buscarRecursivo(Nodo node, int valorBuscado) { 
    if (node==null) 
      resultado = null;      

    if (valorBuscado == node.valor)        
      resultado = node; 
          
    for(Nodo tmp: node.hijos)    
      buscarRecursivo(tmp, valorBuscado); 
    
    return resultado;
  }      
    
  public void insertarNodo(int valor, int padre)
  {      
      raiz = insertarNodo(raiz, valor, padre);
  }
    
  private Nodo insertarNodo(Nodo nodo, int valor, int padre)
  {              
      if(nodo == null)
          nodo = new Nodo(valor);                                                                                             
      else 
      {          
          Nodo nodoPadre = buscarRecursivo(padre);
          if(nodoPadre != null)          
              nodoPadre.hijos.add(new Nodo(valor));
          else
              nodo.hijos.add(new Nodo(valor));
      }
      return nodo;
  }
  
  //Devolvemos el numero de nodo, buscandolo por valor
  public int buscarNodo(int valorNodo)
  {      
      return nodosVisitados.indexOf(valorNodo);
  }
  
  public void finalizarGrafica()
  {
      System.out.println("\n");
      graficador.addln(graficador.end_graph());
          System.out.println(graficador.getDotSource());
          
          String type = "gif";
          File out = new File("arbol." + type);
          graficador.writeGraphToFile(graficador.getGraph(graficador.getDotSource(), type), out);
  }
  
  static int contadorNodos = 0;
  static int contadorNulos = 0;
  static ArrayList<Integer> nodosVisitados = new ArrayList<>();
  private void encontrarNodos(Nodo nodo, Arboles arbol)
  {
      if(nodo != null)
      {
          //System.out.println("\nEstoy en el nodo " + nodo.valor + " ");
          String nulo = "null";
          //Creación del nodo
          graficador.addln(String.format(
                  "%d [ label=<%d> ]", contadorNodos, nodo.valor));                         
          nodosVisitados.add(nodo.valor);  
          //System.out.println("Nuevo nodo registrado: " + nodo.valor + " con indice: " + contadorNodos);
          contadorNodos++;
          
          for(Nodo tmp: nodo.hijos)
          {
            if(tmp == null)
            {
                nulo = "null" + contadorNulos;
                contadorNulos++;
                graficador.addln(String.format("%s[shape=point]", nulo));                                       
            }                        
            encontrarNodos(tmp, arbol);                                            
          }
      }
  }      
  static int nuevoContadorNulos = 0;
  public void asignarPadreHijo(Nodo nodo, Arboles arbol)
  {
      if(nodo != null)
      {         
          for(Nodo tmp: nodo.hijos)
          {
              if(tmp != null)
                  //Agregamos el hijo izquierdo a la gráfica
                  graficador.addln(String.format(
                      "%d -> %d", buscarNodo(nodo.valor), buscarNodo(tmp.valor)));   
              else
                  graficador.addln(String.format(
                      "%d -> %s", buscarNodo(nodo.valor), "null" + nuevoContadorNulos++));                                                                
              asignarPadreHijo(tmp, arbol);             
          }
      }
  }
  
  public void graficarArbol(Arboles arbol)
  {            
      graficador.addln("ordering = out");  
      encontrarNodos(raiz, arbol);                 
      asignarPadreHijo(raiz, arbol);
      finalizarGrafica();
  }

  static int tamaño = 0;
  public int obtenerTamaño(Nodo nodo, Arboles arbol)
  {
      if(nodo != null)
      {
          tamaño++;
          for(Nodo tmp: nodo.hijos)
            obtenerTamaño(tmp, arbol);                    
      }
      return tamaño;
  }
  
 
   
}


//  public void eliminarNodo(int valor) //funcion para árbol binario
//  {
//      Nodo pivote = raiz;
//      Nodo tmp, nodoModificar, previo = null;
//      
//      //Inicia la busqueda del nodo a eliminar
//      while(pivote != null && pivote.valor != valor)
//      {
//          previo = pivote;
//          if(pivote.valor < valor)
//              pivote = pivote.derecho;
//          else
//              pivote = pivote.izquierdo;
//      }
//      nodoModificar = pivote;
//      
//      if(pivote != null)//Si se encontro el nodo
//      {
//          if(nodoModificar.derecho == null)
//              nodoModificar = nodoModificar.izquierdo;
//          else if(nodoModificar.izquierdo == null)
//              nodoModificar = nodoModificar.derecho;
//          else //Tiene dos hijos
//          {
//              tmp = nodoModificar.izquierdo;
//              while(tmp.derecho != null)              
//                  tmp = tmp.derecho;
//              tmp.derecho = nodoModificar.derecho;
//              nodoModificar = nodoModificar.izquierdo;
//          }
//          if (pivote == raiz)
//              raiz = nodoModificar;
//          else if(previo.izquierdo == pivote)
//              previo.izquierdo = nodoModificar;
//          else previo.derecho = nodoModificar;
//      }      
//  }









/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Francisco Daniel Hernández Rodríguez
 * Institución: ServIT México
 * Descripción: Implementación básica de un árbol N-ario
 */

class Nodo {
    //Se cambia valor por concepto    
    String concepto;    
    ArrayList<Nodo> hijos;

    Nodo(String newData) {
      hijos = new ArrayList<>();
      concepto = newData;
    } 
  } 

public class Arboles {        

    /**
     * @param args the command line arguments
     */      
    Nodo raiz; 
    GraphViz graficador = new GraphViz();      
        
  public Arboles() {
    raiz = null; 
    graficador.addln(graficador.start_graph());
  } 
    
  public Nodo buscarRecursivo(String data) { 
    return(buscarRecursivo(raiz, data)); 
  }     
  
  //Sobre carga de funciones/métodos
  Nodo resultado = null;
  private Nodo buscarRecursivo(Nodo node, String valorBuscado) { 
    if (node==null) 
      resultado = null;      

    if (valorBuscado.equals(node.concepto))        
      resultado = node; 
          
    for(Nodo tmp: node.hijos)    
      buscarRecursivo(tmp, valorBuscado); 
    
    return resultado;
  }      
    
  public void insertarNodo(String concepto, String padre)
  {      
      raiz = insertarNodo(raiz, concepto, padre);
  }
    
  private Nodo insertarNodo(Nodo nodo, String valor, String padre)
  {              
      if(nodo == null)
          nodo = new Nodo(valor);                                                                                             
      else 
      {          
          Nodo nodoPadre = buscarRecursivo(padre);
          if(nodoPadre != null)          
              nodoPadre.hijos.add(new Nodo(valor));
          else
              nodo.hijos.add(new Nodo(valor));
      }
      return nodo;
  }
  
  //Devolvemos el numero de nodo, buscandolo por valor
  public int buscarNodo(String valorNodo)
  {      
      return nodosVisitados.indexOf(valorNodo);
  }
  
  public void finalizarGrafica()
  {
      System.out.println("\n");
      graficador.addln(graficador.end_graph());
          System.out.println(graficador.getDotSource());
          
          String type = "gif";
          File out = new File("arbol." + type);
          graficador.writeGraphToFile(graficador.getGraph(graficador.getDotSource(), type), out);
  }
  
  static int contadorNodos = 0;
  static int contadorNulos = 0;
  static ArrayList<String> nodosVisitados = new ArrayList<>();
  private void encontrarNodos(Nodo nodo, Arboles arbol)
  {
      if(nodo != null)
      {
          //System.out.println("\nEstoy en el nodo " + nodo.valor + " ");
          String nulo = "null";
          //Creación del nodo
          graficador.addln(String.format(
                  "%d [ label=<%s> ]", contadorNodos, nodo.concepto));                         
          nodosVisitados.add(nodo.concepto);  
          //System.out.println("Nuevo nodo registrado: " + nodo.valor + " con indice: " + contadorNodos);
          contadorNodos++;
          
          for(Nodo tmp: nodo.hijos)
          {
            if(tmp == null)
            {
                nulo = "null" + contadorNulos;
                contadorNulos++;
                graficador.addln(String.format("%s[shape=point]", nulo));                                       
            }                        
            encontrarNodos(tmp, arbol);                                            
          }
      }
  }      
  static int nuevoContadorNulos = 0;
  public void asignarPadreHijo(Nodo nodo, Arboles arbol)
  {
      if(nodo != null)
      {         
          for(Nodo tmp: nodo.hijos)
          {
              if(tmp != null)
                  //Agregamos el hijo izquierdo a la gráfica
                  graficador.addln(String.format(
                      "%s -> %s", buscarNodo(nodo.concepto), buscarNodo(tmp.concepto)));   
              else
                  graficador.addln(String.format(
                      "%s -> %s", buscarNodo(nodo.concepto), "null" + nuevoContadorNulos++));                                                                
              asignarPadreHijo(tmp, arbol);             
          }
      }
  }
  
  public void graficarArbol(Arboles arbol)
  {            
      graficador.addln("ordering = out");  
      encontrarNodos(raiz, arbol);                 
      asignarPadreHijo(raiz, arbol);
      finalizarGrafica();
  }

  static int tamaño = 0;
  public int obtenerTamaño(Nodo nodo, Arboles arbol)
  {
      if(nodo != null)
      {
          tamaño++;
          for(Nodo tmp: nodo.hijos)
            obtenerTamaño(tmp, arbol);                    
      }
      return tamaño;
  }
  
 
   
}


//  public void eliminarNodo(int valor) //funcion para árbol binario
//  {
//      Nodo pivote = raiz;
//      Nodo tmp, nodoModificar, previo = null;
//      
//      //Inicia la busqueda del nodo a eliminar
//      while(pivote != null && pivote.valor != valor)
//      {
//          previo = pivote;
//          if(pivote.valor < valor)
//              pivote = pivote.derecho;
//          else
//              pivote = pivote.izquierdo;
//      }
//      nodoModificar = pivote;
//      
//      if(pivote != null)//Si se encontro el nodo
//      {
//          if(nodoModificar.derecho == null)
//              nodoModificar = nodoModificar.izquierdo;
//          else if(nodoModificar.izquierdo == null)
//              nodoModificar = nodoModificar.derecho;
//          else //Tiene dos hijos
//          {
//              tmp = nodoModificar.izquierdo;
//              while(tmp.derecho != null)              
//                  tmp = tmp.derecho;
//              tmp.derecho = nodoModificar.derecho;
//              nodoModificar = nodoModificar.izquierdo;
//          }
//          if (pivote == raiz)
//              raiz = nodoModificar;
//          else if(previo.izquierdo == pivote)
//              previo.izquierdo = nodoModificar;
//          else previo.derecho = nodoModificar;
//      }      
//  }