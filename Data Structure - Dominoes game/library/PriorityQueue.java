package library;

/**
 * Created by AS
 */
public interface PriorityQueue<T> {
    void insert(T x);
    T getMin();
    T extractMin();
    boolean isEmpty();
}
