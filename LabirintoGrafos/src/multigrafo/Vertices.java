package multigrafo;

public class Vertices implements InterfaceVertices {
    private int chave;
    private double valor;

    public Vertices(int chave, double valor) {
        this.chave = chave;
        this.valor = valor;
    }
    
    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString(){        
        //return "["+chave+" - "+valor+"]";
        return "["+chave+"]";
    }
    
}
