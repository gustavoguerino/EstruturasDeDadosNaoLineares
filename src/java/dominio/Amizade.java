package dominio;

import matriz_adjacencia.Vertices;

public class Amizade implements Comparable<Amizade> {

    public Double getValorAmizade() {
        return valorAmizade;
    }

    public void setValorAmizade(Double valorAmizade) {
        this.valorAmizade = valorAmizade;
    }

    public Vertices getPessoaOrigem() {
        return pessoaOrigem;
    }

    public void setPessoaOrigem(Vertices pessoaOrigem) {
        this.pessoaOrigem = pessoaOrigem;
    }

    public Vertices getPessoaDestino() {
        return pessoaDestino;
    }

    public void setPessoaDestino(Vertices pessoaDestino) {
        this.pessoaDestino = pessoaDestino;
    }
    private Double valorAmizade;
    private Vertices pessoaOrigem;
    private Vertices pessoaDestino;
    
    public Amizade(Vertices pessoaOrigem, Vertices pessoaDestino, Double valorAmizade) {
        this.pessoaDestino = pessoaDestino;
        this.pessoaOrigem = pessoaOrigem;
        this.valorAmizade = valorAmizade;
    }

    @Override
    public int compareTo(Amizade otherAmizade) {
        return otherAmizade.getValorAmizade().compareTo(this.getValorAmizade());
    }
}
