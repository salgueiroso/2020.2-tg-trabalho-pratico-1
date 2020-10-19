/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico.testes;

import br.com.tutotec.trabalhopratico.GrafoString;
import br.com.tutotec.trabalhopratico.Vertice;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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

        var grafo = new GrafoString();
        String conteudo = "A:B,C,D";
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

        var grafo = new GrafoString();
        String conteudo = "A:B,C,D";
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

        var grafo = new GrafoString();
        String conteudo = "A:B,C,D";
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

        var grafo = new GrafoString();
        String conteudo = "A:B,C,D\n";
        conteudo += "B:A,C,D\n";
        conteudo += "C:A,B,D\n";
        conteudo += "D:A,B,C\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertTrue(grafo.ehCompleto());
    }

    @Test
    public void testEhCompleto_falso() {

        var grafo = new GrafoString();
        String conteudo = "A:B,C,D\n";
        conteudo += "B:A,C,D\n";
        conteudo += "C:A,B,D\n";
        conteudo += "D:A,B\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertFalse(grafo.ehCompleto());
    }

    @Test
    public void testEhConexo() {

        var grafo = new GrafoString();
        String conteudo = "v1:v2\n";
        conteudo += "v2:v3\n";
        conteudo += "v3:v4\n";
        conteudo += "v4:v1,v6\n";
        conteudo += "v5:v3,v6\n";
        conteudo += "v6:v5\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertTrue(grafo.ehConexo());
    }

    @Test
    public void testEhConexo_falso() {

        var grafo = new GrafoString();
        String conteudo = "v1:v2\n";
        conteudo += "v2:v3\n";
        conteudo += "v3:v4\n";
        conteudo += "v4:v1\n";
        conteudo += "v5:v3,v6\n";
        conteudo += "v6:v5\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertFalse(grafo.ehConexo());
    }

    @Test
    public void testEhRegular() {

        var grafo = new GrafoString();
        String conteudo = "";
        
        conteudo += "a1:a2,a3\n";
        conteudo += "a2:a1,a3\n";
        conteudo += "a3:a1,a2\n";
        
        conteudo += "b1:b2,b3\n";
        conteudo += "b2:b1,b3\n";
        conteudo += "b3:b1,b2\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertTrue(grafo.ehRegular());
    }

    @Test
    public void testEhRegular_falso() {

        var grafo = new GrafoString();
        String conteudo = "";
        
        conteudo += "a1:a2,a3\n";
        conteudo += "a2:a1,a3\n";
        conteudo += "a3:a1,a2\n";
        
        conteudo += "b1:b2,b3\n";
        conteudo += "b2:b1,b3\n";
        grafo.carregarGrafoDoTexto(conteudo);

        assertFalse(grafo.ehRegular());
    }

}
