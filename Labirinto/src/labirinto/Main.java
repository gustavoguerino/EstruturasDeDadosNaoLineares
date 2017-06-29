package labirinto;

import java.io.IOException;
import labirinto.domain.Cell;

public class Main {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String fileContents = Libs.fileReader("teste.dat");
        Cell[][] matrix = Libs.matrixify(fileContents);
        System.out.println(Libs.printWalkerPosition(matrix));
    }
}
