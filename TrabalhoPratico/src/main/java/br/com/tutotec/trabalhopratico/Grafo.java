/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 *
 * @author acaci
 * @param <TVertice>
 */
public abstract class Grafo<TVertice> implements IGrafo<TVertice> {

    protected LinkedHashMap<Vertice<TVertice>, LinkedList<Vertice<TVertice>>> grafo;

    public Grafo() {
        grafo = new LinkedHashMap<>();
    }

    public abstract void carregarGrafoDoTexto(String conteudo);

    @Override
    public List<TVertice> getAdjacentes(TVertice vertice) {
        var vertices = grafo.getOrDefault(getVertice(vertice), new LinkedList<>());
        return vertices
                .stream()
                .map(v -> v.getVertice())
                .collect(Collectors.toList());
    }

    @Override
    public boolean ehRegular() {

        var primeiroVertice = grafo.keySet().stream().findFirst().orElse(null);

        if (primeiroVertice == null) {
            return false;
        }

        return grafo
                .keySet()
                .stream()
                .allMatch(v -> grafo.get(v).size() == grafo.get(primeiroVertice).size());
    }

    @Override
    public boolean ehCompleto() {
        var todosVertices = grafo.keySet();
        return todosVertices
                .stream()
                .allMatch(v -> grafo.get(v)
                .containsAll(todosVertices.stream()
                        .filter(v2 -> v2 != v)
                        .collect(Collectors.toList())));
    }

    @Override
    public boolean ehConexo() {
        var ks = grafo.keySet();
        return ks.stream()
                .allMatch(vOrigem -> ks.stream()
                .allMatch(vAlvo -> !buscaProfundidade(vOrigem, vAlvo).empty()));
    }

    private Stack<Vertice<TVertice>> buscaProfundidade(Vertice<TVertice> origem) {
        return buscaProfundidade(origem, null);
    }

    private Stack<Vertice<TVertice>> buscaProfundidade(Vertice<TVertice> origem, Vertice<TVertice> alvo) {

        grafo.keySet()
                .stream()
                .forEach(v -> v.setVisitado(false));

        var v = origem;

        var pilha = new Stack<Vertice<TVertice>>();
        v.setVisitado(true);
        pilha.add(v);

        if (alvo != null && origem == alvo) {
            return pilha;
        }

        while (!pilha.empty()) {
            Vertice<TVertice> w;
            while ((w = getAdjacsenteNaoVisitado(pilha.peek())) != null) {
                w.setVisitado(true);
                pilha.push(w);
                if (alvo != null && w == alvo) {
                    return pilha;
                }
            }
            pilha.pop();
        }
        return pilha;
    }

    private Vertice<TVertice> getAdjacsenteNaoVisitado(Vertice<TVertice> vertice) {
        var result = grafo.get(vertice).stream().filter(x -> !x.isVisitado()).findFirst().orElse(null);
        return result;
    }

    public Vertice<TVertice> addVertice(TVertice vertice) {
        var verticeKey = grafo
                .keySet()
                .stream()
                .filter(v -> v.getVertice().equals(vertice))
                .findFirst()
                .orElse(null);

        if (verticeKey == null) {
            verticeKey = new Vertice<>(vertice);
            grafo.put(verticeKey, new LinkedList<>());
        }

        return verticeKey;
    }

    public void addVerticeAdjascente(TVertice vertice, TVertice verticeAdjascente) {
        var verticeAdicionado = addVertice(vertice);
        var adjascenteAdicionado = addVertice(verticeAdjascente);

        var adjascentes = grafo.get(verticeAdicionado);
        if (!adjascentes.contains(adjascenteAdicionado)) {
            adjascentes.add(adjascenteAdicionado);
        }
    }

    public void print() {
        for (var key : grafo.keySet()) {

            var adjascentes = grafo.get(key)
                    .stream()
                    .map(v -> v.getVertice().toString())
                    .collect(Collectors.joining("->"));

            System.out.println(String.format("%s\t: %s", key.getVertice().toString(), adjascentes));
        }
    }

    private Vertice<TVertice> getVertice(TVertice vertice) {
        return grafo
                .keySet()
                .stream()
                .filter(v -> v.getVertice().equals(vertice))
                .findFirst()
                .orElse(null);
    }

}
