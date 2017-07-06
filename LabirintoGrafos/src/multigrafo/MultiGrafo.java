package multigrafo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

public class MultiGrafo implements InterfaceMultigrafo {
    private int qtdVertices;
    private List<Vertices> vertices;
    protected List<Arestas> matrizAdj[][];

    public MultiGrafo () {
        qtdVertices = 0;
        vertices = new ArrayList<>();
    }

    public void inserirVertice(Vertices vertice) {
        int novaQtdLinhasColunas = this.ordem() + 1;
        List<Arestas> novaMatriz[][] = (ArrayList<Arestas>[][])
                new ArrayList[novaQtdLinhasColunas][novaQtdLinhasColunas];
        for (int i = 0; i < this.ordem(); i++) {
            System.arraycopy(matrizAdj[i], 0, novaMatriz[i], 0, this.ordem());
        }
        this.matrizAdj = novaMatriz;
        vertices.add(vertice);
        qtdVertices++;
    }

    public void removerVertice(Vertices vertice) {
        qtdVertices--;
        List<Arestas> novaMatriz[][] = (ArrayList<Arestas>[][])
                new ArrayList[qtdVertices][qtdVertices];
        int indice = achaIndice(vertice.getChave());
        vertices.remove(vertice);
        int colunaChecar = 0, linhaChecar = 0;
        for (int i = 0; i < qtdVertices; i++) {
            linhaChecar = 0;
            for (int j = 0; j < qtdVertices; j++) {
                if (i != indice && j != indice) {
                    novaMatriz[colunaChecar][linhaChecar] = matrizAdj[i][j];
                    linhaChecar++;
                }
            }
            if (i != indice) {
                colunaChecar++;
            }
        }
        matrizAdj = novaMatriz;
    }

    public Arestas insereAresta(Vertices verticeOrigem, Vertices verticeDestino,
            double valor) {
        return this.insereArestaMatriz(new Arestas(verticeOrigem, verticeDestino, valor), verticeOrigem, verticeDestino);
    }

    public Arestas insereAresta(Vertices verticeOrigem, Vertices verticeDestino) {
        return this.insereArestaMatriz(new Arestas(verticeOrigem, verticeDestino), verticeOrigem, verticeDestino);
    }

    private Arestas insereArestaMatriz(Arestas A, Vertices verticeOrigem, Vertices verticeDestino) {
        int ind1 = achaIndice(verticeOrigem.getChave());
        int ind2 = achaIndice(verticeDestino.getChave());
        if (matrizAdj[ind1][ind2] == null) {
            matrizAdj[ind1][ind2] = new ArrayList<>();
        }
        if (matrizAdj[ind2][ind1] == null) {
            matrizAdj[ind2][ind1] = new ArrayList<>();
        }
        matrizAdj[ind1][ind2].add(A);
        if (ind1 != ind2) matrizAdj[ind2][ind1].add(A);
        return A;
    }

    public void removeAresta(Arestas Aresta) {
        int ind1 = achaIndice(Aresta.getVerticeOrigem().getChave());
        int ind2 = achaIndice(Aresta.getVerticeDestino().getChave());
        matrizAdj[ind1][ind2].remove(Aresta);
        matrizAdj[ind2][ind1].remove(Aresta);
        nulificarIndiceVazio(ind1, ind2);
        nulificarIndiceVazio(ind2, ind1);
    }

    public Arestas insereArco(Vertices verticeOrigem, Vertices verticeDestino, double valor) {
        return this.insereArcoIndiceMatriz(new Arestas(verticeOrigem, verticeDestino, valor, true),
                verticeOrigem, verticeDestino);
    }

    public Arestas insereArco(Vertices verticeOrigem, Vertices verticeDestino) {
        return this.insereArcoIndiceMatriz(new Arestas(verticeOrigem, verticeDestino, true),
                verticeOrigem, verticeDestino);
    }

    private Arestas insereArcoIndiceMatriz(Arestas A, Vertices verticeOrigem, Vertices verticeDestino) {
        return insereArestaMatriz(A, verticeOrigem, verticeDestino);
    }

    public void removeArco(Arestas aresta) {
        int indiceVerticeOrigem = achaIndice(aresta.getVerticeOrigem().getChave());
        int indiceVerticeDestino = achaIndice(aresta.getVerticeDestino().getChave());
        matrizAdj[indiceVerticeOrigem][indiceVerticeDestino].remove(aresta);
        if (!(aresta.isDirecionada())) {
            matrizAdj[indiceVerticeDestino][indiceVerticeOrigem].remove(aresta);
        }
        nulificarIndiceVazio(indiceVerticeDestino, indiceVerticeOrigem);
        nulificarIndiceVazio(indiceVerticeOrigem, indiceVerticeDestino);
    }

    public int grau(Vertices vertice) {
        int grau = 0;
        int indiceVertice = achaIndice(vertice.getChave());
        int qtdLinhas = ordem();
        for (int i = 0; i < qtdLinhas; i++) {
            if (matrizAdj[indiceVertice][i] != null) {
                for (Arestas aresta : matrizAdj[indiceVertice][i]) {
                    if (aresta.getVerticeDestino().equals(vertice) ||
                            aresta.getVerticeOrigem().equals(vertice)) {
                        grau++;
                    }
                }
            }
            if (matrizAdj[i][indiceVertice] != null && i != indiceVertice) {
                for (Arestas aresta : matrizAdj[i][indiceVertice]) {
                    if (aresta.getVerticeDestino().equals(vertice) ||
                            aresta.getVerticeOrigem().equals(vertice)) {
                        grau++;
                    }
                }
            }
        }
        return grau;
    }

