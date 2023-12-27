package src;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EdgeListGraph<V, E> implements Graph<V, E> {

    private static class InnerVertex<V> implements Vertex<V> {
        V element;
        int pos;

        public InnerVertex(V e) {
            element = e;
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
    private ArrayList<Edge<E>> edges = new ArrayList<>();

    private boolean isDirected;

    public EdgeListGraph(boolean directed) {
        isDirected = directed;
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return (Iterable<Vertex<V>>) vertices;
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return (Iterable<Edge<E>>) edges;
    }

    private InnerVertex<V> validateV(Vertex<V> p) throws IllegalArgumentException {
        if (!(p instanceof InnerVertex<?>))
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
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        validateV(u);
        validateV(v);
        for (Edge<E> ed : edges) {
            InnerEdge<E> e = validateE(ed);
            Vertex<V>[] endpoints = e.getEndpoints();
            if ((endpoints[0].equals(u) && endpoints[1].equals(v)) ||
                    (!isDirected && endpoints[0].equals(v) && endpoints[1].equals(u))) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E> e) {
        InnerEdge<E> ed = validateE(e);
        return ed.getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        validateV(v);
        InnerEdge<E> ed  =validateE(e);
        Vertex<V>[] endpoints = ed.getEndpoints();
        if (endpoints[0].equals(v)) {
            return endpoints[1];
        } else if (endpoints[1].equals(v)) {
            return endpoints[0];
        } else {
            throw new IllegalArgumentException("v is not incident to edge e");
        }
    }

    @Override
    public int outDegree(Vertex<V> v) {
        validateV(v);
        int count = 0;
        for (Edge<E> e : edges) {
            InnerEdge<E> ed  =validateE(e);
            if (ed.getEndpoints()[0].equals(v)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int inDegree(Vertex<V> v) {
        validateV(v);
        int count = 0;
        for (Edge<E> e : edges) {
            InnerEdge<E> ed  =validateE(e);
            if (ed.getEndpoints()[1].equals(v)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
        validateV(v);
        List<Edge<E>> outgoingEdges = new ArrayList<>();
        for (Edge<E> e : edges) {
            InnerEdge<E> ed  =validateE(e);
            if (ed.getEndpoints()[0].equals(v)) {
                outgoingEdges.add(e);
            }
        }
        return (Iterable<Edge<E>>) outgoingEdges;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        validateV(v);
        List<Edge<E>> incomingEdges = new ArrayList<>();
        for (Edge<E> e : edges) {
            InnerEdge<E> ed  =validateE(e);
            if (ed.getEndpoints()[1].equals(v)) {
                incomingEdges.add(e);
            }
        }
        return (Iterable<Edge<E>>) incomingEdges;
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
        validateV(u);
        validateV(v);
        InnerEdge<E> e = new InnerEdge<>(u, v, x);
        e.setPos(edges.size());
        edges.add(e);
        return e;
    }
    public void printGraph() {
        System.out.println("Vertices:");
        for (Vertex<V> vertex : vertices) {
            System.out.println(vertex.getElement());
        }

        System.out.println("\nEdges:");
        for (Edge<E> edge : edges) {
            InnerEdge<E> ed  =validateE(edge);
            Vertex<V>[] endpoints = ed.getEndpoints();
            System.out.println(endpoints[0].getElement() + " -- " + edge.getElement() + " --> " + endpoints[1].getElement());
        }
    }
}
