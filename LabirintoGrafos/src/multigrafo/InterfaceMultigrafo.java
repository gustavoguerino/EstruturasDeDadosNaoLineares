package multigrafo;

import java.util.List;
import java.util.Vector;

public interface InterfaceMultigrafo {
    public abstract void inserirVertice(Vertices Vertice);

    public abstract void removerVertice(Vertices Vertice);

    public abstract Arestas insereAresta(Vertices VerticeUm, Vertices VerticeDois,
                                         double valor);

    public abstract Arestas insereAresta(Vertices VerticeUm, Vertices VerticeDois);

    public abstract void removeAresta(Arestas Aresta);

    public abstract Arestas insereArco(Vertices VerticeUm, Vertices VerticeDois,
                                       double valor);

    public abstract Arestas insereArco(Vertices VerticeUm, Vertices VerticeDois);

    public abstract void removeArco(Arestas Aresta);

    public abstract int grau(Vertices Vertice);

    public abstract int ordem();

    public abstract List<Vertices> vertices();

    public abstract List<Arestas> arestas();

    public abstract List<Arestas> arestasIncidentes(Vertices vertice);

    public abstract List<Vertices> finalVertices(Arestas a);

    public abstract Vertices oposto(Vertices v, Arestas a) throws Exception;

    public abstract boolean isAdjacente(Vertices v, Vertices w);
}