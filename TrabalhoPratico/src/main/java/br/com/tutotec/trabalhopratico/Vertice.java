/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

/**
 * Modelo base de cada vertice. Esta classe armazena flags basicos relacionada a cada vertice.
 * @author Acacio Salgueiro
 * @param <TVertice>
 */
public class Vertice<TVertice> {
    
    /**
     * Valor do vértice armazenado
     */
    private TVertice vertice;
    
    /**
     * Flag com o estado de visitado
     */
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

    
    /**
     * Compara a igualdade a partir do valor do vértice armazenado
     * @param obj Outro vértice a comparar
     * @return True se forem iguais. False caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        return vertice.equals(((Vertice)obj).getVertice());
    }

    /**
     * Obtem o hash a partir do valor do vértice armazenado
     * @return o hash
     */
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
