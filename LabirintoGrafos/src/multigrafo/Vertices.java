package multigrafo;

public class Vertices implements InterfaceVertices {
    private String chave;
    private double valor;

    public Vertices(String chave, double valor) {
        this.chave = chave;
        this.valor = valor;
    }
    
    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
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
