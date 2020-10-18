/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tutotec.trabalhopratico;

import java.util.List;

/**
 *
 * @author acaci
 * @param <TVertice>
 */
public interface IGrafo<TVertice> {
    List<TVertice> getAdjacentes(TVertice vertice);
    boolean ehRegular();
    boolean ehCompleto();
    boolean ehConexo();
}
