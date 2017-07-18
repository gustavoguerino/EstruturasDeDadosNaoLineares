package matriz_adjacencia;

public class Vertices {
    private int chave;
    private String nome;

    public Vertices(int chave, String nome) {
        super();
        this.chave = chave;
        this.nome = nome;
    }
    
    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }
    
    public String getValor() {
        return nome;
    }

    public void setValor(String nome) {
        this.nome = nome;
    }
}
