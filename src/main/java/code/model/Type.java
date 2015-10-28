package code.model;

/**
 * Created by eric on 10/27/15.
 */
public class Type {
    /**
     * IMAGE DEFINITIONS
     */
    public static final int emptyTile = 0;
    public static final int emptyTileBottomEdge = 1;
    public static final int emptyTileBottomRightEdge = 2;
    public static final int emptyTileRightEdge = 3;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                '}';
    }
}
