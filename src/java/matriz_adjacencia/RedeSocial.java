package matriz_adjacencia;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class RedeSocial {

    protected static Random randomGenerator;
    public static int qtdPessoas;
    public static GrafoSimples grafo;
    public static HashMap<Vertices, TreeSet<Arestas>> amizades;
    public static int maxSuggestions;

    public static void initialize() {
        amizades = new HashMap<>();
        randomGenerator = new Random();
        qtdPessoas = 0;
        grafo = new GrafoSimples();
        maxSuggestions = 6;
    }

    /*
        VÉRTICES SÃO AS PESSOAS
        ARESTAS SÃO AS RELAÇÕES DE AMIZADES
     */

    public static void criaGrafo() throws IOException {
        initialize();
        String fileContents = new String(Files.readAllBytes(
                Paths.get("/home/joaopaulo/NetBeansProjects/REDE-SOCIAL-TESTE/grafo2.txt")
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
            TreeSet<Arestas> amizadesSet = new TreeSet<>();
            for (int j = 0; j < grafo.ordem(); j++) {
                if (grafo.matrizAdj[i][j] != null) {
                    amizadesSet.add(grafo.matrizAdj[i][j]);
                }
            }
            amizades.put(vertices, amizadesSet);
        }
    }

    // Sugere novas amizades com base nas amizades dos amigos
    public static ArrayList<Vertices[]> sugerirAmizades(int idUsuario) {
        int verticesSize = grafo.vertices().size();
        ArrayList<Vertices[]> sugestoesAmigos = new ArrayList<>();
        if (verticesSize == 1) {
            // Só tem um usuário no sistema
            return sugestoesAmigos;
        }
        Vertices usuario = grafo.vertices().get(grafo.achaIndice(idUsuario));
        TreeSet<Arestas> amizadesUsuario = amizades.get(usuario);
        if (amizadesUsuario.isEmpty()) {
            // Não tem amigos. Sugestão aleatória!
            ArrayList<Vertices> usuariosAleatorios = new ArrayList<>(grafo.vertices());
            int i = 0;
            while (i < maxSuggestions && !usuariosAleatorios.isEmpty()) {
                Vertices vTemp = grafo.vertices().get(randomGenerator.nextInt(verticesSize));
                if (!usuariosAleatorios.contains(vTemp) && vTemp.getChave() != idUsuario) {
                    sugestoesAmigos.add(new Vertices[] { usuario, vTemp });
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
                sugestoesAmigos.add(new Vertices[]{arestasTmp.getVerticeOrigem(), arestasTmp.getVerticeDestino()});
                i++;
            }
        }
        return sugestoesAmigos;
    }

    private static TreeSet<Arestas> algoritmoSugestoesAmigos(TreeSet<Arestas> amigos) {
        TreeSet<Arestas> filtroAmizades = new TreeSet<>();
        amigos.forEach((arestas) -> {
            TreeSet<Arestas> innerFiltroAmizades
                    = amizades.get(grafo.vertices().get(grafo.achaIndice(arestas.getVerticeDestino().getChave())));
            innerFiltroAmizades.forEach((innerArestas) -> {
                if (filtroAmizades.contains(innerArestas)) {
                    filtroAmizades.add(innerArestas);
                }
            });
        });
        return filtroAmizades;
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
                nomes.add(element.toString());
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
