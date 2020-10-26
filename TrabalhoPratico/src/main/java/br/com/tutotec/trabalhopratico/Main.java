/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acacio Salgueiro
 */
public class Main {

    public static void main(String[] args) {

        var grafo = new GrafoAtividade();
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\Grafo1.txt"));
            String linha;
            while ((linha = buffer.readLine()) != null) {
                grafo.carregarGrafoDoTexto(linha);
            }
            
            System.out.println("================================================");
            System.out.println("Impressão do Grafo");
            grafo.print();
            
            System.out.println("");
            System.out.println("> Detalhes:");

            System.out.println("> O grafo é completo: " + grafo.ehCompleto());
            System.out.println("> O grafo é conexo: " + grafo.ehConexo());
            System.out.println("> O grafo é regular: " + grafo.ehRegular());

            
            System.out.println("================================================");
            System.out.println("Algoritmos de menor caminho Dijkstra");
            System.out.println("");
            
            System.out.println("Menor caminho entre o vértice 'a' e o vertice 'i':");            
            grafo.ImprimeCaminhoParaAlvo("a", "i");
            
            System.out.println("");
            
            System.out.println("Menor caminho entre o vértice 'a' e todos:");            
            grafo.ImprimeCaminhoParaTodos("a");
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
