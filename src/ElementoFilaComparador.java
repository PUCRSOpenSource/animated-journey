import java.util.Comparator;

public class ElementoFilaComparador implements Comparator<ElementoFila> {
    @Override
    public int compare(ElementoFila qe1, ElementoFila qe2) {
        if (qe1.getPriority() < qe2.getPriority())
            return -1;
        if (qe1.getPriority() > qe2.getPriority())
            return 1;
        return 0;
    }
}
