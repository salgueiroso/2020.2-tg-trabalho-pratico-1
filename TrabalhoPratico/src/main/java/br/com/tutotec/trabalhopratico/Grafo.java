/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
     * Mapa de hash contendo todos os custos de cada vértice adjascente.
     */
    protected LinkedHashMap<Vertice<TVertice>, LinkedHashMap<Vertice<TVertice>, Integer>> custosGrafo;

    /**
     * Components do grafo.
     */
    protected ArrayList<ArrayList<Vertice<TVertice>>> componentes;

    /**
     * Cria uma nova instancia para analisar um grafo.
     */
    public Grafo() {
        grafo = new LinkedHashMap<>();
        custosGrafo = new LinkedHashMap<>();
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
     *
     * @param vertice Vértice base
     * @param verticeAdjacente Vértice que será adjacente ao vértice base.
     * @param custo Custo da aresta
     */
    public void addVerticeAdjacente(TVertice vertice, TVertice verticeAdjacente, int custo) {
        var verticeAdicionado = addVertice(vertice);
        var adjacenteAdicionado = addVertice(verticeAdjacente);

        var adjacentes = grafo.get(verticeAdicionado);
        if (!adjacentes.contains(adjacenteAdicionado)) {
            adjacentes.add(adjacenteAdicionado);
        }

        var adjacentesCusto = custosGrafo.get(verticeAdicionado);
        if (!adjacentesCusto.containsKey(adjacenteAdicionado)) {
            adjacentesCusto.put(adjacenteAdicionado, custo);
        } else {
            adjacentesCusto.replace(verticeAdicionado, custo);
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
     * Realiza uma busca em profundidado no grafo
     *
     * @param origem Vértice inicial / raiz
     * @return Retorna uma pilha com o caminho encontrado ou uma pilha vazia
     * caso não exista um caminho
     */
    public Stack<Vertice<TVertice>> buscaProfundidade(Vertice<TVertice> origem) {
        return buscaProfundidade(origem, null);
    }

    /**
     * Realiza uma busca em profundidado no grafo
     *
     * @param origem Vértice inicial / raiz
     * @param alvo Vértice alvo
     * @return Retorna uma pilha com o caminho encontrado ou uma pilha vazia
     * caso não exista um caminho
     */
    public Stack<Vertice<TVertice>> buscaProfundidade(Vertice<TVertice> origem, Vertice<TVertice> alvo) {

        if (origem == null) {
            throw new InvalidParameterException("origem não pode ser nula");
        }

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
     * Obtém o custo de uma aresta
     * @param vertice Vertice origem
     * @param adjacente Vertice adjacente alvo
     * @return retorna o custo da aresta
     */
    public int obterCustoAresta(TVertice vertice, TVertice adjacente) {
        return this.custosGrafo
                .entrySet()
                .stream()
                .filter(kv -> kv.getKey().getVertice() == vertice)
                .flatMap(v -> v.getValue().entrySet().stream())
                .filter(v -> v.getKey().getVertice() == adjacente)
                .map(x -> x.getValue())
                .findFirst()
                .orElse(0);
    }

    /**
     * Obtém o vértice encapsulado a partir do valor do vértice informado.
     *
     * @param vertice O valor do vértice encapsulado a buscar.
     * @return O vertice encapsulado encontrado ou nulo caso nçao exista.
     */
    public Vertice<TVertice> getVertice(TVertice vertice) {
        return grafo
                .keySet()
                .stream()
                .filter(v -> v.getVertice().equals(vertice))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Algoritmo de Dijkstra's
     * @param origem Vertice origem
     * @param alvo Vertice destino/alvo
     * @return Uma lista com a sequencia de vertices de a cordo com o menor caminho entre os vertices informados.
     */
    public List<Vertice<TVertice>> menorCaminhoDijkstra(Vertice<TVertice> origem, Vertice<TVertice> alvo) {

        var vertices = grafo.keySet();

        vertices.stream()
                .forEach(v -> v.redefinir());

        var conjuntoNaoManipulado = new HashMap<TVertice, VerticeDijkstra<TVertice>>();
        var conjuntoManipulado = new HashMap<TVertice, VerticeDijkstra<TVertice>>();

        for (var vertice : vertices) {
            conjuntoNaoManipulado.put(vertice.getVertice(), new VerticeDijkstra<>());
            var v = conjuntoNaoManipulado.get(vertice.getVertice());
            v.setPath(null);
            v.setDistancia(Long.MAX_VALUE);
            v.setVertice(vertice.getVertice());
        }

        conjuntoNaoManipulado.get(origem.getVertice()).setDistancia(0);

        while (!conjuntoNaoManipulado.isEmpty()) {
            var u = conjuntoNaoManipulado.values()
                    .stream()
                    .min(Comparator.comparingLong(VerticeDijkstra<TVertice>::getDistancia))
                    .get();

            conjuntoNaoManipulado.remove(u.getVertice());

            var adjacentes = getAdjacentes(u.getVertice())
                    .stream()
                    .filter(x -> conjuntoNaoManipulado.keySet().contains(x))
                    .collect(Collectors.toList());

            for (var adjacente : adjacentes) {
                var custo = u.getDistancia() + obterCustoAresta(u.getVertice(), adjacente);
                var v = conjuntoNaoManipulado.get(adjacente);
                if (custo < v.getDistancia()) {
                    v.setDistancia(custo);
                    v.setPath(u);
                }
            }

            conjuntoManipulado.put(u.getVertice(), u);

        }

        var path = new ArrayList<Vertice<TVertice>>();
        var v = conjuntoManipulado.get(alvo.getVertice());

        v = conjuntoManipulado.get(alvo.getVertice());
        if (v != null || v == conjuntoManipulado.get(origem.getVertice())) {
            while (v != null) {
                var vertice = getVertice(v.getVertice());
                vertice.setDistancia(v.getDistancia());
                path.add(0, vertice);
                v = v.getPath();
            }
        }

        return path;

    }

    /**
     * Adiciona um vertice no grafo
     *
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
            custosGrafo.put(verticeKey, new LinkedHashMap<>());
        }

        return verticeKey;
    }

    /**
     * Obtém um vértice não visitado, adjacente ao vértice informado.
     *
     * @param vertice Vértice base para retorno dos adjascentes
     * @return O próximo vertice adjacente não visitado ou nulo caso não exista
     */
    private Vertice<TVertice> getAdjacsenteNaoVisitado(Vertice<TVertice> vertice) {
        var result = grafo.get(vertice).stream().filter(x -> !x.isVisitado()).findFirst().orElse(null);
        return result;
    }

}
