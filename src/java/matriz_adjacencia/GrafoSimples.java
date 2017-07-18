package matriz_adjacencia;

import java.util.*;

public class GrafoSimples {

    private int qtdVertices;
    private List<Vertices> vertices;
    public Arestas matrizAdj[][];

    public GrafoSimples() {
        qtdVertices = 0;
        vertices = new ArrayList<>();
    }

    public void inserirVertice(Vertices vertice) {
        int novaQtdLinhasColunas = ordem() + 1;
        Arestas novaMatriz[][] = new Arestas[novaQtdLinhasColunas][novaQtdLinhasColunas];
        for (int i = 0; i < ordem(); i++) {
            System.arraycopy(matrizAdj[i], 0, novaMatriz[i], 0, ordem());
        }
        this.matrizAdj = novaMatriz;
        vertices.add(vertice);
        qtdVertices++;
    }

    public void removerVertice(Vertices vertice) {
        qtdVertices--;
        Arestas novaMatriz[][] = new Arestas[qtdVertices][qtdVertices];
        int indice = achaIndice(vertice.getChave());
        vertices.remove(vertice);
        int colunaChecar = 0, linhaChecar = 0;
        for (int i = 0; i < qtdVertices; i++) {
            linhaChecar = 0;
            for (int j = 0; j < qtdVertices; j++) {
                if (i != indice && j != indice) {
                    novaMatriz[colunaChecar][linhaChecar] = matrizAdj[i][j];
                    if (j != indice) {
                        linhaChecar++;
                    }
                }
            }
            if (i != indice) {
                colunaChecar++;
            }
        }
        matrizAdj = novaMatriz;
    }

    public Arestas insereAresta(Vertices VerticeUm, Vertices VerticeDois,
            double valor) {
        Arestas A = new Arestas(VerticeUm, VerticeDois, valor);
        int ind1 = achaIndice(VerticeUm.getChave());
        int ind2 = achaIndice(VerticeDois.getChave());
        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = A; // grafo nao orientado
        return A;
    }

    public Arestas insereAresta(Vertices VerticeUm, Vertices VerticeDois) {
        Arestas A = new Arestas(VerticeUm, VerticeDois);
        int ind1 = achaIndice(VerticeUm.getChave());
        int ind2 = achaIndice(VerticeDois.getChave());
        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = A; // grafo nao orientado
        return A;
    }

    public void removeAresta(Arestas Aresta) {
        int ind1 = achaIndice(Aresta.getVerticeOrigem().getChave());
        int ind2 = achaIndice(Aresta.getVerticeDestino().getChave());
        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = null; // grafo nao orientado
    }

    public Arestas insereArco(Vertices VerticeUm, Vertices VerticeDois, double valor) {
        Arestas A = new Arestas(VerticeUm, VerticeDois, valor, true);
        int ind1 = achaIndice(VerticeUm.getChave());
        int ind2 = achaIndice(VerticeDois.getChave());
        matrizAdj[ind1][ind2] = A; // grafo orientado
        return A;
    }

    public Arestas insereArco(Vertices VerticeUm, Vertices VerticeDois) {
        Arestas A = new Arestas(VerticeUm, VerticeDois, 0, true);
        int ind1 = achaIndice(VerticeUm.getChave());
        int ind2 = achaIndice(VerticeDois.getChave());
        matrizAdj[ind1][ind2] = A; // grafo orientado
        return A;
    }

    public void removeArco(Arestas aresta) {
        int indiceVerticeOrigem = achaIndice(aresta.getVerticeOrigem().getChave());
        int indiceVerticeDestino = achaIndice(aresta.getVerticeDestino().getChave());
        matrizAdj[indiceVerticeOrigem][indiceVerticeDestino] = null;
        if (!(aresta.isDirecionada())) {
            matrizAdj[indiceVerticeDestino][indiceVerticeOrigem] = null;
        }
    }

    public void mostraVertices() {
        for (int f = 0; f < vertices.size(); f++) {
            System.out.print(vertices.get(f) + ",");
        }
    }

    public void mostraMatriz() {
        for (int f = 0; f < qtdVertices; f++) {
            for (int g = 0; g < qtdVertices; g++) {
                System.out.print(matrizAdj[f][g] + " ");
            }
            System.out.println();
        }
    }

    public int grau(Vertices vertice) {
        int grau = 0;
        int qtdLinhas = ordem();
        for (int i = 0; i < qtdLinhas; i++) {
            for (int j = 0; j < qtdLinhas; i++) {
                if (matrizAdj[i][j] != null) {
                    if (matrizAdj[i][j].getVerticeDestino().equals(vertice)
                            || matrizAdj[i][j].getVerticeOrigem().equals(vertice)) {
                        grau++;
                    }
                }
            }
        }
        return grau;
    }

    public int ordem() {
        return qtdVertices;
    }

    public int achaIndice(int chave) {
        int i = 0;
        for (Vertices vertice : vertices()) {
            if (vertice.getChave() == chave) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public List<Vertices> vertices() {
        return vertices;
    }

    public List<Arestas> arestas() {
        List<Arestas> listaDeArestas = new ArrayList<>();
        for (int i = 0; i < qtdVertices; i++) {
            for (int j = 0; j < qtdVertices; j++) {
                listaDeArestas.add(matrizAdj[i][j]);
            }
        }
        return listaDeArestas;
    }

    public List<Arestas> arestasIncidentes(Vertices vertice) {
        List<Arestas> listaDeArestas = new ArrayList<Arestas>();
        int indiceVertice = achaIndice(vertice.getChave());
        for (int i = 0; i < ordem(); i++) {
            if (matrizAdj[i][indiceVertice] != null) {
                listaDeArestas.add(matrizAdj[i][indiceVertice]);
            }
            if (matrizAdj[indiceVertice][i] != null) {
                listaDeArestas.add(matrizAdj[indiceVertice][i]);
            }
        }
        if (matrizAdj[indiceVertice][indiceVertice] != null) {
            listaDeArestas.add(matrizAdj[indiceVertice][indiceVertice]);
        }
        return listaDeArestas;
    }

    public List<Vertices> finalVertices(Arestas a) {
        List<Vertices> listaDeVertices = new ArrayList<>();
        listaDeVertices.add(a.getVerticeOrigem());
        listaDeVertices.add(a.getVerticeDestino());
        return listaDeVertices;
    }

    public Vertices oposto(Vertices v, Arestas a) throws Exception {
        if (v.equals(a.getVerticeDestino())) {
            return a.getVerticeOrigem();
        } else if (v.equals(a.getVerticeOrigem())) {
            return a.getVerticeOrigem();
        }
        throw new Exception("O vértice não tem um oposto para a aresta fornecida. ");
    }

    public boolean isAdjacente(Vertices v, Vertices w) {
        int ind1 = achaIndice(v.getChave());
        int ind2 = achaIndice(w.getChave());
        return (matrizAdj[ind1][ind2]) != null;

    }

    public Arestas getAresta(Vertices v, Vertices w) {
        int ind1 = achaIndice(v.getChave());
        int ind2 = achaIndice(w.getChave());
        return (matrizAdj[ind1][ind2]);
    }
}
