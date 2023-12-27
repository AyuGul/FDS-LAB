import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r;
        
        while(true){
            try{
            System.out.print("Enter value of the root element: ");
            r = scanner.nextInt();
            break;
            }
            catch(Exception e)
            {
                System.out.println("Invalid Input. Enter an integer");
                scanner.next(); // Clear the invalid input
            }}
       
        System.out.println();
        MyTree<Integer> tree = new MyTree<>(r);
        Position<Integer> root = tree.root();
        boolean check=true;
        while (check) {

            // Display menu options
            System.out.println("Choose an option:");
            System.out.println("1. Add a child node");
            System.out.println("2. Display the tree");
            System.out.println("3. Find siblings of a node");
            System.out.println("4. List leaves of the tree");
            System.out.println("5. List internal nodes of the tree");
            System.out.println("6. List edges");
            System.out.println("7. Find a path for a given node");
            System.out.println("8. Find the depth of a node");
            System.out.println("9. Find the height of the tree");
            System.out.println("10. Get a subtree rooted at a node");
            System.out.println("11. Exit");

            // Read user's choice
            System.out.print("Enter your choice: ");
            int choice;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    if (choice <= 0) {
                        System.out.println("Please enter a positive integer");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect input! Please enter a positive integer");
                    scanner.next(); // Clear the invalid input
                }
            }
            
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the parent element: ");
                    int parentElement=0;
                    while(true){
                    try{
                    parentElement = scanner.nextInt();
                    break;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid Input. Enter an integer");
                        scanner.next(); // Clear the invalid input
                    }}
                    System.out.print("Enter the child element: ");
                    int childElement=0;
                    while(true){
                    try{
                        childElement = scanner.nextInt();
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid Input. Enter an integer");
                        scanner.next(); // Clear the invalid input
                    }}
                    // Find the parent position
                    Position<Integer> parentPosition = findPosition(tree, parentElement);
                    if (parentPosition != null) {
                        // Add a child node
                        tree.addChild(parentPosition, childElement);
                        System.out.println(childElement + " added successfully.");
                    } else {
                        System.out.println("Parent not found.");
                    }
                    break;
                case 2:
                    // Display the tree
                    Iterator<Position<Integer>> iterator = tree.positions().iterator();
                    while (iterator.hasNext()) {
                        Position<Integer> pos = iterator.next();
                        int i = tree.findDepth(pos);
                        while(i-->0){
                            System.out.print("  ");
                        }
                        System.out.println("└─"+pos.getElement());
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter the element to find siblings: ");
                    int siblingElement;
                    while(true){
                        try{
                            siblingElement = scanner.nextInt();
                        break;
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Input. Enter an integer");
                            scanner.next(); // Clear the invalid input
                        }}
                    
                    Position<Integer> siblingPosition = findPosition(tree, siblingElement);
                    if (siblingPosition != null) {
                        // List siblings
                        System.out.println("Siblings of "+ siblingElement);
                        Iterator<Position<Integer>> siblings = tree.findSiblings(siblingPosition);
                        while (siblings.hasNext()) {
                            System.out.print(siblings.next().getElement() + " ");
                        }
                        System.out.println();
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;
                case 4:
                    // List leaves
                    Iterator<Position<Integer>> leaves = tree.listLeaves(root).iterator();
                    System.out.println("All leaves of the tree");
                    while (leaves.hasNext()) {
                        System.out.print(leaves.next().getElement() + " ");
                    }
                    System.out.println();
                    break;
                case 5:
                    // List internal nodes
                    Iterator<Position<Integer>> internalNodes = tree.listInternal(tree.root()).iterator();
                    System.out.println("All internal nodes");
                    while (internalNodes.hasNext()) {
                        System.out.print(internalNodes.next().getElement() + " ");
                    }
                    System.out.println();
                    break;
                case 6:
                    // List edges
                    Iterator<Edge<Integer>> edges = tree.listEdges();
                    System.out.println("All edges:");
                    while (edges.hasNext()) {
                        Edge<Integer> edge = edges.next();

                        System.out.println(edge.getParent().getElement() + "->" + edge.getChild().getElement());
                    }
                    break;
                case 7:
                    System.out.print("Enter the element to find the path: ");
                    int pathElement;
                    while(true){
                        try{
                            pathElement = scanner.nextInt();
                        break;
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("Invalid Input. Enter an integer");
                            scanner.next(); // Clear the invalid input
                        }}
                    
                    Position<Integer> pathPosition = findPosition(tree, pathElement);
                    if (pathPosition != null) {
                        System.out.println("Path:");
                        // Find and display the path
                        System.out.println(tree.findPath(pathPosition, "->"));
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;
                case 8:
                    System.out.print("Enter the element to find depth: ");
                    int depthElement;
                    while(true){
                        try{
                            depthElement = scanner.nextInt();
                        break;
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("Invalid Input. Enter an integer");
                            scanner.next(); // Clear the invalid input
                        }}
                    Position<Integer> depthPosition = findPosition(tree, depthElement);
                    if (depthPosition != null) {
                        // Find and display the depth
                        int depth = tree.findDepth(depthPosition);
                        System.out.println("Depth: " + depth);
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;
                case 9:
                    // Find and display the height of the tree
                    int height = tree.findHeight(root);
                    System.out.println("Height: " + height);
                    break;
                case 10:
                    System.out.print("Enter the element to get the subtree rooted at: ");
                    int subtreeElement;
                    while (true) {
                        try {
                            subtreeElement = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. Enter an integer");
                            scanner.next(); // Clear the invalid input
                        }
                    }

                    Position<Integer> subtreePosition = findPosition(tree, subtreeElement);

                    if (subtreePosition != null) {
                        tree.displaySubtree(tree, subtreePosition, 0);
                    } else {
                        System.out.println("Node not found.");
                    }

                    break;
                case 11:
                    System.out.println("Program exited");
                    scanner.close();
                    check=false;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static Position<Integer> findPosition(MyTree<Integer> tree, int element) {
        for (Position<Integer> position : tree.positions()) {
            if (position.getElement().equals(element)) {
                return position;
            }
        }
        return null;
    }


}
