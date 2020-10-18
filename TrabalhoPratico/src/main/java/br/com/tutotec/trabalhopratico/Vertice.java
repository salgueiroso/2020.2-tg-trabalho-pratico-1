/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.util.zip.CRC32;

/**
 *
 * @author acaci
 * @param <TVertice>
 */
public class Vertice<TVertice> {
    private TVertice vertice;
    private boolean visitado;
    
    public Vertice(TVertice vertice){
        this.vertice = vertice;
    }

    /**
     * @return the vertice
     */
    public TVertice getVertice() {
        return vertice;
    }

    /**
     * @param vertice the vertice to set
     */
    public void setVertice(TVertice vertice) {
        this.vertice = vertice;
    }

    @Override
    public boolean equals(Object obj) {
        return vertice.equals(((Vertice)obj).getVertice());
    }

    @Override
    public int hashCode() {
        return vertice.hashCode();
    }

    @Override
    public String toString() {
        return String.format("vertice{%s}", vertice);
    }

    /**
     * @return the visitado
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     * @param visitado the visitado to set
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}
