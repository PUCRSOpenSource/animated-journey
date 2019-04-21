

public class Main {

    public static void main(String[] args) {
        Ambiente a = new Ambiente();
        a.carregaArquivo("labirintos/labirinto1_10.txt");

        while (true) {
            a.printMapa();
            a.getAgente().achaSaida();
        }

    }


}
