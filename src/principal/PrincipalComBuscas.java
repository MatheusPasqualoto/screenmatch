package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErroDeConversaoDeAno;
import modelos.Titulo;
import modelos.TituloOMDB;

import java.awt.image.TileObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBuscas {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        while(!busca.equalsIgnoreCase("sair")) {


            System.out.println("Digite um filme para busca: ");
            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase("sair")) {
                break;
            }

            String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=8731b53c";
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);


                //Titulo novoTitulo = gson.fromJson(json, Titulo.class);
                TituloOMDB tituloOMDB = gson.fromJson(json, TituloOMDB.class);
                System.out.println(tituloOMDB);

                Titulo testeTitulo = new Titulo(tituloOMDB);
                System.out.println("");
                System.out.println("********************Titulo convertido********************");
                System.out.println(testeTitulo);
                System.out.println("");

                titulos.add(testeTitulo);

            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Verifique o nome pesquisado: ");
                System.out.println(e.getMessage());
            } catch (ErroDeConversaoDeAno e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(titulos);

        FileWriter escritaArquivo = new FileWriter("filmes.json");
        escritaArquivo.write(gson.toJson(titulos));
        escritaArquivo.close();
        System.out.println("Programa finalizado corretamente!");

    }
}
