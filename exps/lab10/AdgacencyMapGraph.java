import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdgacencyMapGraph<V, E> {

    public void printGraph() {
        for (Vertex<V> vertex : vertices) {
            System.out.print("Vertex " + vertex.getElement() + ": ");

            // Print outgoing edges
            for (Edge<E> outgoingEdge : outgoingEdges(vertex)) {
                Vertex<V>[] endpoints = endVertices(outgoingEdge);
                System.out.print(endpoints[0].getElement() + " --(" + outgoingEdge.getElement() + ")--> " + endpoints[1].getElement() + " ");
            }

            System.out.println(); // Move to the next line for the next vertex
        }
    }

    private class InnerVertex<V> implements Vertex<V> {
        private V element;
        private int pos;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;

        public InnerVertex(V elem, boolean graphIsDirected) {
            element = elem;
            outgoing = new HashMap<>();
            if (graphIsDirected) {
                incoming = new HashMap<>();
            } else {
                incoming = new HashMap<>();
                outgoing = incoming; // fix the aliasing for undirected graphs
            }
        }

        public V getElement() {
            return element;
        }

        public void setPosition(int p) {
            pos = p;
        }

        public int getPosition() {
            return pos;
        }

        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }

    private class InnerEdge<E> implements Edge<E> {
        private E element;
        private int pos;
        private Vertex<V>[] endpoints;

        public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
            element = elem;
            endpoints = (Vertex<V>[]) new Vertex[]{u, v}; // array of length 2
        }

        public E getElement() {
            return element;
        }

        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        public void setPosition(int p) {
            pos = p;
        }

        public int getPosition() {
            return pos;
        }
    }

    private boolean isDirected;
    private ArrayList<Vertex<V>> vertices = new ArrayList<>();
    private ArrayList<Edge<E>> edges = new ArrayList<>();

    public AdgacencyMapGraph(boolean directed) {
        isDirected = directed;
    }

    public int numVertices() {
        return vertices.size();
    }

    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    public int numEdges() {
        return edges.size();
    }

    public Iterable<Edge<E>> edges() {
        return edges;
    }

    public int outDegree(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().size();
    }

    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().values();
    }

    public int inDegree(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().size();
    }

    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().values();
    }

    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        InnerVertex<V> origin = validate(u);
        return origin.getOutgoing().get(v);
    }

    public Vertex<V>[] endVertices(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndpoints();
        if (endpoints[0] == v) return endpoints[1];
        else if (endpoints[1] == v) return endpoints[0];
        else throw new IllegalArgumentException("v is not incident to this edge");
    }

    public Vertex<V> insertVertex(V element) {
        InnerVertex<V> v = new InnerVertex<>(element, isDirected);
        vertices.add(v);
        v.setPosition(vertices.size() - 1); // pass the correct position
        return v;
    }

    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
        if (getEdge(u, v) == null) {
            InnerEdge<E> e = new InnerEdge<>(u, v, element);
            e.setPosition(edges.size()); // use the correct position
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, e);
            dest.getIncoming().put(u, e);
            edges.add(e); // add the edge to the list
            return e;
        } else throw new IllegalArgumentException("Edge from u to v exists");
    }

    private InnerVertex<V> validate(Vertex<V> p) throws IllegalArgumentException {
        if (!(p instanceof InnerVertex))
            throw new IllegalArgumentException("Not valid vertex type");
        InnerVertex<V> node = (InnerVertex<V>) p;
        if (node.getPosition() == -1)
            throw new IllegalArgumentException("v is no longer in the graph");
        return node;
    }

    private InnerEdge<E> validate(Edge<E> p) throws IllegalArgumentException {
        if (!(p instanceof InnerEdge))
            throw new IllegalArgumentException("Not valid Edge type");
        InnerEdge<E> edge = (InnerEdge<E>) p;
        if (edge.getPosition() == -1)
            throw new IllegalArgumentException("e is no longer in the graph");
        return edge;
    }

    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        for (Edge<E> e : vert.getOutgoing().values())
            removeEdge(e);
        for (Edge<E> e : vert.getIncoming().values())
            removeEdge(e);
        vertices.remove(vert); // remove the vertex directly
    }

    private void removeEdge(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndpoints();
        InnerVertex<V> origin = validate(endpoints[0]);
        InnerVertex<V> dest = validate(endpoints[1]);
        origin.getOutgoing().remove(dest);
        dest.getIncoming().remove(origin);
        edges.remove(edge);
    }
}
