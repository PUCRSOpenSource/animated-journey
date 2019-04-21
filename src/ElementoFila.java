public class ElementoFila {
    private Ponto position;
    private ElementoFila parent;
    private int value;
    private int priority;

    public ElementoFila(Ponto position, int value) {
        this.position = position;
        this.value = value;
    }

    public Ponto getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public ElementoFila getParent() {
        return parent;
    }

    public void setParent(ElementoFila parent) {
        this.parent = parent;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        ElementoFila qe = (ElementoFila) obj;
        return position.equals(qe.getPosition());
    }
}

