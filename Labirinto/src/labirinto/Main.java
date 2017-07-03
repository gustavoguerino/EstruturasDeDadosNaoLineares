package labirinto;

import java.io.IOException;
import java.util.ArrayList;
import labirinto.domain.Matrix;

public class Main {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String fileContents = Lib.fileReader("teste.dat");
        Matrix matrix = new Matrix(Lib.matrixify(fileContents));
        System.out.println(matrix.printWalkerPosition());
        int[] playerPosition = matrix.findWalkerPosition();
        matrix.findShortestPath(new ArrayList<>(), playerPosition[0], playerPosition[1]);
        System.out.println("Fim! Caminho é: " + matrix.minVisitedNodes + " de tamanho: " + matrix.minDist);
    }
}
