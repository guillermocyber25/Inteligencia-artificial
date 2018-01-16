/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pregunton;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author GUILLERMO
 */

class Nodo {
   
    String concepto;
    String atributo;
    String valorAtributo;
    String sobreEscribir;
    int numNodo;
    String padreRef;
    ArrayList<Nodo> hijos;

    Nodo(String concept, String atribute, String atributeValue, String sobreescribir, int valorEntero, String P) {
      //System.out.println("El valor del nodo es "+ valorEntero);
      hijos = new ArrayList<>();
      concepto = concept;
      atributo = atribute;
      valorAtributo = atributeValue;
      sobreEscribir = sobreescribir;
      numNodo = valorEntero;
      padreRef = P;
        
    } 

    Nodo() {
       
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
    
  public void insertarNodo(String concepto,String atributo, String valorAtributo, String sobreEscribir, String padre, int valor, String padreRef)
  {      
      raiz = insertarNodo(raiz, concepto, atributo,valorAtributo,sobreEscribir, padre, valor, padreRef);
  }
    
  private Nodo insertarNodo(Nodo nodo, String concepto,String atributo, String valorAtributo, String sobreEscribir, String padre, int valor, String padreRef)
  {              
      if(nodo == null)
          nodo = new Nodo(concepto, atributo, valorAtributo, sobreEscribir, valor, padreRef);                                                                                             
      else 
      {          
          Nodo nodoPadre = buscarRecursivo(padre);
          if(nodoPadre != null)          
              nodoPadre.hijos.add(new Nodo(concepto, atributo, valorAtributo, sobreEscribir, valor, padreRef));
          else
              nodo.hijos.add(new Nodo(concepto, atributo, valorAtributo, sobreEscribir, valor, padreRef));
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
  
 ////busqueda de nodo por valor en la cual se va a generar el random
  
  public Nodo buscarRecursivo2(int data) { 
    return(buscarRecursivo2(raiz, data)); 
  }     
  
  
  Nodo resultado2 = null;
  private Nodo buscarRecursivo2(Nodo node, int valorBuscado) { 
    if (node==null) 
      resultado2 = null;      

    if (valorBuscado == node.numNodo)        
      resultado2 = node; 
          
    for(Nodo tmp: node.hijos)    
      buscarRecursivo2(tmp, valorBuscado); 
    
    return resultado2;
  }      
   
}


