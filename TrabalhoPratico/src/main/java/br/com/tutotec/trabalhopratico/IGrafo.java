/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.util.List;

/**
 * Interface com os metodos solicitados na atividade.
 *
 * @author Acacio Salgueiro
 * @param <TVertice>
 */
public interface IGrafo<TVertice> {

    /**
     * Obtém uma lista de adjacentes de um vértice passado como parâmetro
     *
     * @param vertice o vértice a verificar
     * @return lista Uma lista com os vértices adjacentes
     */
    List<TVertice> getAdjacentes(TVertice vertice);

    /**
     * Verifica se um determinado grafo é regular ou não.
     *
     * @return True se for regular. False caso contrario.
     */
    boolean ehRegular();

    /**
     * Verifica se um determinado grafo é completo ou não.
     *
     * @return True se for regular. False caso contrario.
     */
    boolean ehCompleto();

    /**
     * Verifica se um determinado grafo é conexo ou não. Utiliza busca em
     * profundidade para fazer essa verificação.
     *
     * @return True se for regular. False caso contrario.
     */
    boolean ehConexo();
}
