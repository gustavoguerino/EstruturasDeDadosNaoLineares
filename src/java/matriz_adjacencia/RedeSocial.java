package matriz_adjacencia;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dominio.Amizade;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class RedeSocial {

    protected static Random randomGenerator;
    public static int qtdPessoas;
    public static GrafoSimples grafo;
    public static HashMap<Vertices, ArrayList<Arestas>> amizades;
    public static int maxSuggestions;

    /*
        VÉRTICES SÃO AS PESSOAS
        ARESTAS SÃO AS RELAÇÕES DE AMIZADES
     */
    public static void initialize() {
        amizades = new HashMap<>();
        randomGenerator = new Random();
        qtdPessoas = 0;
        grafo = new GrafoSimples();
        maxSuggestions = 6;
    }

    public static void criarGrafo() throws IOException {
        initialize();
        String fileContents = new String(Files.readAllBytes(
                Paths.get("/home/albert/EstruturasDeDadosNaoLineares/grafo2.txt")
        ), StandardCharsets.UTF_8);
        int fileContentsLength = fileContents.length();
        ArrayList<String> nomes = gerarNomes();

        for (int i = 0; i < nomes.size(); i++) {
            grafo.inserirVertice(new Vertices(i, nomes.get(i)));
        }
        int i = 0;
        int j = 0;
        for (int k = 0; k < fileContentsLength; k++) {
            Character character = fileContents.charAt(k);
            if (character == ' ') {
                continue;
            }
            if (character != '\n') {
                if (character != '0') {
                    grafo.insereArco(grafo.vertices().get(i), grafo.vertices().get(j), Double.parseDouble(fileContents.charAt(k) + ""));
                }
                i++;
            } else {
                i = 0;
                j++;
            }
        }
        construirAmizades();
    }

    protected static void construirAmizades() {
        for (int i = 0; i < grafo.ordem(); i++) {
            Vertices vertices = grafo.vertices().get(i);
            ArrayList<Arestas> amizadesSet = new ArrayList<>();
            for (int j = 0; j < grafo.ordem(); j++) {
                if (grafo.matrizAdj[i][j] != null) {
                    amizadesSet.add(grafo.matrizAdj[i][j]);
                }
            }
            Collections.sort(amizadesSet, new Comparator<Arestas>() {
                @Override
                public int compare(Arestas a1, Arestas a2) {
                    return a2.getValor().compareTo(a1.getValor());
                }
            });
            amizades.put(vertices, amizadesSet);
        }
    }

    // Sugere novas amizades com base nas amizades dos amigos
    public static ArrayList<Amizade> sugerirAmizades(int idUsuario) throws IOException {
        if (grafo == null) {
            criarGrafo();
        }
        int verticesSize = grafo.vertices().size();
        ArrayList<Amizade> sugestoesAmigos = new ArrayList<>();
        Vertices usuario = grafo.vertices().get(grafo.achaIndice(idUsuario));
        ArrayList<Arestas> amizadesUsuario = amizades.get(usuario);
        if (amizadesUsuario.isEmpty()) {
            // Não tem amigos. Sugestão aleatória!
            ArrayList<Vertices> usuariosAleatorios = new ArrayList<>(grafo.vertices());
            int i = 0;
            while (i < maxSuggestions && !usuariosAleatorios.isEmpty()) {
                Vertices vTemp = grafo.vertices().get(randomGenerator.nextInt(verticesSize));
                if (!usuariosAleatorios.contains(vTemp) && vTemp.getChave() != idUsuario) {
                    sugestoesAmigos.add(new Amizade(usuario, vTemp, 0.0));
                    i++;
                }
                usuariosAleatorios.remove(vTemp);
                verticesSize--;
            }
        } else {
            int i = 0;
            Iterator<Arestas> iterAmizades = algoritmoSugestoesAmigos(amizadesUsuario).iterator();
            while (i < maxSuggestions && iterAmizades.hasNext()) {
                Arestas arestasTmp = iterAmizades.next();
                sugestoesAmigos.add(new Amizade(arestasTmp.getVerticeOrigem(), arestasTmp.getVerticeDestino(), arestasTmp.getValor()));
                i++;
            }
        }
        return sugestoesAmigos;
    }

    private static ArrayList<Arestas> algoritmoSugestoesAmigos(ArrayList<Arestas> amigos) {
        ArrayList<Vertices> amigosJaAdicionados = new ArrayList<>();
        HashMap<Vertices, Arestas> filtroAmizades = new HashMap<>();
        amigos.forEach((arestas) -> {
            ArrayList<Arestas> innerFiltroAmizades
                    = amizades.get(grafo.vertices().get(grafo.achaIndice(arestas.getVerticeDestino().getChave())));
            innerFiltroAmizades.forEach((innerArestas) -> {
                //if (!amigosJaAdicionados.contains(innerArestas.getVerticeDestino())) {
                filtroAmizades.computeIfPresent(innerArestas.getVerticeDestino(), (k,v) -> v);
                /*    amigosJaAdicionados.add(innerArestas.getVerticeDestino());
                }*/
            });
        });
        ArrayList<Arestas> list = new ArrayList<>();
        list.addAll(filtroAmizades.values());
        Collections.sort(list, new Comparator<Arestas>() {
            @Override
            public int compare(Arestas a1, Arestas a2) {
                return a2.getValor().compareTo(a1.getValor());
            }
        });
        return list;
    }

    public void deletarUsuario(int idUsuario) {
        Vertices verticeRemover = grafo.vertices().get(grafo.achaIndice(idUsuario));
        amizades.remove(verticeRemover);
        grafo.removerVertice(verticeRemover);
    }

    private static ArrayList<String> gerarNomes() {
        ArrayList<String> nomes = new ArrayList<>();
        try {
            URL url = new URL("http://www.wjr.eti.br/nameGenerator/index.php?q=10&o=json");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JsonArray jsonArray = new Gson().fromJson(reader, JsonArray.class);
            for (JsonElement element : jsonArray) {
                nomes.add(element.toString().replace("\"", ""));
            }
        } catch (Exception exception) {
            nomes = new ArrayList<>();
            nomes.add("Carina Veríssimo");
            nomes.add("Constantino Castro");
            nomes.add("Dino Cordero");
            nomes.add("Edgar Fortunato");
            nomes.add("Janaína Soares");
            nomes.add("Rogério Alves");
            nomes.add("Albert Almeida Morato");
            nomes.add("Ítalo Moura");
            nomes.add("Guilherme Moura");
            nomes.add("Robinson Alves");
        }
        return nomes;
    }
}
