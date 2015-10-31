package code.model;

import java.util.ArrayList;

/**
 * Created by eric on 10/26/15.
 */
public class Layout {
    private ArrayList<BlankTile> cells;

    public ArrayList<BlankTile> getBlankTiles() {
        return cells;
    }

    public void setBlankTiles(ArrayList<BlankTile> blankTiles) {
        this.cells = blankTiles;
    }

    @Override
    public String toString() {
        return "Layout{" +
                "blankTiles=" + cells +
                '}';
    }
}
