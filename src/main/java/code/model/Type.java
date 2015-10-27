package code.model;

/**
 * Created by eric on 10/27/15.
 */
public class Type {
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
