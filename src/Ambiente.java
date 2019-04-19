import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ambiente {

    private Posicao mapa[][];


    public void carregaArquivo(String nomeArquivo){
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(nomeArquivo))) {

            String line;

            line = br.readLine();
            int dimensao = Integer.parseInt(line);
            mapa = new Posicao[dimensao][dimensao];
            int i = 0;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                String[] array = line.split(" ");
                int j = 0;
                for (String e : array){
                    switch (e) {
                        case "0":
                            mapa[i][j] = Posicao.V;
                            break;
                        case "1":
                            mapa[i][j] = Posicao.P;
                            break;
                        case "E":
                            mapa[i][j] = Posicao.E;
                            break;
                        case "S":
                            mapa[i][j] = Posicao.S;
                            break;
                    }
                    j++;
                }
                i++;
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        System.out.println(sb);


    }
}
