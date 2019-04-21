public class Ponto {
    private int x;
    private int y;
    private int valor;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        Ponto pos = (Ponto) o;
        if (x == pos.getX() && y == pos.getY())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Position {x: " + y + ", y: " + x + "} ";
    }
}
