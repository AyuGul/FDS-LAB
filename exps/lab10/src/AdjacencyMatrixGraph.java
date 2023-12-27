package src;
import java.util.*;

public class AdjacencyMatrixGraph<V, W> {
    // Vertex class


// Vertex class
private class Vertex<V> {
    private static int idCounter = 0;
    private int id;
    private V data;

    public Vertex(V data) {
        super();
        this.id = idCounter++;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public V getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) obj;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }
}

// Edge class
private class Edge {
        private Vertex<V> src;
        private Vertex<V> dest;
        private W weight;

        public Edge(Vertex<V> src, Vertex<V> dest, W weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public Vertex<V> getSrc() {
            return src;
        }

        public Vertex<V> getDest() {
            return dest;
        }

        public W getWeight() {
            return weight;
        }

        public List<Vertex<V>> endVertices() {
            List<Vertex<V>> endVertices = new ArrayList<>();
            endVertices.add(src);
            endVertices.add(dest);
            return endVertices;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", dest=" + dest +
                    ", weight=" + weight +
                    '}';
        }
    }

    private Map<Vertex<V>, List<Edge>> vertices;
    private List<Edge> edges;
    private int[][] adjacencyMatrix;


    public AdjacencyMatrixGraph() {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
        this.adjacencyMatrix = new int[0][0];
    }

    public int numVertices() {
        return vertices.size();
    }

    public List<Vertex<V>> vertices() {
        return new ArrayList<>(vertices.keySet());
    }

    public int numEdges() {
        return edges.size();
    }

    public List<Edge> edges() {
        return new ArrayList<>(edges);
    }

    public Edge getEdge(Vertex<V> u, Vertex<V> v) {
        for (Edge edge : edges) {
            if (edge.getSrc().equals(u) && edge.getDest().equals(v)) {
                return edge;
            }
        }
        return null;
    }

    public boolean hasEdge(Vertex<V> u, Vertex<V> v) {
        return getEdge(u, v) != null;
    }

    public List<? extends Vertex<V>> endVertices(Edge e) {
        return e.endVertices();
    }

    public Vertex<V> opposite(Vertex<V> v, Edge e) {
        if (v.equals(e.getSrc())) {
            return e.getDest();
        } else if (v.equals(e.getDest())) {
            return e.getSrc();
        } else {
            throw new IllegalArgumentException("Vertex is not incident to this edge");
        }
    }

    public int outDegree(Vertex<V> v) {
        return vertices.getOrDefault(v, new ArrayList<>()).size();
    }

    public int inDegree(Vertex<V> v) {
        int count = 0;
        for (List<Edge> edgesList : vertices.values()) {
            for (Edge edge : edgesList) {
                if (edge.getDest().equals(v)) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<Edge> outgoingEdges(Vertex<V> v) {
        return new ArrayList<>(vertices.getOrDefault(v, new ArrayList<>()));
    }

    public List<Edge> incomingEdges(Vertex<V> v) {
        List<Edge> incomingEdges = new ArrayList<>();
        for (List<Edge> edgesList : vertices.values()) {
            for (Edge edge : edgesList) {
                if (edge.getDest().equals(v)) {
                    incomingEdges.add(edge);
                }
            }
        }
        return incomingEdges;
    }

    public void insertVertex(Vertex<V> x) {
        vertices.put(x, new ArrayList<>());
        updateAdjacencyMatrix();
    }

    public void insertEdge(Vertex<V> u, Vertex<V> v, W weight) {
        Edge edge = new Edge(u, v, weight);
        edges.add(edge);

        vertices.computeIfAbsent(u, k -> new ArrayList<>()).add(edge);
        vertices.computeIfAbsent(v, k -> new ArrayList<>()).add(edge);

        updateAdjacencyMatrix();
    }

    public void removeEdge(Edge e) {
        if (edges.remove(e)) {
            vertices.get(e.getSrc()).remove(e);
            vertices.get(e.getDest()).remove(e);

            updateAdjacencyMatrix();
        }
    }

    private void updateAdjacencyMatrix() {
        int numVertices = vertices.size();
        int[][] newMatrix = new int[numVertices][numVertices];

        for (Edge edge : edges) {
            int srcId = edge.getSrc().getId();
            int destId = edge.getDest().getId();
            newMatrix[srcId][destId] = 1;
            newMatrix[destId][srcId] = 1;  // for undirected graph
        }

        adjacencyMatrix = newMatrix;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
