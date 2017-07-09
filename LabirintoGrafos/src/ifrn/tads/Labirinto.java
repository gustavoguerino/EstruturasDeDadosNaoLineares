package ifrn.tads;

import multigrafo.Arestas;
import multigrafo.Vertices;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Labirinto {
    private GrafoLabirinto grafoLabirinto;
    private List<Integer[]> listaDeSaidas;
    private List<Vertices> listaDeSaidasVertices;
    private Integer[] coordenadaJogador;
    private Vertices verticeJogador;

    // dijkstra
    private List<Vertices> menorCaminho;
    private double menorCaminhoValor;

    public Labirinto() {
        grafoLabirinto = new GrafoLabirinto();
        listaDeSaidas = new ArrayList<>();
        listaDeSaidasVertices = new ArrayList<>();
        coordenadaJogador = new Integer[] {0,0};
        menorCaminhoValor = Double.MAX_VALUE;
    }

    void printaArestas(int i, int j) {
        grafoLabirinto.printaArestas(i, j);
    }

    void printaPosicaoJogador() {
        System.out.println("[X:" + coordenadaJogador[1] + ", Y:" + coordenadaJogador[0] + "]");
    }

    void printaSaidas() {
        for (Integer[] coordenadas : this.listaDeSaidas) {
            System.out.println("[X:" + coordenadas[1] + ", Y:" + coordenadas[0] + "]");
        }
    }

    void iniciaLabirinto(final String path) throws IOException {
        grafoLabirinto.vertices().clear();
        String fileContents = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        int fileContentsLength = fileContents.length();

        int i = 0;
        int j = 0;
        for (int k = 0; k < fileContentsLength; k++) {
            Character character = fileContents.charAt(k);
            if (character != '\n') {
                Vertices vertices = new Vertices(String.format("%d-%d", i, j), 0);
                int verticeIndice = grafoLabirinto.vertices().size()-1;
                this.grafoLabirinto.inserirVertice(vertices);
                if (character != '1') {
                    grafoLabirinto.iniciaIndice(verticeIndice);
                    grafoLabirinto.conectarArestasAdjacentes(i, j, vertices);
                    if (character == '2') {
                        coordenadaJogador[0] = i;
                        coordenadaJogador[1] = j;
                        verticeJogador = vertices;
                    }
                    if (character == '3') {
                        listaDeSaidas.add(new Integer[] {i,j});
                        listaDeSaidasVertices.add(vertices);
                    }
                }
                i++;
            } else {
                i = 0;
                j++;
            }
        }
    }

    void acharMenorCaminho() {
        algoritmoDijkstra(grafoLabirinto.getArestasVertice(verticeJogador), null, 0);
        System.out.println("Menor caminho foi: " + ((menorCaminho != null) ? menorCaminho : "não achado"));
        System.out.println("Custo do menor caminho: " + ((menorCaminhoValor != Double.MAX_VALUE) ? menorCaminhoValor : "0"));
    }

    private void algoritmoDijkstra(List<Arestas> arestasList, List<Vertices> caminho, double caminhoValor) {
        for (Arestas arestas : arestasList) {
            double valorAtual = arestas.getValor() + caminhoValor;
            caminho.add(arestas.getVerticeDestino());
            if (listaDeSaidasVertices.contains(arestas.getVerticeDestino()) && valorAtual < menorCaminhoValor) {
                    menorCaminhoValor = arestas.getValor() + caminhoValor;
                    menorCaminho = caminho;
            }
            List<Arestas> arestasAlvo = grafoLabirinto.getArestasVertice(arestas.getVerticeDestino());
            if ((valorAtual < caminhoValor || caminhoValor == Double.MAX_VALUE) && arestasAlvo != null) {
                algoritmoDijkstra(arestasAlvo, caminho, valorAtual);
            }
        }
    }

}
