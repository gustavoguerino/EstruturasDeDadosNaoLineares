package redesocial;

import estruturas.grafo.Grafo;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RedeSocial s1 = new RedeSocial();
        s1.inserirPessoa(1, "Pedro");
        s1.inserirPessoa(2, "Jose");
        s1.inserirPessoa(3, "Matheus");
        s1.inserirPessoa(4, "Leandro");
        s1.inserirPessoa(5, "Camila");
        s1.inserirPessoa(6, "Bruna");
        /*
        Peso das relacoes:
            Peso(Alvo,Amigo) * (Peso(Amigo, AmigoDoAmigo)*Peso(AmigoDoAmigo, Amigo)
        
        Objetivo: Gerar um valor sobre as relacoes do alvo.
        
        Teste 1 - ID 1 com amizade apenas com id 2 (Peso 5)
        ID 2 Amigo de todos os outros com peso diferente nas relacoes.
        
        
        
        
        OBS: RedeSocial.inserirAmizade(IDOrigem, pesoAmizadeParaOrigem, IDAmigo, pesoAmizadeParaAmigo); 
        
        Usando arestas valoradas, ou seja cada relação tem um peso, 
        eu posso considerar uma pessoa como 4, e e pessoa me considerar 1..
        
        Valores negativos para inimizade..
        
        
        Considero no calculo apenas o peso da relação do amigo sobre o amigo dele, e não o contrario.
        
        */
        s1.inserirAmizade(1, 5, 2, 5); 
        
        s1.inserirAmizade(2, 5, 3, 5);
        s1.inserirAmizade(2, 4, 4, 5);
        s1.inserirAmizade(2, 3, 5, 5);
        s1.inserirAmizade(2, 2, 6, 5);
        System.out.println("Amizade sugerida = " + s1.calcularAmizades(1));
    }

}

