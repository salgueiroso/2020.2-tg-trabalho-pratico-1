/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

/**
 * Modelo base de cada vertice. Esta classe armazena flags basicos relacionada a
 * cada vertice.
 *
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

    /**
     * Valor da distancia para chegar até este vértice.
     */
    private long distancia;

    /**
     * Vertice parente.
     */
    private Vertice<TVertice> path;

    public Vertice(TVertice vertice) {
        this.vertice = vertice;
        distancia = 0;
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
     *
     * @param obj Outro vértice a comparar
     * @return True se forem iguais. False caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        return vertice.equals(((Vertice) obj).getVertice());
    }

    /**
     * Obtem o hash a partir do valor do vértice armazenado
     *
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

    /**
     * @return the distancia
     */
    public long getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(long distancia) {
        this.distancia = distancia;
    }

    public void redefinir() {
        this.distancia = 0;
        this.visitado = false;
        this.path = null;
    }

    /**
     * @return the path
     */
    public Vertice<TVertice> getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(Vertice<TVertice> path) {
        this.path = path;
    }

}
