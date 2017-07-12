package ifrn.tads;


import multigrafo.Arestas;
import multigrafo.MultiGrafo;
import multigrafo.Vertices;

import java.util.ArrayList;
import java.util.List;

class GrafoLabirinto extends MultiGrafo {
    void conectarArestasAdjacentes(int indiceVerticeI, int indiceVerticeJ, Vertices vertices) {
        int indiceTmp = achaIndice(String.format("%d-%d", indiceVerticeI-1, indiceVerticeJ));
        if (indiceTmp != -1 && isIniciado(indiceTmp)) {
            insereAresta(vertices().get(indiceTmp), vertices, 1);
            insereAresta(vertices, vertices().get(indiceTmp), 1);
        }
        indiceTmp = achaIndice(String.format("%d-%d", indiceVerticeI, indiceVerticeJ-1));
        if (indiceTmp != -1 && isIniciado(indiceTmp)) {
            insereAresta(vertices().get(indiceTmp), vertices, 1);
            insereAresta(vertices, vertices().get(indiceTmp), 1);
        }
        indiceTmp = achaIndice(String.format("%d-%d", indiceVerticeI+1, indiceVerticeJ));
        if (indiceTmp != -1 && isIniciado(indiceTmp)) {
            insereAresta(vertices().get(indiceTmp), vertices, 1);
            insereAresta(vertices, vertices().get(indiceTmp), 1);
        }
        indiceTmp = achaIndice(String.format("%d-%d", indiceVerticeI, indiceVerticeJ+1));
        if (indiceTmp != -1 && isIniciado(indiceTmp)) {
            insereAresta(vertices().get(indiceTmp), vertices, 1);
            insereAresta(vertices, vertices().get(indiceTmp), 1);
        }
    }

    void iniciaIndice(int indiceVertice) {
        for (int i = 0; i < ordem(); i++) this.matrizAdj[indiceVertice][i] = new ArrayList<>();
    }

    boolean isIniciado(int indiceVertice) {
        return this.matrizAdj[indiceVertice][0] != null;
    }

    void printaArestas(int i, int j) {
        System.out.println(this.matrizAdj[i][j]);
    }

    List<Arestas> getArestasVertice(Vertices verticeOrigem) {
        List<Arestas> arestasList = new ArrayList<>();
        int indiceVertice = achaIndice(verticeOrigem.getChave());
        System.out.println("chave vertice: " + verticeOrigem.getChave() + " " + indiceVertice);
        if (isIniciado(indiceVertice)) {
            for (int i = 0; i < ordem() ; i++){
            	if(matrizAdj[indiceVertice][i] != null)
            		arestasList.addAll(matrizAdj[indiceVertice][i]);
            }

        }
        return arestasList;
    }
}