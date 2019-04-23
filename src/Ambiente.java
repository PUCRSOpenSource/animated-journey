import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Ambiente {

    private Posicao mapa[][];
    private Agente agente;
    private Ponto entrada;
    private Ponto saida;
    private int dimensao;

    private int offsetMove[][] = {
                      {-1, 0},
            { 0, -1},          { 0, +1},
                      {+1, 0}};

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
                            mapa[i][j] = Posicao._;
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
    }

    public void moveAgente(Ponto de, Ponto para) {
        mapa[de.getY()][de.getX()] = Posicao._;
        mapa[entrada.getY()][entrada.getX()] = Posicao.E;
        mapa[saida.getY()][saida.getX()] = Posicao.S;
        mapa[para.getY()][para.getX()] = Posicao.A;
    }

    public Agente getAgente() {
        return agente;
    }

    public ArrayList<Ponto> getPosicoesVizinhas(Ponto posicao) {
        ArrayList<Ponto> possibleMoves = new ArrayList<>();
        for (int[] off :
                offsetMove) {
            int x = posicao.getX() + off[0];
            if (x < 0 || x >= dimensao)
                continue;
            int y = posicao.getY() + off[1];
            if (y < 0 || y >= dimensao)
                continue;
            if (mapa[y][x] != Posicao.P) {
                possibleMoves.add(new Ponto(x, y));
            }
        }
        return possibleMoves;
    }

    public boolean ehSaida(Ponto posicao) {
        return mapa[posicao.getY()][posicao.getX()] == Posicao.S;
    }

    public ArrayList<Ponto> aEstrela(){
        PriorityQueue<ElementoFila> naoOlheiAinda = new PriorityQueue<>(new ElementoFilaComparador());
        naoOlheiAinda.add(new ElementoFila(entrada, 0));
        HashSet<ElementoFila> visitados = new HashSet<>();
        while (naoOlheiAinda.peek() != null && !naoOlheiAinda.peek().getPosition().equals(saida)) {
            ElementoFila potencialmenteMelhorCanditado = naoOlheiAinda.poll(); // retira o primeiro elemento (de maior prioridade = menor valor)
            visitados.add(potencialmenteMelhorCanditado);
            ArrayList<Ponto> movimentosPossiveis = getPosicoesVizinhas(potencialmenteMelhorCanditado.getPosition());
            for (Ponto vizinho :
                    movimentosPossiveis) {
                int cost = potencialmenteMelhorCanditado.getValue() + 1;
                ElementoFila vizinhoElementoFila = new ElementoFila(vizinho, cost);
                if (aindaNaoOlheiMasAcheiMaisBarato(naoOlheiAinda, vizinhoElementoFila))
                    naoOlheiAinda.remove(vizinhoElementoFila);
                if (ehCaraNovo(vizinhoElementoFila, naoOlheiAinda, visitados))
                    vizinhoElementoFila.setValue(cost);
                vizinhoElementoFila.setPriority(cost + manhattanDistanceHeuristica(vizinho, saida));
                vizinhoElementoFila.setParent(potencialmenteMelhorCanditado);
                naoOlheiAinda.add(vizinhoElementoFila);
            }
        }
        ElementoFila lastVisited = naoOlheiAinda.peek();
        if (lastVisited == null)
            return new ArrayList<>();
        return reconstructPath(lastVisited);
    }

    private ArrayList<Ponto> reconstructPath(ElementoFila lastVisited) {
        ElementoFila current = lastVisited;
        ArrayList<Ponto> path = new ArrayList<>();
        while (current.getParent() != null) {
            path.add(0, current.getPosition());
            current = current.getParent();
        }
        return path;
    }

    private boolean ehCaraNovo(ElementoFila neighbor, PriorityQueue<ElementoFila> open, HashSet<ElementoFila> closed) {
        return !open.contains(neighbor) && !closed.contains(neighbor);
    }

    private boolean aindaNaoOlheiMasAcheiMaisBarato(PriorityQueue<ElementoFila> open, ElementoFila neighbor) {
        for (ElementoFila qe :
                open) {
            if (qe.getPosition().equals(neighbor.getPosition()) && qe.getValue() > neighbor.getValue())
                return true;
        }
        return false;
    }

    public static int manhattanDistanceHeuristica(Ponto de, Ponto para) {
        return Math.abs(de.getX() - para.getX()) + Math.abs(de.getY() - para.getY());
    }
}
