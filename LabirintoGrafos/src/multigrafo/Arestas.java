package multigrafo;

public class Arestas implements InterfaceArestas {
    private Vertices verticeOrigem;
    private Vertices verticeDestino;
    private double valor;
    private boolean direcionada;
   
    public Arestas(Vertices verticeOrigem, Vertices verticeDestino) {
        super();
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        direcionada=false;
    }
    
    public Arestas(Vertices verticeOrigem, Vertices verticeDestino,
                   double valor) {
        super();
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        this.valor = valor;
        direcionada=false;
    }

    public Arestas(Vertices verticeOrigem, Vertices verticeDestino, boolean direcionada) {
        super();
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        this.valor = valor;
        this.direcionada = direcionada;
    }

    public Arestas(Vertices verticeOrigem, Vertices verticeDestino,
                   double valor, boolean direcionada) {
        super();
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString(){
        //return "["+verticeOrigem+"-"+verticeDestino+":"+valor+"]";        
        return "["+valor+"]";
    }
    
}
