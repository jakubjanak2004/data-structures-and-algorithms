package dsa.dataStructures.Set;

public class LineEntry<T> {
    private boolean deleted = false;
    private final T value;

    public LineEntry(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
