package estruturas.grafo;

/**
 * Created by gustavo on 14/06/2017.
 */
public class Vertice {
    int chave;
    String valor;
    public Vertice(int chave, String valor){
        this.chave = chave;
        this.valor = valor;
    }
    @Override
    public String toString() {
        return "["+chave+"] "+ valor;
    }
    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
