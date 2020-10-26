/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico.testes;

import br.com.tutotec.trabalhopratico.GrafoAtividade;
import java.security.InvalidParameterException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Acacio Salgueiro
 */
public class GrafoStringTest {

    public GrafoStringTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCarregarGrafoDoTexto() {

        var grafo = new GrafoAtividade();
        String conteudo = "A:B(0),C(0),D(0)";
        grafo.carregarGrafoDoTexto(conteudo);
        var expected = new String[]{"B", "C", "D"};

        var actual = grafo.getAdjacentes("A");

        assertArrayEquals(
                expected,
                actual.toArray(new String[expected.length])
        );
    }

    @Test
    public void testCarregarGrafoDoTexto_fail() {

        var grafo = new GrafoAtividade();
        String conteudo = "A:B(0),C(0),D(0)";
        grafo.carregarGrafoDoTexto(conteudo);
        var expected = new String[]{"B", "C", "D", "E"};

        var actual = grafo.getAdjacentes("A");

        assertNotEquals(
                expected.length,
                actual.size()
        );
    }

    @Test
    public void testGetAdjacentes() {

        var grafo = new GrafoAtividade();
        String conteudo = "A:B(0),C(0),D(0)";
        grafo.carregarGrafoDoTexto(conteudo);
        var expected = new String[]{"B", "C", "D"};

        var actual = grafo.getAdjacentes("A");

        assertArrayEquals(
                expected,
                actual.toArray(new String[expected.length])
        );
    }

    @Test
    public void testEhCompleto() {

        var grafo = new GrafoAtividade();
        String conteudo = "A:B(0),C(0),D(0)\n";
        conteudo += "B:A(0),C(0),D(0)\n";
        conteudo += "C:A(0),B(0),D(0)\n";
        conteudo += "D:A(0),B(0),C(0)\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertTrue(grafo.ehCompleto());
    }

    @Test
    public void testEhCompleto_falso() {

        var grafo = new GrafoAtividade();
        String conteudo = "A:B(0),C(0),D(0)\n";
        conteudo += "B:A(0),C(0),D(0)\n";
        conteudo += "C:A(0),B(0),D(0)\n";
        conteudo += "D:A(0),B(0)\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertFalse(grafo.ehCompleto());
    }

    @Test
    public void testEhConexo() {

        var grafo = new GrafoAtividade();
        String conteudo = "v1:v2(0)\n";
        conteudo += "v2:v3(0)\n";
        conteudo += "v3:v4(0)\n";
        conteudo += "v4:v1(0),v6(0)\n";
        conteudo += "v5:v3(0),v6(0)\n";
        conteudo += "v6:v5(0)\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertTrue(grafo.ehConexo());
    }

    @Test
    public void testEhConexo_falso() {

        var grafo = new GrafoAtividade();
        String conteudo = "v1:v2(0)\n";
        conteudo += "v2:v3(0)\n";
        conteudo += "v3:v4(0)\n";
        conteudo += "v4:v1(0)\n";
        conteudo += "v5:v3(0),v6(0)\n";
        conteudo += "v6:v5(0)\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertFalse(grafo.ehConexo());
    }

    @Test
    public void testEhRegular() {

        var grafo = new GrafoAtividade();
        String conteudo = "";

        conteudo += "a1:a2(0),a3(0)\n";
        conteudo += "a2:a1(0),a3(0)\n";
        conteudo += "a3:a1(0),a2(0)\n";

        conteudo += "b1:b2(0),b3(0)\n";
        conteudo += "b2:b1(0),b3(0)\n";
        conteudo += "b3:b1(0),b2(0)\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertTrue(grafo.ehRegular());
    }

    @Test
    public void testEhRegular_falso() {

        var grafo = new GrafoAtividade();
        String conteudo = "";

        conteudo += "a1:a2(0),a3(0)\n";
        conteudo += "a2:a1(0),a3(0)\n";
        conteudo += "a3:a1(0),a2(0)\n";

        conteudo += "b1:b2(0),b3(0)\n";
        conteudo += "b2:b1(0),b3(0)\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertFalse(grafo.ehRegular());
    }

    @Test
    public void testBuscaProfundidade_InvalidParameterException() {
        var grafo = new GrafoAtividade();
        Assertions.assertThrows(InvalidParameterException.class, () -> {
            grafo.buscaProfundidade(grafo.getVertice("a1"), grafo.getVertice("a4"));
        });

    }

    @Test
    public void testBuscaProfundidade() {
        var grafo = new GrafoAtividade();
        String conteudo = "";

        conteudo += "a1:a2(0),a3(0)\n";
        conteudo += "a2:a1(0),a3(0),a4(0)\n";
        conteudo += "a3:a1(0),a2(0)\n";

        grafo.carregarGrafoDoTexto(conteudo);

        var caminho = grafo
                .buscaProfundidade(grafo.getVertice("a1"), grafo.getVertice("a4"))
                .stream()
                .map(x -> x.getVertice())
                .collect(Collectors.toList());

        var expected = new String[]{"a1", "a2", "a4"};
        var actual = caminho.toArray(new String[caminho.size()]);

        assertArrayEquals(
                expected,
                actual
        );

    }

    @Test
    public void testMenorCaminhoDijkstra() {
        var grafo = new GrafoAtividade();
        String conteudo = "";

        conteudo += "a:b(1),e(11),g(11)\n";
        conteudo += "b:d(7),f(3),c(7)\n";
        conteudo += "c:i(11),j(7)\n";
        conteudo += "d:h(3)\n";
        conteudo += "e:g(11),j(7)\n";
        conteudo += "f:i(42)\n";
        conteudo += "g:k(7)\n";
        conteudo += "h:g(5),k(42)\n";
        conteudo += "i:k(1)\n";
        conteudo += "j:k(5)\n";
        conteudo += "k:i(1)\n";

        grafo.carregarGrafoDoTexto(conteudo);

        var caminho = grafo
                .menorCaminhoDijkstra(grafo.getVertice("a"), grafo.getVertice("i"))
                .stream()
                .map(x->x.getVertice())
                .collect(Collectors.toList());

        var expected = new String[]{"a", "b", "c", "i"};
        var actual = caminho.toArray(new String[caminho.size()]);

        assertArrayEquals(
                expected,
                actual
        );
    }

}
