package ifrn.tads;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    Labirinto labirinto = new Labirinto();
	    labirinto.iniciaLabirinto("teste.dat");
	    labirinto.printaSaidas();
	    labirinto.printaPosicaoJogador();
	    labirinto.printaArestasVertices("4-3");
	    labirinto.acharMenorCaminho();
    }
}
