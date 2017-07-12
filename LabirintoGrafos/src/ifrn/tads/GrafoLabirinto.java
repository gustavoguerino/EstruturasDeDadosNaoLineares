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
            insereAresta(vertices, vertices().get(indiceTmp), 1);
        }
        indiceTmp = achaIndice(String.format("%d-%d", indiceVerticeI, indiceVerticeJ-1));
        if (indiceTmp != -1 && isIniciado(indiceTmp)) {
            insereAresta(vertices, vertices().get(indiceTmp), 1);
        }
        indiceTmp = achaIndice(String.format("%d-%d", indiceVerticeI+1, indiceVerticeJ));
        if (indiceTmp != -1 && isIniciado(indiceTmp)) {
            insereAresta(vertices, vertices().get(indiceTmp), 1);
        }
        indiceTmp = achaIndice(String.format("%d-%d", indiceVerticeI, indiceVerticeJ+1));
        if (indiceTmp != -1 && isIniciado(indiceTmp)) {
            insereAresta(vertices, vertices().get(indiceTmp),1);
        }
    }

    void iniciaIndice(int indiceVertice) {
        for (int i = 0; i < ordem(); i++) this.matrizAdj[indiceVertice][i] = new ArrayList<>();
    }

    void printaArestasVertices(String chaveVertice) {
        System.out.println(getArestasVertice(vertices().get(achaIndice(chaveVertice))));
    }

    boolean isIniciado(int indiceVertice) {
        return this.matrizAdj[indiceVertice][0] != null;
    }

    List<Arestas> getArestasVertice(Vertices verticeOrigem) {
        List<Arestas> arestasList = new ArrayList<>();
        int indiceVertice = achaIndice(verticeOrigem.getChave());
        if (isIniciado(indiceVertice)) {
            for (int i = 0; i < ordem(); i++)
                if (matrizAdj[indiceVertice][i] != null)
                    arestasList.addAll(matrizAdj[indiceVertice][i]);
        }
        return arestasList;
    }
}