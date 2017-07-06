package multigrafo;

public interface InterfaceArestas {
    public abstract Vertices getVerticeDestino();

    public abstract void setVerticeDestino(Vertices verticeDestino);

    public abstract Vertices getVerticeOrigem();

    public abstract void setVerticeOrigem(Vertices verticeOrigem);

    public abstract boolean isDirecionada();

    public abstract void setDirecionada(boolean direcionada);

    public abstract double getValor();

    public abstract void setValor(double valor);
}