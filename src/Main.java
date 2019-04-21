import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Ambiente a = new Ambiente();
        a.carregaArquivo("labirintos/labirinto3_20.txt");

        while (a.getAgente().getTemperatura() > 1) {
            //a.printMapa();
            a.getAgente().achaSaida();
        }
        a.printMapa();
        System.out.println(a.getAgente().getLugaresVisitados().size());
        ArrayList<Ponto> caminhoEstrela = a.aEstrela();
        for (Ponto p:
             caminhoEstrela) {
            System.out.println(p);
        }
        System.out.println("Passos: " + caminhoEstrela.size());
    }


}
