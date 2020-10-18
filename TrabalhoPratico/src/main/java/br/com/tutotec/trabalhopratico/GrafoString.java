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
 *
 * @author acaci
 */
public class GrafoString extends Grafo<String> {

    @Override
    public void carregarGrafoDoTexto(String conteudo) {
        var linhas = conteudo.lines().collect(Collectors.toList());
        linhas.stream()
                .map((linha) -> Arrays.asList(linha.split(":")).iterator())
                .forEachOrdered((verticeEAdjascentes) -> {

                    var vertice = verticeEAdjascentes.hasNext() ? verticeEAdjascentes.next() : null;

                    if (vertice != null && !vertice.isBlank()) {
                        addVertice(vertice);
                        var adjascentes = verticeEAdjascentes.hasNext() ? verticeEAdjascentes.next() : null;
                        if (adjascentes != null && !adjascentes.isBlank()) {
                            var arrAdjascentes = adjascentes.split(",");
                            for (var adjascente : arrAdjascentes) {
                                addVerticeAdjascente(vertice, adjascente);
                            }
                        }
                    }
                });
    }

}
