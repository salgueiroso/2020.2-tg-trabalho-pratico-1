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

        var grafo = new GrafoString();
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\Grafo1.txt"));
            String linha;
            while ((linha = buffer.readLine()) != null) {
                grafo.carregarGrafoDoTexto(linha);
            }
            grafo.print();

            System.out.println("ehCompleto: " + grafo.ehCompleto());
            System.out.println("ehConexo: " + grafo.ehConexo());
            System.out.println("ehRegular: " + grafo.ehRegular());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
