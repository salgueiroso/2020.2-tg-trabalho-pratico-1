/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Classe abstrata que implementa todos os metodos básicos relacionados aos
 * grafos.
 *
 * @author Acacio Salgueiro
 * @param <TVertice>
 */
public abstract class Grafo<TVertice> implements IGrafo<TVertice> {

    /**
     * Mapa de hash contendo todos os vértices e seus adjacentes.
     */
    protected LinkedHashMap<Vertice<TVertice>, LinkedList<Vertice<TVertice>>> grafo;

    /**
     * Cria uma nova instancia para analisar um grafo.
     */
    public Grafo() {
        grafo = new LinkedHashMap<>();
    }

    /**
     * Método abstrato para realizar a carga do grafo
     *
     * @param conteudo texto contendo a descrição do grafo conforme descrito no
     * README.md
     */
    public abstract void carregarGrafoDoTexto(String conteudo);

    /**
     * Obtém uma lista de adjacentes de um vértice passado como parâmetro
     *
     * @param vertice o vértice a verificar
     * @return lista Uma lista com os vértices adjacentes
     */
    @Override
    public List<TVertice> getAdjacentes(TVertice vertice) {
        var vertices = grafo.getOrDefault(getVertice(vertice), new LinkedList<>());
        return vertices
                .stream()
                .map(v -> v.getVertice())
                .collect(Collectors.toList());
    }

    /**
     * Verifica se um determinado grafo é regular ou não.
     *
     * @return True se for regular. False caso contrario.
     */
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

    /**
     * Verifica se um determinado grafo é completo ou não.
     *
     *
     * @return True se for regular. False caso contrario.
     */
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

    /**
     * Verifica se um determinado grafo é conexo ou não. Utiliza busca em
     * profundidade para fazer essa verificação.
     *
     * @return True se for regular. False caso contrario.
     */
    @Override
    public boolean ehConexo() {
        var ks = grafo.keySet();
        return ks.stream()
                .allMatch(vOrigem -> ks.stream()
                .allMatch(vAlvo -> !buscaProfundidade(vOrigem, vAlvo).empty()));
    }

    /**
     * Adiciona vértices adjacentes no vértice informado
     * @param vertice Vértice base
     * @param verticeAdjacente Vértice que será adjacente ao vértice base.
     */
    public void addVerticeAdjacente(TVertice vertice, TVertice verticeAdjacente) {
        var verticeAdicionado = addVertice(vertice);
        var adjacenteAdicionado = addVertice(verticeAdjacente);

        var adjacentes = grafo.get(verticeAdicionado);
        if (!adjacentes.contains(adjacenteAdicionado)) {
            adjacentes.add(adjacenteAdicionado);
        }
    }

    /**
     * Imprime o grafo no formato de lista de adjacencia.
     */
    public void print() {
        for (var key : grafo.keySet()) {

            var adjacentes = grafo.get(key)
                    .stream()
                    .map(v -> v.getVertice().toString())
                    .collect(Collectors.joining("->"));

            System.out.println(String.format("%s\t: %s", key.getVertice().toString(), adjacentes));
        }
    }

    /**
     * Adiciona um vertice no grafo
     * @param vertice o vertice a ser adicionado
     * @return Retorna um vértice encapsulado para manipulação de estados
     */
    protected Vertice<TVertice> addVertice(TVertice vertice) {
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

    /**
     * Realiza uma busca em profundidado no grafo
     * @param origem Vértice inicial / raiz
     * @return Retorna uma pilha com o caminho encontrado ou uma pilha vazia caso não exista um caminho
     */
    private Stack<Vertice<TVertice>> buscaProfundidade(Vertice<TVertice> origem) {
        return buscaProfundidade(origem, null);
    }

    /**
     * Realiza uma busca em profundidado no grafo
     * @param origem Vértice inicial / raiz
     * @param alvo Vértice alvo
     * @return Retorna uma pilha com o caminho encontrado ou uma pilha vazia caso não exista um caminho
     */
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

    /**
     * Obtém um vértice não visitado, adjacente ao vértice informado.
     * @param vertice Vértice base para retorno dos adjascentes
     * @return O próximo vertice adjacente não visitado ou nulo caso não exista
     */
    private Vertice<TVertice> getAdjacsenteNaoVisitado(Vertice<TVertice> vertice) {
        var result = grafo.get(vertice).stream().filter(x -> !x.isVisitado()).findFirst().orElse(null);
        return result;
    }

    /**
     * Obtém o vértice encapsulado a partir do valor do vértice informado.
     * @param vertice O valor do vértice encapsulado a buscar.
     * @return O vertice encapsulado encontrado ou nulo caso nçao exista.
     */
    private Vertice<TVertice> getVertice(TVertice vertice) {
        return grafo
                .keySet()
                .stream()
                .filter(v -> v.getVertice().equals(vertice))
                .findFirst()
                .orElse(null);
    }

}
