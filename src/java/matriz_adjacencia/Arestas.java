package matriz_adjacencia;

public class Arestas implements Comparable<Arestas> {
    private Vertices verticeOrigem;
    private Vertices verticeDestino;
    private Double valor;
    private boolean direcionada;
   
    public Arestas(Vertices verticeOrigem, Vertices verticeDestino) {
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        this.valor = 0.0;
        this.direcionada=false;
    }
    
    public Arestas(Vertices verticeOrigem, Vertices verticeDestino, double valor) {
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        this.valor = valor;
        this.direcionada=false;
    }

    public Arestas(Vertices verticeOrigem, Vertices verticeDestino, double valor, boolean direcionada) {
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        this.valor = valor;
        this.direcionada = direcionada;
    }

    public Vertices getVerticeDestino() {
        return verticeDestino;
    }

    public void setVerticeDestino(Vertices verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    public Vertices getVerticeOrigem() {
        return verticeOrigem;
    }

    public void setVerticeOrigem(Vertices verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }

    public boolean isDirecionada() {
        return direcionada;
    }

    public void setDirecionada(boolean direcionada) {
        this.direcionada = direcionada;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Arestas arestas) {
        return arestas.valor.compareTo(this.valor);
    }
    
}
