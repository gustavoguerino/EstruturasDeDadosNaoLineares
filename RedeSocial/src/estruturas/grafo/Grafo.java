package estruturas.grafo;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by gustavo on 14/06/2017.
 *
 * TAD GRAFO - IFRN- ESTRUTURA DE DADOS NÃO LINEARES - 2017.1
 * Gustavo Guerino
 * Késsia Castro
 * Mauro de Carvalho
 *
 */
public class Grafo {
    private int quantidadeVertices;
    private Vector vertices;
    private Aresta matrizAdjacencia[][];

    public Grafo() {
        this.quantidadeVertices = 0;
        this.vertices = new Vector();
    }

    //====================================================================================
    //              REMOVER ARESTA
    //              remove uma aresta entre dois vertices (a primeira do vector)
    //-----------------------------------------------------------------------------------
    protected void removerAresta(int chave1, int chave2) {
        int indice1 = encontraIndice(chave1), indice2 = encontraIndice(chave2);
        if (indice1 != -1 && indice2 != -1 && matrizAdjacencia[indice1][indice2] != null) {//Apenas insere se ambas as chaves existirem no grafo
            matrizAdjacencia[indice1][indice2] = null;
        }
    }

    //====================================================================================
    //              INSERIR ARESTA
    //              Insere uma aresta entre dois vertices
    //-----------------------------------------------------------------------------------
    protected void inserirAresta(int chave1, int chave2, int valor) {
        int indice1 = encontraIndice(chave1), indice2 = encontraIndice(chave2);
        if (indice1 != -1 && indice2 != -1) {//Apenas insere se ambas as chaves existirem no grafo
            Aresta aresta = new Aresta();//Cria uma nova aresta
            aresta.setValor(valor);
            aresta.destino = (Vertice) vertices.get(indice2);
            if(indice1 == indice2)  //Ignora caso seja um laço...
                aresta.setLoop(true); //Usado na contagem do grau..
            matrizAdjacencia[indice1][indice2] = (aresta);
        }
    }

    //====================================================================================
    //              INSERIR VERTICE
    //              Insere um vertice ao grafo, aumentando o tamanho da matriz
    //-----------------------------------------------------------------------------------
    protected void inserirVertice(int chave, String valor) {
        if (encontraIndice(chave) == -1) {//Apenas insere se a chave não existir
            //Adiciona o vertice
            vertices.add(new Vertice(chave, valor));
            //Amplia a matriz em uma linha e uma coluna, mantendo o conteudo.
            quantidadeVertices++;
            Aresta tempMatrizAdjacencia[][] = new Aresta[quantidadeVertices][quantidadeVertices];
            //Percorre toda matriz antiga passando os elementos pra nova
            for (int linha = 0; linha < quantidadeVertices - 1; linha++)
                for (int coluna = 0; coluna < quantidadeVertices - 1; coluna++)
                    tempMatrizAdjacencia[linha][coluna] = matrizAdjacencia[linha][coluna];
            matrizAdjacencia = tempMatrizAdjacencia;
        }
    }


    //====================================================================================
    //              REMOVER VERTICE
    //              Remove um vertice do grafo, reduzindo a matriz de adjacencia
    //------------------------------------------------------------------------------------
    protected void removerVertice(int chave) {
        int indice = encontraIndice(chave);
        if (indice != -1) { //Para caso a chave não exista
            vertices.remove(indice);
            // remove a linha e coluna respectiva ao indice da matriz de adjacência
            quantidadeVertices--;
            Aresta tempMatrizAdjacencia[][] = new Aresta[quantidadeVertices][quantidadeVertices];
            int controleColuna, controleLinha = 0;
            //Percorre a matriz de adjacencia ignorando a linha e coluna completa relacionado ao indice
            for (int linha = 0; linha < quantidadeVertices + 1; linha++) {
                if (linha != indice) {//Ignora a linha do indice
                    controleColuna = 0;
                    for (int coluna = 0; coluna < quantidadeVertices + 1; coluna++) {
                        if (coluna != indice) {//Ignora a coluna do indice
                            tempMatrizAdjacencia[controleLinha][controleColuna] = matrizAdjacencia[linha][coluna];
                            if (coluna != indice)
                                controleColuna++;
                        }
                    }
                    controleLinha++;
                }
            }
            matrizAdjacencia = tempMatrizAdjacencia;
        }
    }


