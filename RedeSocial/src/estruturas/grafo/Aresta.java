package estruturas.grafo;

/**
 * Created by gustavo on 14/06/2017.
 */
public class Aresta {
     // Para implementações futuras.. por enquanto apenas um construtor e se é um.
    // Para ser usado na matriz de adjacencia..
    boolean loop;
    int valor = 0;
    Vertice destino;
    public Aresta(){
        loop = false;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
