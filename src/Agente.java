import java.util.ArrayList;
import java.util.Random;

public class Agente {

    private Ponto posicao;
    private Direcao direcao;
    private Ambiente ambiente;
    private ArrayList<Ponto> lugaresVisitados;

    public Agente(Ponto posicao, Ambiente ambiente) {
        this.posicao = posicao;
        lugaresVisitados = new ArrayList<>();
        lugaresVisitados.add(posicao);
        this.ambiente = ambiente;
        direcao = Direcao.NORTH;
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
        ArrayList<Ponto> posicoesVizinhas = ambiente.getPosicoesVizinhas(posicao);
        for (Ponto p :
                posicoesVizinhas) {
            p.setValor(funcaoHeuristica(p));
        }
        Ponto candidato = proximaPosicao(posicoesVizinhas);

        if (funcaoHeuristica(posicao) > funcaoHeuristica(candidato))
            move(candidato);
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
}
