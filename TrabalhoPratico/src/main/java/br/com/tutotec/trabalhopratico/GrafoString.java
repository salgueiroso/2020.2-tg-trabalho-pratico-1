/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Implementa a classe grafo de forma que trate cada vértice como uma string.
 * @author Acacio Salgueiro
 */
public class GrafoString extends Grafo<String> {

     /**
     * Realiza a carga do grafo.
     *
     * @param conteudo texto contendo a descrição do grafo conforme descrito no
     * README.md
     */
    @Override
    public void carregarGrafoDoTexto(String conteudo) {
        var linhas = conteudo.lines().collect(Collectors.toList());
        linhas.stream()
                .map((linha) -> Arrays.asList(linha.split(":")).iterator())
                .forEachOrdered((verticeEAdjacentes) -> {

                    var vertice = verticeEAdjacentes.hasNext() ? verticeEAdjacentes.next() : null;

                    if (vertice != null && !vertice.isBlank()) {
                        addVertice(vertice);
                        var adjacentes = verticeEAdjacentes.hasNext() ? verticeEAdjacentes.next() : null;
                        if (adjacentes != null && !adjacentes.isBlank()) {
                            var arrAdjacentes = adjacentes.split(",");
                            for (var adjacente : arrAdjacentes) {
                                addVerticeAdjacente(vertice, adjacente);
                            }
                        }
                    }
                });
    }

}
