package code.model;

import java.util.ArrayList;

/**
 * Created by eric on 10/26/15.
 */
public class Layout {
    private ArrayList<Cell> cells;

    public ArrayList<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return "Layout{" +
                "cells=" + cells +
                '}';
    }
}