    private int achaIndice(int chave) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getChave() == chave) return i;
        }
        return -1;
    }

    public List<Arestas> arestas() {
        List<Arestas> listaDeArestas = new ArrayList<>();
        for (int i = 0; i < qtdVertices; i++) {
            for (int j = 0; j < qtdVertices; j++) {
                if (matrizAdj[i][j] != null) {
                    listaDeArestas.addAll(matrizAdj[i][j]);
                }
            }
        }
        return listaDeArestas;
    }

    public List<Arestas> arestasIncidentes(Vertices vertice) {
        List<Arestas> listaDeArestas = new ArrayList<Arestas>();
        int indiceVertice = achaIndice(vertice.getChave());
        for (int i = 0; i < ordem(); i++) {
            if (matrizAdj[i][indiceVertice] != null) {
                for (Arestas arestas : matrizAdj[i][indiceVertice]) {
                    if (arestas.getVerticeDestino().equals(vertice)) listaDeArestas.add(arestas);
                }
            }
            if (matrizAdj[indiceVertice][i] != null && indiceVertice != i) {
                for (Arestas arestas : matrizAdj[indiceVertice][i]) {
                    if (arestas.getVerticeDestino().equals(vertice)) listaDeArestas.add(arestas);
                }
            }
        }
        return listaDeArestas;
    }

    public List<Vertices> finalVertices(Arestas a) {
        List<Vertices> listaDeVertices = new ArrayList<>();
        listaDeVertices.add(a.getVerticeOrigem());
        listaDeVertices.add(a.getVerticeDestino());
        return listaDeVertices;
    }

    public Vertices oposto(Vertices v, Arestas a) throws Exception {
        if (v.equals(a.getVerticeDestino())) {
            return a.getVerticeOrigem();
        } else if (v.equals(a.getVerticeOrigem())) {
            return a.getVerticeOrigem();
        }
        throw new Exception("O vértice não tem um oposto para a aresta fornecida.");
    }

    public boolean isAdjacente(Vertices v, Vertices w) {
        int ind1 = achaIndice(v.getChave());
        int ind2 = achaIndice(w.getChave());
        return (matrizAdj[ind1][ind2]) != null;
    }

    public List<Arestas> getArestas(Vertices v, Vertices w) {
        int ind1 = achaIndice(v.getChave());
        int ind2 = achaIndice(w.getChave());
        return (matrizAdj[ind1][ind2]);
    }

    public void mostraVertices(List<Vertices> vertices) {
        String strBuilder = "";
        for (Vertices vertice : vertices) {
            strBuilder += String.format("[%d=%.2f] - ", vertice.getChave(), vertice.getValor());
        }
        System.out.print(strBuilder.substring(0, strBuilder.length()-2));
    }

    public void mostraMatriz(String nomeDoArquivo) {
        String strBuilder = "<head><style>#myCanvas {z-index: -1; width: 100%; height: 90vh; position: absolute;} table {border-spacing: 10px;border-collapse: separate;} table * {cursor: pointer; border: 1px solid black} td {padding: 16px;}</style><body><canvas id='myCanvas'></canvas><center><table><tbody>";
        for (int i = 0; i < this.ordem(); i++) {
            strBuilder += "<tr>";
            for (int j = 0; j < this.ordem(); j++) {
                strBuilder += "<td id='" + String.format("%d,%d", i,j) + "' onclick='mostrarArestasJanela(this)' onmouseover='mostrarArestasColoridas(this)' onmouseout='sumirArestasColoridas()' arestas='";
                if (matrizAdj[i][j] != null) {
                    for (Arestas arestas : matrizAdj[i][j]) {
                        strBuilder += String.format("%d-%d#%s%.2f;", achaIndice(arestas.getVerticeOrigem().getChave()), 
                                achaIndice(arestas.getVerticeDestino().getChave()), arestas.isDirecionada() ? "D" : "B",
                                arestas.getValor());
                    }
                    strBuilder += "'>•</td>";
                } else {
                    strBuilder += "'>&nbsp;</td>";
                }
            }
            strBuilder += "</tr>";
        }
        strBuilder += "</tbody></table><br><button onclick='toggleNulos()'>MOSTRAR/ESCONDER NULOS</button></center><script src='script.js'></script></body>";
        saveToFile(strBuilder, nomeDoArquivo);
    }
    
    private void saveToFile(String fileContents, String fileName) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(fileName), "utf-8"))) {
            writer.write(fileContents);
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String isEuleriano(){
        int qtd_impar, qtd_linhas_impares = 0;
        String retorno = "Não é Euleriano";
        for(int i = 0; i < this.ordem(); i++){
            qtd_impar = 0;
            for(int j = 0; j < this.ordem(); j++){
                if(matrizAdj[i][j] != null) qtd_impar += matrizAdj[i][j].size();
            }
            if(qtd_impar % 2 != 0) qtd_linhas_impares++;
        }
        if(qtd_linhas_impares == 0)
            retorno = "É um ciclo Euleriano";
        else if(qtd_linhas_impares == 2)
            retorno = "É um caminho Euleriano";        
        return retorno;        
    }
    
    private void nulificarIndiceVazio (int i, int j) {
        if (matrizAdj[i][j] != null && matrizAdj[i][j].isEmpty()) matrizAdj[i][j] = null;
    }
    
    public int ordem() {
        return qtdVertices;
    }
    
    public List<Vertices> vertices() {
        return vertices;
    }
}
