package labirinto.domain;

import java.util.ArrayList;
import java.util.List;
import labirinto.domain.Cell.CellEnum;

public class Matrix {
    public Cell[][] matrix;
    public int minDist;
    public List minVisitedNodes;
    public List stringPath;
    public int matrixY;
    public int matrixX;
    
    public Matrix(Cell[][] matrix) {
        this.matrix = matrix;
        this.minDist = Integer.MAX_VALUE;
        this.minVisitedNodes = null;
        this.matrixY = 0;
        this.matrixX = 0;
        this.stringPath = null;
    }
    
    public int[] findWalkerPosition () {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].walker != null) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }
    
    public String printWalkerPosition() {
        int[] position = findWalkerPosition();
        return String.format("[X: %d, Y: %d]", position[0], position[1]);
    }
    
    protected boolean isIndexValid(int i, int j) {
        if (matrixX == 0) {
            matrixX = matrix.length;
            matrixY = matrix[0].length;
        }
        return (i > -1 && j > -1 && i < matrixX && j < matrixY);
    }
    
    protected boolean isMoveValid (Cell matrix[][], int i, int j, List visitedNodes) {
        return (isIndexValid(i, j) && matrix[i][j].walkable && !visitedNodes.contains(matrix[i][j]));
    }
    
    public void findShortestPath(List visitedNodes, int i, int j) {
        if (isIndexValid(i, j)) {
            System.out.println(String.valueOf(i) + "," + String.valueOf(j) + "::");
            if (matrix[i][j].cellType == CellEnum.CELULA_SAIDA && visitedNodes.size() < minDist) {
                List newVisitedNodes = new ArrayList<>(visitedNodes);
                newVisitedNodes.add(matrix[i][j]);
                
                minDist = newVisitedNodes.size();
                minVisitedNodes = newVisitedNodes;
            }
        }
        visitedNodes.add(matrix[i][j]);
        if (isMoveValid(matrix, i+1, j, visitedNodes)) {
            findShortestPath(visitedNodes, i+1, j);
        }
        if (isMoveValid(matrix, i, j+1, visitedNodes)) {
            findShortestPath(visitedNodes, i, j+1);
        }
        if (isMoveValid(matrix, i, j-1, visitedNodes)) {
            findShortestPath(visitedNodes, i, j-1);
        }
        if (isMoveValid(matrix, i-1, j, visitedNodes)) {
            findShortestPath(visitedNodes, i-1, j);
        }
        visitedNodes.remove(matrix[i][j]);
    }
}
