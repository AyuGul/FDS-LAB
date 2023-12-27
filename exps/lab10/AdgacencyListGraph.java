import java.util.*;
public class AdgacencyListGraph<V,E> implements Graph<V,E>{
    private class InnerVertex<V> implements Vertex<V> {
        V element;
        int pos;

        private ArrayList<Edge<E>> adj;

        public InnerVertex(V e) {
            element = e;
            adj = new ArrayList<>();
        }

        public V getElement() {
            return element;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public ArrayList<Edge<E>> getAdj() {
            return adj;
        }
        public void addAdj(Edge<E> e){
            adj.add(e);
        }
    }

    private class InnerEdge<E> implements Edge<E> {

        E element;
        int pos;

        private Vertex<V>[] endpoints;

        public InnerEdge(Vertex<V> u, Vertex<V> v, E e) {
            element = e;
            endpoints = (Vertex<V>[]) new Vertex[]{u, v};
        }

        public E getElement() {
            return element;
        }

        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }
    }

    private ArrayList<Vertex<V>> vertices = new ArrayList<>();


    private boolean isDirected;

    public AdgacencyListGraph(boolean directed) {
        isDirected = directed;
    } 
    private InnerVertex<V> validateV(Vertex<V> p) throws IllegalArgumentException {
        if (!(p instanceof InnerVertex))
            throw new IllegalArgumentException("Not valid vertex type");
        InnerVertex<V> node = (InnerVertex<V>) p; // safe cast

        if (node.getPos() == -1) // our convention for defunct node
            throw new IllegalArgumentException("v is no longer in the graph");
        return node;
    }

    private InnerEdge<E> validateE(Edge<E> p) throws IllegalArgumentException {
        if (!(p instanceof InnerEdge))
            throw new IllegalArgumentException("Not valid Edge type");
        InnerEdge<E> edge = (InnerEdge<E>) p; // safe cast
        if (edge.getPos() == -1) // our convention for defunct node
            throw new IllegalArgumentException("e is no longer in the graph");
        return edge;
    }
    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }


    @Override
    public int numEdges() {
        int count = 0;
        for (Vertex<V> v : vertices) {
            count += outDegree(v);
        }
        return count;
    }

    @Override
    public Iterable<Edge<E>> edges() {
        List<Edge<E>> edges = new ArrayList<>();
        for (Vertex<V> v : vertices) {
            for (Edge<E> edge : outgoingEdges(v)) {
                edges.add(edge);
            }
        }
        return edges;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        for (Edge<E> edge : outgoingEdges(u)) {
            if (opposite(u,edge).equals(v)) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E> e) {
        return ((InnerEdge<E>) validateE(e)).getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        Vertex<V>[] endpoints = endVertices(e);
        if (endpoints[0].equals(v)) {
            return endpoints[1];
        } else if (endpoints[1].equals(v)) {
            return endpoints[0];
        } else {
            throw new IllegalArgumentException("Vertex is not incident to the edge");
        }
    }

    @Override
    public int outDegree(Vertex<V> v) {
        return validateV(v).getAdj().size();
    }

    @Override
    public int inDegree(Vertex<V> v) {
        if (!isDirected) {
            // For undirected graphs, in-degree is the same as out-degree
            return outDegree(v);
        }

        int inDegree = 0;
        for (Vertex<V> u : vertices) {
            for (Edge<E> edge : outgoingEdges(u)) {
                if (opposite(u,edge).equals(v)) {
                    inDegree++;
                }
            }
        }
        return inDegree;
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
        return validateV(v).getAdj();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        if (!isDirected) {
            // For undirected graphs, incoming edges are the same as outgoing edges
            return outgoingEdges(v);
        }

        List<Edge<E>> incomingEdges = new ArrayList<>();
        for (Vertex<V> u : vertices) {
            for (Edge<E> edge : outgoingEdges(u)) {
                if (opposite(u,edge).equals(v)) {
                    incomingEdges.add(edge);
                }
            }
        }
        return incomingEdges;
    }

    @Override
    public Vertex<V> insertVertex(V x) {
        InnerVertex<V> v = new InnerVertex<>(x);
        v.setPos(vertices.size());
        vertices.add(v);
        return v;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E x) {
        InnerEdge<E> edge = new InnerEdge<>(u, v, x);
        edge.setPos(numEdges()); // Set the position to the current number of edges
        validateV(u).addAdj(edge);
        // For undirected graphs, add the edge in the opposite direction as well
        if (!isDirected) {
            validateV(v).addAdj(edge);
        }
        return edge;
    }
    public void printGraph() {
    System.out.println("Graph:");
    for (Vertex<V> vertex : vertices) {
        System.out.print(vertex.getElement() + ": ");
        InnerVertex<V> v = validateV(vertex);
        for (Edge<E> edge : v.getAdj()) {
            Vertex<V>[] endpoints = endVertices(edge);
            System.out.print(endpoints[0].getElement() + " --(" + edge.getElement() + ")-- " + endpoints[1].getElement() + "  ");
        }
        System.out.println();
    }
}
}