    //====================================================================================
    //              ENCONTRA UM INDICE
    //              Busca o indice de uma chave no vector de vertices
    //------------------------------------------------------------------------------------
    protected int encontraIndice(int chave) {
        Iterator I = vertices.iterator();
        int indice = 0;
        while (I.hasNext()) {
            Vertice v = (Vertice) (I.next());
            if (v.getChave() == chave)
                return indice;
            indice++;
        }
        return -1;
    }
    //====================================================================================
    //              PEGAR ARESTAS CONECTADAS
    //              Retorna todos as arestas ligadas a um vertice passado como parametro
    //------------------------------------------------------------------------------------
    protected List<Aresta> arestasConectados(int chave) {
        Vector arestas = new Vector();
        int indice = encontraIndice(chave);
        if(indice != -1){
            for (int coluna = 0; coluna < quantidadeVertices; coluna++) {
                if(matrizAdjacencia[indice][coluna] != null)
                    arestas.add(matrizAdjacencia[indice][coluna]);
            }
        }
        return arestas;
    }    
//====================================================================================
    //              PEGAR ARESTAS CONECTADAS
    //              Retorna todos as arestas ligadas a um vertice passado como parametro
    //------------------------------------------------------------------------------------
    protected Aresta encontrarAresta(int chave1, int chave2) {
        int indice = encontraIndice(chave1), indice2 = encontraIndice(chave2);
        if(indice != -1 && indice2 != -1){
           return matrizAdjacencia[indice][indice2];
        }
        return null;
    }


    //====================================================================================
    //              PRINT GRAFO
    //              Exibe todos os vertices e matriz de adjacencia
    //------------------------------------------------------------------------------------
    public void printGrafo() {
        System.out.println("\n\nVertices: ([chave] valor)\n");
        for (int f = 0; f < vertices.size(); f++)
            System.out.println(vertices.elementAt(f));
        System.out.println("\n\nMatriz de Adjacencia:\n");
        for (Aresta[] linha : matrizAdjacencia) {
            for(Aresta coluna : linha) {
                System.out.print((coluna != null) ? "| " + "1" + "\t" : "| 0\t");
            }
            System.out.println("\n");
        }
    }

    //====================================================================================
    //              ORDEM
    //              Retorna a quantidade de vertices (Ordem)
    //------------------------------------------------------------------------------------
    public int ordem() {
        return quantidadeVertices;
    }

    //====================================================================================
    //              GRAU
    //              Retorna o grau de um vertices (Quantidades de arestas sobre o mesmo)
    //------------------------------------------------------------------------------------
    protected int grau(int chave) {
        int indice = encontraIndice(chave), grau = 0;
        if(indice != -1){
            for (int coluna = 0; coluna < quantidadeVertices; coluna++) {// Percorre todas colunas da tabela de adjacencia para o vertice selecionado
                if(matrizAdjacencia[indice][coluna] != null) {
                        if(matrizAdjacencia[indice][coluna].isLoop()) grau += 2;
                        else grau += 1;
                        //Loop conta como 2 no grau.
                }
            }
        }
        return grau;
    }
    //====================================================================================
    //              CAMINHO EULERIANO
    //              Retorna TRUE se possuir um caminho euleriano
    //------------------------------------------------------------------------------------
    public boolean caminhoEuleriano(){
        Iterator I = vertices.iterator();
        int indicesImpar = 0;
        while (I.hasNext()) {
            Vertice v = (Vertice) (I.next());
            if(grau(v.getChave()) % 2 != 0){
                indicesImpar++;
            }
        }
        if(indicesImpar > 0 && indicesImpar != 2) return false;
        else return true;
    }
    //====================================================================================
    //              CAMINHO EULERIANO
    //              Retorna TRUE se possuir um caminho euleriano
    //------------------------------------------------------------------------------------
    public boolean circuitoEuleriano(){
        Iterator I = vertices.iterator();
        while (I.hasNext()) {
            Vertice v = (Vertice) (I.next());
            if(grau(v.getChave()) % 2 != 0){
                return true;
            }
        }
        return false;
    }
}

