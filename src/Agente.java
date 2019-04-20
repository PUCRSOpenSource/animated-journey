public class Agente {

    private Ponto posicao;
    private Direcao direcao;
    private Ambiente ambiente;

    public Agente(Ponto posicao, Ambiente ambiente) {
        this.posicao = posicao;
        this.ambiente = ambiente;
        direcao = Direcao.NORTH;
    }

    public Ponto getPosicao() {
        return posicao;
    }

    public void move(Ponto novaPosicao) {
        ambiente.moveAgente(posicao, novaPosicao);
        posicao = novaPosicao;
    }
}
