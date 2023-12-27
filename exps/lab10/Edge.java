public interface Edge<E> {
     E getElement();

     Vertex<?>[] getEndpoints();
}
