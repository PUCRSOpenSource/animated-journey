import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Agente {

    private Ponto posicao;
    private Ambiente ambiente;
    private ArrayList<Ponto> lugaresVisitados;
    private double temperatura;
    private double taxaResfriamento;

    public Agente(Ponto posicao, Ambiente ambiente) {
        this.posicao = posicao;
        lugaresVisitados = new ArrayList<>();
        lugaresVisitados.add(posicao);
        this.ambiente = ambiente;
        temperatura = 10000000;
        taxaResfriamento = 0.003;
    }

    public Ponto getPosicao() {
        return posicao;
    }

    public void move(Ponto novaPosicao) {
        ambiente.moveAgente(posicao, novaPosicao);
        posicao = novaPosicao;
        lugaresVisitados.add(posicao);
    }

    /* Logica feliz do algoritmo.*/
    public void achaSaida() {
        if (ambiente.ehSaida(posicao)){
            ambiente.printMapa();
            return;
        }

        ArrayList<Ponto> posicoesVizinhas = ambiente.getPosicoesVizinhas(posicao);
        for (Ponto p :
                posicoesVizinhas) {
            p.setValor(funcaoHeuristica(p));
        }
        Ponto candidato = proximaPosicao(posicoesVizinhas);

        if (funcaoHeuristica(posicao) > funcaoHeuristica(candidato))
            move(candidato);
        else {
            if (Math.exp(funcaoHeuristica(posicao) - funcaoHeuristica(candidato) / temperatura) > ThreadLocalRandom.current().nextDouble())
                move(candidato);
        }
        temperatura *= 1 - taxaResfriamento;
    }

    private int funcaoHeuristica(Ponto p) {
        int ocorrencias = contaOcorrencias(p);
        return 10 * ocorrencias;
    }

    private int contaOcorrencias(Ponto ponto) {
        int cont = 0;
        for (Ponto p :
                lugaresVisitados) {
            if (p.equals(ponto))
                cont++;
        }
        return cont;
    }

    private Ponto proximaPosicao(ArrayList<Ponto> posicoesVizinhas) {
        int valor = posicoesVizinhas.get(0).getValor();

        for (Ponto p :
                posicoesVizinhas) {
            if (p.getValor() < valor)
                valor = p.getValor();
        }

        ArrayList<Ponto> candidatos = new ArrayList<>();
        for (Ponto p :
                posicoesVizinhas) {
            if (p.getValor() == valor)
                candidatos.add(p);
        }

        Random rand = new Random();
        Ponto candidato = candidatos.get(rand.ints(0, candidatos.size()).findFirst().getAsInt());

        return candidato;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public ArrayList<Ponto> getLugaresVisitados() {
        return lugaresVisitados;
    }
}
