package library;

/**
 * Created by AS
 */
public interface IList<T> extends Iterable<T>{

    int size();
    boolean isEmpty();
    void clear();

    T first();

    T last();

    void addFirst(T object);
    void addLast(T object);
    void insert(int position, T object) throws Exception;
    T get(int position) throws Exception;
    void remove(int position) throws Exception;
    int find(T object);

    String toString();

    void pop() throws Exception;
}
