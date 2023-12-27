public interface Graph<V,E> {

    int numVertices();
    Iterable<Vertex<V>> vertices();

    int numEdges();
    Iterable<Edge<E>> edges();

    Edge<E> getEdge(Vertex<V> u,Vertex<V> v);

    Vertex<V>[] endVertices(Edge<E> e);

    Vertex<V> opposite(Vertex<V> v, Edge<E> e);
    int outDegree(Vertex<V> v);
    int inDegree(Vertex<V> v);

    Iterable<Edge<E>> outgoingEdges(Vertex<V> v);
    Iterable<Edge<E>> incomingEdges(Vertex<V> v);
    Vertex<V> insertVertex(V x);
    Edge<E> insertEdge(Vertex<V> u,Vertex<V> v,E x);

}
