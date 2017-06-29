package labirinto.domain;

public class Cell {
    
    public Walker walker;
    public boolean walkable;
    public CellEnum cellType;
    
    public enum CellEnum {
        CELULA_VAZIA, CELULA_PAREDE, CELULA_SAIDA
    }

    public static Cell build(CellEnum option) {
        Cell cell = new Cell();
        cell.walkable = (option != CellEnum.CELULA_PAREDE);
        cell.cellType = option;
        return cell;
    }
    
}