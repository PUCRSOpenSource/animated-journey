import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Ambiente a = new Ambiente();
        a.carregaArquivo("labirintos/labirinto1_10.txt");

        boolean mostraMapa = true;
        Scanner scanner = new Scanner(System.in);
        while (a.getAgente().getTemperatura() > 1) {
            if (mostraMapa) {
                a.printMapa();
                String entrada = scanner.nextLine();
                if (entrada.equals("q")) {
                    mostraMapa = false;
                }
            }
            a.getAgente().achaSaida();
        }
        System.out.println("Onde o agente parou: ");
        a.printMapa();
        System.out.println("Numero de passos dados: " + a.getAgente().getLugaresVisitados().size() + "\n\n");
        System.out.println("Caminho no A estrela: ");
        ArrayList<Ponto> caminhoEstrela = a.aEstrela();
        for (Ponto p :
                caminhoEstrela) {
            System.out.println(p);
        }
        System.out.println("Passos: " + caminhoEstrela.size());
    }
}
