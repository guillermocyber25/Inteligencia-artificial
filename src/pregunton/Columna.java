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
public class Columna {
    private String concepto;
    private String atributo;
    private String valorAtributo;
    private String sobreEscribir;
    private String padre;
    public Columna(){};
    public Columna(String concepto, String atributo, String valorAtributo, String sobreEscribir, String padre) {
        this.concepto = concepto;
        this.atributo = atributo;
        this.valorAtributo = valorAtributo;
        this.sobreEscribir = sobreEscribir;
        this.padre = padre;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getValorAtributo() {
        return valorAtributo;
    }

    public void setValorAtributo(String valorAtributo) {
        this.valorAtributo = valorAtributo;
    }

    public String getSobreEscribir() {
        return sobreEscribir;
    }

    public void setSobreEscribir(String sobreEscribir) {
        this.sobreEscribir = sobreEscribir;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    @Override
    public String toString() {
        return "Columna{" + "concepto=" + concepto + ", atributo=" + atributo + ", valorAtributo=" + valorAtributo + ", sobreEscribir=" + sobreEscribir + ", padre=" + padre + '}';
    }
    
    
}
