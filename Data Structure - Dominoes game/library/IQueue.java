package library;

/**
 * Created by AS
 */
public interface IQueue<T> {

    T dequeue() throws Exception;
    void enqueue(T object);
    T front() throws Exception;
    T back() throws Exception;
    boolean isEmpty();
}
