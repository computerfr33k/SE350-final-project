package code.model;

/**
 * Created by eric on 10/26/15.
 */
public class LayoutMap {
    private Layout layout;

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "LayoutMap{" +
                "layout=" + layout +
                '}';
    }
}
