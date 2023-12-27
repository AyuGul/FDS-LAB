package src;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EdgeListGraph<String, Integer> graph = new EdgeListGraph<>(false);

        while (true) {
            System.out.println("\nGraph Operations Menu:");
            System.out.println("1. Insert Vertex");
            System.out.println("2. Insert Edge");
            System.out.println("3. Get Edge");
            System.out.println("4. End Vertices");
            System.out.println("5. Opposite");
            System.out.println("6. Out Degree");
            System.out.println("7. In Degree");
            System.out.println("8. Outgoing Edges");
            System.out.println("9. Incoming Edges");
            System.out.println("10. Print graph");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter vertex element: ");
                    String vertexElement = scanner.next();
                    Vertex<String> vertex = graph.insertVertex(vertexElement);
                    System.out.println("Vertex inserted with element: " + vertex.getElement());
                    break;

                case 2:
                    System.out.print("Enter source vertex element: ");
                    String sourceElement = scanner.next();
                    Vertex<String> source = findVertex(graph, sourceElement);

                    System.out.print("Enter target vertex element: ");
                    String targetElement = scanner.next();
                    Vertex<String> target = findVertex(graph, targetElement);

                    System.out.print("Enter edge element: ");
                    int edgeElement = scanner.nextInt();

                    Edge<Integer> edge = graph.insertEdge(source, target, edgeElement);
                    System.out.println("Edge inserted with element: " + edge.getElement());
                    break;

                case 3:
                    System.out.print("Enter source vertex element: ");
                    sourceElement = scanner.next();
                    source = findVertex(graph, sourceElement);

                    System.out.print("Enter target vertex element: ");
                    targetElement = scanner.next();
                    target = findVertex(graph, targetElement);

                    Edge<Integer> foundEdge = graph.getEdge(source, target);
                    if (foundEdge != null) {
                        System.out.println("Edge found with element: " + foundEdge.getElement());
                    } else {
                        System.out.println("Edge not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter source vertex element: ");
                    String sourceElement4 = scanner.next();
                    Vertex<String> source4 = findVertex(graph, sourceElement4);

                    System.out.print("Enter target vertex element: ");
                    String targetElement4 = scanner.next();
                    Vertex<String> target4 = findVertex(graph, targetElement4);

                    Edge<Integer> edge4 = graph.getEdge(source4, target4);
                    if (edge4 != null) {
                        Vertex<String>[] endVertices = graph.endVertices(edge4);
                        System.out.println("End vertices of the edge [" + sourceElement4 + " -- " + edge4.getElement() + " --> " + targetElement4 + "]: " +
                                endVertices[0].getElement() + ", " + endVertices[1].getElement());
                    } else {
                        System.out.println("Edge not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter vertex element: ");
                    String vertexElement5 = scanner.next();
                    Vertex<String> vertex5 = findVertex(graph, vertexElement5);

                    System.out.print("Enter edge element: ");
                    int edgeElement5 = scanner.nextInt();
                    Edge<Integer> edge5 = findEdge(graph, vertex5, edgeElement5);

                    Vertex<String> oppositeVertex = graph.opposite(vertex5, edge5);
                    System.out.println("Opposite of vertex " + vertexElement5 + " in the edge [" +
                            vertex5.getElement() + " -- " + edge5.getElement() + " --> " + oppositeVertex.getElement() + "]");
                    break;

                case 6:
                    System.out.print("Enter vertex element: ");
                    String vertexElement6 = scanner.next();
                    Vertex<String> vertex6 = findVertex(graph, vertexElement6);

                    int outDegree = graph.outDegree(vertex6);
                    System.out.println("Out degree of vertex " + vertexElement6 + ": " + outDegree);
                    break;

                case 7:
                    System.out.print("Enter vertex element: ");
                    String vertexElement7 = scanner.next();
                    Vertex<String> vertex7 = findVertex(graph, vertexElement7);

                    int inDegree = graph.inDegree(vertex7);
                    System.out.println("In degree of vertex " + vertexElement7 + ": " + inDegree);
                    break;

                case 8:
                    System.out.print("Enter vertex element: ");
                    String vertexElement8 = scanner.next();
                    Vertex<String> vertex8 = findVertex(graph, vertexElement8);

                    System.out.println("Outgoing edges of vertex " + vertexElement8 + ":");
                    for (Edge<Integer> outgoingEdge : graph.outgoingEdges(vertex8)) {
                        Vertex<String>[] endpoints8 = (Vertex<String>[]) outgoingEdge.getEndpoints();
                        System.out.println(endpoints8[0].getElement() + " -- " + outgoingEdge.getElement() + " --> " + endpoints8[1].getElement());
                    }
                    break;

                case 9:
                    System.out.print("Enter vertex element: ");
                    String vertexElement9 = scanner.next();
                    Vertex<String> vertex9 = findVertex(graph, vertexElement9);

                    System.out.println("Incoming edges of vertex " + vertexElement9 + ":");
                    for (Edge<Integer> incomingEdge : graph.incomingEdges(vertex9)) {
                        Vertex<String>[] endpoints9 = (Vertex<String>[]) incomingEdge.getEndpoints();
                        System.out.println(endpoints9[0].getElement() + " -- " + incomingEdge.getElement() + " --> " + endpoints9[1].getElement());
                    }
                    break;


                case 10:
                    System.out.println("Printing the graph:");
                    graph.printGraph();
                    break;

                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static Vertex<String> findVertex(EdgeListGraph<String, Integer> graph, String element) {
    for (Vertex<String> vertex : graph.vertices()) {
        if (vertex.getElement().equals(element)) {
            return vertex;
        }
    }
    System.out.println("Vertex with element " + element + " not found in the graph.");
    return null; // or throw an exception, depending on your preference
}

private static <V, E> Edge<E> findEdge(Graph<V, E> graph, Vertex<V> source, E edgeElement) {
    for (Edge<E> edge : graph.outgoingEdges(source)) {
        if (edge.getElement().equals(edgeElement)) {
            return edge;
        }
    }
    System.out.println("Edge with element " + edgeElement + " not found from the source vertex " + source.getElement());
    return null; // or throw an exception, depending on your preference
}

}
