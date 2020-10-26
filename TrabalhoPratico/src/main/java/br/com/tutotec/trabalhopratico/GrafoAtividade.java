/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Implementa a classe grafo de forma que trate cada vértice como uma string.
 *
 * @author Acacio Salgueiro
 */
public class GrafoAtividade extends Grafo<String> {

    /**
     * Regex para extraão dos nomes dos vertices e os pesos das arestas
     */
    private Pattern pattern;

    public GrafoAtividade() {
        pattern = Pattern.compile("(?<rotulo>.+)(?:\\()(?<custo>[0-9]+)(\\))");
    }

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
                                var label = ExtraiRotulo(adjacente);
                                var custo = ExtraiCusto(adjacente);
                                addVerticeAdjacente(vertice, label, custo);
                            }
                        }
                    }
                });
    }

    /**
     * Extrai o nome do vertice a partir da expressão regular
     *
     * @param content Conteudo do vertice. Ex.: A(25). Sendo A o nome do vertice
     * e 25 o custo da aresta.
     * @return O nome do vertice
     */
    private String ExtraiRotulo(String content) {
        var m = pattern.matcher(content);
        m.find();
        var rotulo = m.group("rotulo");
        return rotulo;
    }

    /**
     * Extrai o custo da aresta a partir da expressão regular
     *
     * @param content O custo da aresta. Ex.: A(25). Sendo A o nome do vertice e
     * 25 o custo da aresta.
     * @return O custo da aresta
     */
    private int ExtraiCusto(String content) {
        var m = pattern.matcher(content);
        m.find();
        var custo = m.group("custo");
        return Integer.parseInt(custo);
    }

    /**
     * A primeira recebe como parâmetro um vértice e o algoritmo retorna o menor
     * caminho deste para todos os demais vértices. Neste caso, deve-se imprimir
     * na tela a menor distância para cada par de vértice e o caminho final
     * entre eles.
     *
     * @param origem
     * @param alvo
     */
    @Override
    public void ImprimeCaminhoParaAlvo(String origem, String alvo) {
        var vOrigem = getVertice(origem);
        var vAlvo = getVertice(alvo);

        var caminho = menorCaminhoDijkstra(vOrigem, vAlvo);

        var distancia = caminho
                .stream()
                .reduce((a, segundo) -> segundo)
                .map(x -> x.getDistancia())
                .orElse(0l);

        var strCaminho = caminho
                .stream()
                .map(x -> x.getVertice())
                .collect(Collectors.joining("->"));

        System.out.println(String.format("> Menor caminho entre '%s' e '%s'", origem, alvo));
        System.out.println(String.format(">> Menor distancia: '%d'", distancia));
        System.out.println(String.format(">> Menor caminho: '%s'", strCaminho));

    }

    /**
     * A segunda recebe como parâmetro dois vértices e o algoritmo retorna o
     * menor caminho somente entre estes dois vértices. Neste caso, deve-se
     * imprimir na tela a menor distância entre eles e o caminho final.
     *
     * @param origem
     */
    @Override
    public void ImprimeCaminhoParaTodos(String origem) {
        var vOrigem = getVertice(origem);

        for (var alvo : grafo.keySet()) {

            var caminho = menorCaminhoDijkstra(vOrigem, alvo);

            var distancia = caminho
                    .stream()
                    .reduce((a, segundo) -> segundo)
                    .map(x -> x.getDistancia())
                    .orElse(0l);

            var strCaminho = caminho
                    .stream()
                    .map(x -> x.getVertice())
                    .collect(Collectors.joining("->"));

            System.out.println("> ----------------------------------------------");
            System.out.println(String.format("> Menor caminho entre '%s' e '%s'", origem, alvo.getVertice()));
            System.out.println(String.format(">> Menor distancia: '%d'", distancia));
            System.out.println(String.format(">> Menor caminho: '%s'", strCaminho));

        }
    }

}
