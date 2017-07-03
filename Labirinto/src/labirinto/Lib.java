package labirinto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import labirinto.domain.Cell;
import labirinto.domain.Cell.CellEnum;
import labirinto.domain.Walker;

public class Lib {
    
    public static String fileReader(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
    
    public static Cell[][] matrixify(String fileContents) {
        int fileContentsLength = fileContents.length();
        int xDimension = 0, yDimension = 0;
        for (int i = 0; i < fileContentsLength; i++) {
            Character character = fileContents.charAt(i);
            if (character == '\n') {
                yDimension++;
            } else if (yDimension == 0) {
                xDimension++;
            }
        }
        Cell[][] matrix = new Cell[xDimension][yDimension];
        
        xDimension = 0;
        yDimension = 0;
        for (int i = 0; i < fileContentsLength; i++) {
            Character character = fileContents.charAt(i);
            if (character != '\n') {
                if (character == '1') {
                    matrix[xDimension][yDimension] = Cell.build(CellEnum.CELULA_PAREDE);
                } else {
                    if (character == '3') {
                        matrix[xDimension][yDimension] = Cell.build(CellEnum.CELULA_SAIDA);
                    } else {
                        matrix[xDimension][yDimension] = Cell.build(CellEnum.CELULA_VAZIA);
                        if (character == '2') {
                            matrix[xDimension][yDimension].walker = new Walker();
                        }
                    }
                }
                xDimension++;
            } else {
                yDimension++;
                xDimension = 0;
            }
        }
        return matrix;
    }
}
