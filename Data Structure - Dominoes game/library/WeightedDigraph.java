package library;

/**
 * Created by AS
 */
public class WeightedDigraph <T extends Comparable<T>> implements IGraph<T> {
    private static final int UNDEFINED = -1;

    protected IList<T> vertices;
    protected IList<IList<Edge>> adjList;

    public class Edge implements Comparable<Edge> {
        Integer destination;
        Integer weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public Integer getDestination() {
            return destination;
        }

        public void setDestination(int destination) {
            this.destination = destination;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight.compareTo(o.getWeight());
        }
    }

    public WeightedDigraph() {
        vertices = new SimpleLinkedList<>();
        adjList = new SimpleLinkedList<>();
    }

    protected int getPosition(T v) throws Exception {
        for (int i = 0; i < vertices.size(); i++)
            if (vertices.get(i).equals(v))
                return i;
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public int numEdges() throws Exception {
        int count = 0;
        for (int i = 0; i < vertices.size(); i++)
            count += adjList.get(i).size();
        return count;
    }

    @Override
    public boolean hasVertex(T v) throws Exception {
        return getPosition(v) != -1;
    }

    @Override
    public boolean hasEdge(T v1, T v2) throws Exception {
        int p1 = getPosition(v1);
        int p2 = getPosition(v2);

        if ((p1 != -1) && (p2 != -1)) {
            IList<Edge> adj = adjList.get(p1);
            for (int i = 0; i < adj.size(); i++)
                if (adj.get(i).equals(p2))
                    return true;
        }
        return false;
    }

    @Override
    public void addVertex(T v) throws Exception {
        if (!hasVertex(v)) {
            vertices.addLast(v);
            adjList.addLast(new SimpleLinkedList<Edge>());
        }
    }

    @Override
    public void addEdge(T v1, T v2, int weight) throws Exception {
        int p1 = getPosition(v1);
        int p2 = getPosition(v2);

        if ((p1 != -1) && (p2 != -1) && (!hasEdge(v1, v2))) {
            IList<Edge> adj = adjList.get(p1);
            adj.addLast(new Edge(p2, weight));
        } else
            throw new IllegalArgumentException();
    }
    @Override
    public void removeVertex(T v) throws Exception {
        int p = getPosition(v);

        if (p != -1) {
            IList<T> adj = getAdjacents(v);
            for (T vertex : adj) {
                removeEdge(v, vertex);
            }
            adjList.remove(p);
            vertices.remove(p);

            int aux;
            IList<Edge> positions;
            for (int i = 0; i < adjList.size(); i++) {
                positions = adjList.get(i);
                for (int j = 0; j < positions.size(); j++) {
                    Edge edge = positions.get(j);
                    aux = edge.getDestination();
                    int weight = edge.getWeight();
                    if (aux > p) {
                        positions.remove(j);
                        if (!positions.isEmpty())
                            positions.insert(aux - 1, new Edge(j, weight));
                        else
                            positions.addLast(new Edge(aux - 1, weight));
                    }
                }
            }

        } else
            throw new IllegalArgumentException();

    }

    @Override
    public void removeEdge(T v1, T v2) throws Exception {
        int p1 = getPosition(v1);
        int p2 = getPosition(v2);

        if ((p1 != -1) && (p2 != -1) && (hasEdge(v1, v2))) {
            IList<Edge> adj = adjList.get(p1);
            for (int i = 0; i < adj.size(); i++) {
                if (adj.get(i).getDestination() == p2)
                    adj.remove(i);
            }

        } else
            throw new IllegalArgumentException();
    }

    @Override
    public void replaceVertex(T v1, T v2) throws Exception {
        int p = getPosition(v1);
        if (p != -1) {
            vertices.remove(p);
            if (!vertices.isEmpty())
                vertices.insert(p, v2);
            else
                vertices.addLast(v2);
        } else
            throw new IllegalArgumentException();
    }

    @Override
    public IList<T> getAdjacents(T v) throws Exception {
        int p = getPosition(v);
        IList<T> aux = new SimpleLinkedList<>();
        IList<Edge> adj = adjList.get(p);

        int len = adj.size();
        for (int i = 0; i < len; i++) {
            int pos = adj.get(i).getDestination();
            aux.addLast(vertices.get(pos));
        }
        return aux;
    }

    @Override
    public IList<T> depthFirstSearch() throws Exception {
        IList<T> visits = new SimpleLinkedList<>();

        for (int i = 0; i < vertices.size(); i++) {
            if (visits.find(vertices.get(i)) == -1)
                visits = DFS(vertices.get(i), visits);
        }

        return visits;
    }

    private IList<T> DFS(T x, IList<T> visits) throws Exception {
        if (visits.find(x) == -1)
            visits.addLast(x);
        for (int i = 0; i < getAdjacents(x).size(); i++)
            if (visits.find(getAdjacents(x).get(i)) == -1)
                visits = DFS(getAdjacents(x).get(i), visits);
        return visits;
    }

    @Override
    public IList<T> breadthFirstSearch() throws Exception {
        IList<T> result = new SimpleLinkedList<>();
        boolean[] visited = new boolean[numVertices()];
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        IQueue<T> queue = new Queue<>();
        visited[0] = true;
        T first = vertices.get(0);
        queue.enqueue(first);

        while (!queue.isEmpty()) {

            T v = queue.dequeue();

            for (T item : getAdjacents(v)) {
                if (!visited[getPosition(item)]) {
                    visited[getPosition(item)] = true;
                    queue.enqueue(item);
                }
            }

            result.addLast(v);
        }

        return result;
    }

    @Override
    public boolean isConnected() throws Exception {
        return false;
    }

    @Override
    public IList<T> shortestPath(T v1, T v2) {
        return null;
    }

    @Override
    public IGraph<T> clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public String toString() {
        try {
            String s = numVertices() + " Vertices, " + numEdges() + " edges\n";
            for (int i = 0; i < numVertices(); i++) {
                T v = vertices.get(i);
                s += v.toString() + ": ";

                IList<T> adj = getAdjacents(vertices.get(i));
                for (int j = 0; j < adj.size(); j++) {
                    T u = adj.get(j);
                    s += "(" + getEdgeWeight(v, u) + "-" + u + ") ";
                }

                s += "\n";
            }

            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public Integer getEdgeWeight(T v1, T v2) throws Exception 
    {
        int p1 = getPosition(v1);
        int p2 = getPosition(v2);

        if ((p1 != -1) && (p2 != -1)) {
            IList<Edge> adj = adjList.get(p1);
            for (int i = 0; i < adj.size(); i++) {
                Edge u = adj.get(i);

                if (u.getDestination().equals(p2))
                    return u.getWeight();
            }
        }

        return -1;
    }

    

    public void dijkstra(T src) throws Exception {
        //Initialize single source
        int[] dist = new int[numVertices()];
        boolean[] sptSet = new boolean[numVertices()];

        for (int i = 0; i < numVertices(); i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // La distancia hacia el mismo es cero.
        int pos = getPosition(src);
        dist[pos] = 0;
        sptSet[pos] = true;

        // inicializo la distancia de sus vecinos
        for (T v : getAdjacents(src)) {
            int vpos = getPosition(v);
            int weight = getEdgeWeight(src, v);

            if (!sptSet[vpos] && (dist[vpos]) > dist[pos] + weight) {
                dist[vpos] = dist[pos] + weight;
            }
        }

        for (int i = 0; i < numVertices() - 1; i++) {
            T u = getMinDistance(dist, sptSet);
            int upos = getPosition(u);
            sptSet[upos] = true;

            for (T v : getAdjacents(u)) {
                int vpos = getPosition(v);
                int weight = getEdgeWeight(u, v);

                if (!sptSet[vpos] && dist[upos] != Integer.MAX_VALUE &&
                        ((dist[upos] + weight) < dist[vpos]))
                    dist[vpos] = dist[upos] + weight;
            }
        }
        System.out.println("Vertex\tDistance");
        for (int i = 0; i < numVertices(); i++) {
            System.out.printf("%d\t\t%d\n", i, dist[i]);
        }
    }

    public void dijkstra(T src, T dest) throws Exception{
        //Initialize single source
        int UNDEFINED = -1;
        int[] dist = new int[numVertices()];
        int[] prev = new int[numVertices()];
        boolean[] sptSet = new boolean[numVertices()];

        for (int i = 0; i < numVertices(); i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
            prev[i] = UNDEFINED;
        }

        // La distancia hacia el mismo es cero.
        int srcp = getPosition(src);
        dist[srcp] = 0;
        sptSet[srcp] = true;

        // inicio el costo del camino minimo de cada vecino del origen
        for (T v : getAdjacents(src)) {
            int vpos = getPosition(v);
            int weight = getEdgeWeight(src, v);

            if (!sptSet[vpos] && (dist[vpos]) > dist[srcp] + weight) {
                dist[vpos] = dist[srcp] + weight;
                prev[vpos] = srcp;
            }
        }

        for (int i = 0; i < numVertices() - 1; i++) {
            T u = getMinDistance(dist, sptSet);
            int upos = getPosition(u);
            sptSet[upos] = true;


            for (T v : getAdjacents(u)) {
                int vpos = getPosition(v);
                int weight = getEdgeWeight(u, v);

                if (!sptSet[vpos] && dist[upos] != Integer.MAX_VALUE &&
                        ((dist[upos] + weight) < dist[vpos])) {
                    dist[vpos] = dist[upos] + weight;
                    prev[vpos] = upos;
                }
            }
        }

        IList<T> shortestPath = new SimpleLinkedList<>();
        int u = getPosition(dest);

        while(prev[u] != UNDEFINED){
            shortestPath.addFirst(vertices.get(u));
            u = prev[u];
        }

        System.out.print("Shortest Path: ");
        for (T v : shortestPath){
            System.out.print(v + " ");
        }
    }


    public void maxLongestPath(T src, T dest) throws Exception{
        //Initialize single source
        int UNDEFINED = -1;
        int[] dist = new int[numVertices()];
        int[] prev = new int[numVertices()];
        boolean[] sptSet = new boolean[numVertices()];

        for (int i = 0; i < numVertices(); i++) {
            dist[i] = Integer.MIN_VALUE;
            sptSet[i] = false;
            prev[i] = UNDEFINED;
        }

        // La distancia hacia el mismo es cero.
        int srcp = getPosition(src);
        dist[srcp] = 0;
        sptSet[srcp] = true;

        // inicio el costo del camino maximo de cada vecino del origen
        for (T v : getAdjacents(src)) {
            int vpos = getPosition(v);
            int weight = getEdgeWeight(src, v);

            if (!sptSet[vpos] && (dist[vpos]) < dist[srcp] + weight) {
                dist[vpos] = dist[srcp] + weight;
                prev[vpos] = srcp;
            }
        }

        for (int i = 0; i < numVertices() - 1; i++) {
            T u = getMaxDistance(dist, sptSet);
            int upos = getPosition(u);
            sptSet[upos] = true;

            for (T v : getAdjacents(u)) {
                int vpos = getPosition(v);
                int weight = getEdgeWeight(u, v);

                if (!sptSet[vpos] && dist[upos] != Integer.MIN_VALUE &&
                        ((dist[upos] + weight) > dist[vpos])) {
                    dist[vpos] = dist[upos] + weight;
                    prev[vpos] = upos;
                }
            }
        }

        IList<T> longestPath = new SimpleLinkedList<>();
        int u = getPosition(dest);

        while(prev[u] != UNDEFINED){
            longestPath.addFirst(vertices.get(u));
            u = prev[u];
        }

        System.out.print("Longest Path: ");
        for (T v : longestPath){
            System.out.print(v + " ");
        }
    }

    private T getMinDistance(int[] dist, boolean[] sptSet) throws Exception {
        int min = Integer.MAX_VALUE;
        T min_idx = null;

        for (T v : vertices) {
            int vpos = getPosition(v);
            if (!sptSet[vpos] && dist[vpos] <= min) {
                min = dist[vpos];
                min_idx = v;
            }
        }
        return min_idx;
    }

    private T getMaxDistance(int[] dist, boolean[] sptSet) throws Exception {
        int max = Integer.MIN_VALUE;
        T max_idx = vertices.first();

        for (T v : vertices) {
            int vpos = getPosition(v);
            if (!sptSet[vpos] && dist[vpos] > max) {
                max = dist[vpos];
                max_idx = v;
            }
        }
        return max_idx;
    }
}
