package labirinto.domain;

public class Cell {
    
    public Walker walker;
    public boolean walkable;
    public CellEnum cellType;
    private int i;
    private int j;
    public enum CellEnum {
        CELULA_VAZIA, CELULA_PAREDE, CELULA_SAIDA
    }

    public static Cell build(CellEnum option) {
        Cell cell = new Cell();
        cell.walkable = (option != CellEnum.CELULA_PAREDE);
        cell.cellType = option;
        return cell;
    }
    
    public Cell(){};
    
    public Cell(int i, int j){
        this.i = i;
        this.j = j;
    }
    
}