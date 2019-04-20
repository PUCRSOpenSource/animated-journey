import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ambiente {

    private Posicao mapa[][];
    private Agente agente;
    private Ponto entrada;
    private Ponto saida;
    private int dimensao;


    public void carregaArquivo(String nomeArquivo) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(nomeArquivo))) {

            String line;

            line = br.readLine();
            dimensao = Integer.parseInt(line);
            mapa = new Posicao[dimensao][dimensao];
            int i = 0;
            while ((line = br.readLine()) != null) {
                //sb.append(line).append("\n");
                String[] array = line.split(" ");
                int j = 0;
                for (String e : array) {
                    switch (e) {
                        case "0":
                            mapa[i][j] = Posicao.V;
                            break;
                        case "1":
                            mapa[i][j] = Posicao.P;
                            break;
                        case "E":
                            mapa[i][j] = Posicao.E;
                            entrada = new Ponto(j, i);
                            agente = new Agente(new Ponto(j, i), this);
                            break;
                        case "S":
                            mapa[i][j] = Posicao.S;
                            saida = new Ponto(j, i);
                            break;
                    }
                    j++;
                }
                i++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public void printMapa() {
        Posicao a[][] = mapa.clone();
        a[agente.getPosicao().getY()][agente.getPosicao().getX()] = Posicao.A;
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                System.out.print(a[i][j] + "  ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        agente.move(new Ponto(2,1));
    }

    public void moveAgente(Ponto de, Ponto para) {
        mapa[de.getY()][para.getX()] = Posicao.V;
        mapa[entrada.getY()][entrada.getX()] = Posicao.E;
        mapa[saida.getY()][saida.getX()] = Posicao.S;
        mapa[para.getY()][para.getX()] = Posicao.A;
    }
}
