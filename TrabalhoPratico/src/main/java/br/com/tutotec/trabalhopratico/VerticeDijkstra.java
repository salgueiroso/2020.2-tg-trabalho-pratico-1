/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

/**
 *
 * @author acaci
 */
public class VerticeDijkstra<TVertice> {

    /**
     * A distancia/custo do vertice em relação a origem.
     */
    private long distancia;
    
    /**
     * O vertice parente mais proximo à origem da busca
     */
    private VerticeDijkstra<TVertice> path;
    
    /**
     * Valor do vértice
     */
    private TVertice vertice;

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

    /**
     * @return the path
     */
    public VerticeDijkstra<TVertice> getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(VerticeDijkstra<TVertice> path) {
        this.path = path;
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
    public String toString() {
        return String.format("VerticeDijkstra<%s>", vertice);
    }

}
