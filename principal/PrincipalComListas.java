package principal;

import modelos.Filme;
import modelos.Serie;
import modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalComListas {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("O poderoso chefão", 1970);
        meuFilme.avalia(9);
        Filme outroFilme = new Filme("Avatar", 2023);
        outroFilme.avalia(6);
        var filmeDoPaulo = new Filme("Dogville", 2003);
        filmeDoPaulo.avalia(10);
        Serie lost = new Serie("Lost", 2000);

        Filme f1 = filmeDoPaulo;

        List<Titulo> listaFilmes = new ArrayList<>();
        listaFilmes.add(filmeDoPaulo);
        listaFilmes.add(meuFilme);
        listaFilmes.add(outroFilme);
        listaFilmes.add(lost);
        for (Titulo item: listaFilmes) {
            System.out.println(item.getNome());
            if (item instanceof  Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação " + filme.getClassificacao());
            }

        }

        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("ArtistaC");
        buscaPorArtista.add("ArtistaB");
        buscaPorArtista.add("ArtistaA");
        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println(buscaPorArtista);

        Collections.sort(listaFilmes);
        System.out.println(listaFilmes);
        listaFilmes.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println(listaFilmes);


    }
}