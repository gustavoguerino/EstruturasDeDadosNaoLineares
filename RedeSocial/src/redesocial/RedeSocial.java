/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesocial;

import estruturas.grafo.Aresta;
import estruturas.grafo.Vertice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author dgti
 */
public class RedeSocial extends estruturas.grafo.Grafo{
    private Vector vertices;
    private Map<Vertice,Integer> sugestoes;
    public RedeSocial(){
        super();
        
    }
    public void inserirAmizade(int id1, int valor1, int id2, int valor2){
        inserirAresta(id1, id2, valor1);
        inserirAresta(id2, id1, valor2);
    }
    public void alterarAmizade(int id1, int id2, int novoValor){
        Aresta aresta = encontrarAresta(id1, id2);
        if(aresta != null){
            aresta.setValor(novoValor);
        }
    }
    public void removerAmizade(int id1, int id2){
        removerAresta(id1, id2);
        removerAresta(id2, id1);
    }
    public void inserirPessoa(int id, String nome){
        inserirVertice(id, nome);
    }
    public void removerPessoa(int id){
        removerVertice(id);
    }
    public Vertice calcularAmizades(int id){
        List amigos = new Vector();
        List arestas = arestasConectados(id);
        this.sugestoes = new HashMap<>();
        for (Iterator iterator = arestasConectados(id).iterator(); iterator.hasNext();) {
        	Aresta amigo = (Aresta) iterator.next();
        	
        	for (Iterator iteratorAdm = arestasConectados(amigo.getDestino().getChave()).iterator(); iteratorAdm.hasNext();) {
            	Aresta amigoDoAmigo = (Aresta) iteratorAdm.next();
            	
            	if(id != amigoDoAmigo.getDestino().getChave()){
            		
		            int peso = 0, pesoAtual = 0;
		            peso = (Math.abs(amigo.getValor() * amigoDoAmigo.getValor()));
		            if(this.sugestoes.containsKey(amigoDoAmigo.getDestino().getChave()))
		            	pesoAtual = this.sugestoes.get( amigoDoAmigo.getDestino());
		            if(amigo.getValor() < 0 || amigoDoAmigo.getValor() < 0)
		            	this.sugestoes.put( amigoDoAmigo.getDestino(),pesoAtual - peso);
		            else
		            	this.sugestoes.put( amigoDoAmigo.getDestino(),pesoAtual + peso);
        		}
        	}
        }
        System.out.println("Calculado = " + sugestoes);
        Vertice maxEntry = Collections.max(sugestoes.entrySet(), Map.Entry.comparingByValue()).getKey();      
        //Retorna o amigo com maior pontua��o..
        return maxEntry;
    }
}
