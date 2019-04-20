import java.util.ArrayList;
import java.util.Random;

public enum Direcao {
    NORTH, SOUTH, WEST, EAST;

    private int[] offset;

    static {
        NORTH.offset = new int[]{+1, 0};
        EAST.offset = new int[]{0, +1};
        SOUTH.offset = new int[]{+1, 0};
        WEST.offset = new int[]{0, -1};
    }

    public int[] getOffset() {
        return offset;
    }

    public static Direcao randomDirection() {
        ArrayList<Direcao> possibleDirections = new ArrayList<>();
        possibleDirections.add(Direcao.NORTH);
        possibleDirections.add(Direcao.EAST);
        possibleDirections.add(Direcao.SOUTH);
        possibleDirections.add(Direcao.WEST);
        Random rand = new Random();
        return possibleDirections.get(rand.nextInt(possibleDirections.size()));
    }

    public static Direcao randomDirection(Direcao dir) {
        ArrayList<Direcao> possibleDirections = new ArrayList<>();
        possibleDirections.add(Direcao.NORTH);
        possibleDirections.add(Direcao.EAST);
        possibleDirections.add(Direcao.SOUTH);
        possibleDirections.add(Direcao.WEST);
        possibleDirections.remove(dir);
        Random rand = new Random();
        return possibleDirections.get(rand.nextInt(possibleDirections.size()));
    }
}
