package ifrn.tads;

import multigrafo.MultiGrafo;
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
    private Integer[] coordenadaJogador;

    public Labirinto() {
        grafoLabirinto = new GrafoLabirinto();
        listaDeSaidas = new ArrayList<>();
        coordenadaJogador = new Integer[] {0,0};
    }

    private class GrafoLabirinto extends MultiGrafo {
        private void conectarArestasAdjacentes(int i, int j) {
            if (isCoordenadasValidas(i, j-1) && isIniciado(i, j-1)) {
                this.insereAresta(grafoLabirinto.vertices().get(i), grafoLabirinto.vertices().get(j-1));
            }
            if (isCoordenadasValidas(i+1, j) && isIniciado(i+1, j)) {
                this.insereAresta(grafoLabirinto.vertices().get(i+1), grafoLabirinto.vertices().get(j));
            }
            if (isCoordenadasValidas(i, j+1) && isIniciado(i, j+1)) {
                this.insereAresta(grafoLabirinto.vertices().get(i), grafoLabirinto.vertices().get(j+1));
            }
            if (isCoordenadasValidas(i-1, j) && isIniciado(i-1, j)) {
                this.insereAresta(grafoLabirinto.vertices().get(i-1), grafoLabirinto.vertices().get(j));
            }
        }

        private void iniciaIndice(int i, int j) {
            this.matrizAdj[i][j] = new ArrayList<>();
        }

        private boolean isIniciado(int i, int j) {
            return this.matrizAdj[i][j] != null;
        }

        private boolean isCoordenadasValidas(int i, int j) {
            return (i > -1 && j > -1 && i < this.ordem() && j < this.ordem());
        }

        private void printaArestas(int i, int j) {
            System.out.println(this.matrizAdj[i][j]);
        }
    }

    public void printaArestas(int i, int j) {
        grafoLabirinto.printaArestas(i, j);
    }

    public void printaPosicaoJogador() {
        System.out.println("[X:" + coordenadaJogador[1] + ", Y:" + coordenadaJogador[0] + "]");
    }

    public void printaSaidas() {
        for (Integer[] coordenadas : this.listaDeSaidas) {
            System.out.println("[X:" + coordenadas[1] + ", Y:" + coordenadas[0] + "]");
        }
    }

    public void iniciaLabirinto(final String path) throws IOException {
        grafoLabirinto.vertices().clear();
        String fileContents = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        int fileContentsLength = fileContents.length();
        for (int i = 0; i < fileContentsLength; i++) {
            Character character = fileContents.charAt(i);
            if (character != '\n') {
                this.grafoLabirinto.inserirVertice(new Vertices(i, 0));
            } else {
                break;
            }
        }

        int i = 0;
        int j = 0;
        for (int k = 0; k < fileContentsLength; k++) {
            Character character = fileContents.charAt(k);
            if (character != '\n') {
                if (character != '1') {
                    grafoLabirinto.iniciaIndice(i, j);
                    grafoLabirinto.conectarArestasAdjacentes(i,j);
                }
                if (character == '2') {
                    coordenadaJogador[0] = i;
                    coordenadaJogador[1] = j;
                }
                if (character == '3') {
                    listaDeSaidas.add(new Integer[] {i,j});
                }
                i++;
            } else {
                i = 0;
                j++;
            }
        }
    }

}
