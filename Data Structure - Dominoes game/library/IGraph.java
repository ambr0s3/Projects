package library;

/**
 * Created by AS
 */
public interface IGraph<T> {
    boolean isEmpty();
    int numVertices();

    int numEdges() throws Exception;
    boolean hasVertex(T v) throws Exception;
    boolean hasEdge(T v1, T v2) throws Exception;

    void addVertex(T v) throws Exception;
    void addEdge(T v1, T v2, int weight) throws Exception;

    void removeVertex(T v) throws Exception;
    void removeEdge(T v1, T v2) throws Exception;

    void replaceVertex(T v1, T v2) throws Exception;

    IList<T> getAdjacents(T v) throws Exception;
    IList<T> depthFirstSearch() throws Exception;
    IList<T> breadthFirstSearch() throws Exception;
    boolean isConnected() throws Exception;
    IList<T> shortestPath(T v1, T v2);
    IGraph<T> clone() throws Exception;
}
