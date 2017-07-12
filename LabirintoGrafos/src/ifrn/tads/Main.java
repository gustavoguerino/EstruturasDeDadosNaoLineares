package ifrn.tads;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    Labirinto labirinto = new Labirinto();
	    labirinto.iniciaLabirinto("teste2.dat");
	    labirinto.printaSaidas();
	    labirinto.printaPosicaoJogador();
	    labirinto.acharMenorCaminho();
    }
}
