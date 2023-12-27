import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A simple console-based program to interact with a PositionalPriorityQueue.
 */
public class Main {

    /**
     * The main method to run the console-based program.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        PositionalPriorityQueue<Integer, String> pq = new PositionalPriorityQueue<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("1. Insert an element");
                System.out.println("2. Minimum element");
                System.out.println("3. Remove the minimum element");
                System.out.println("4. Size of the priority queue");
                System.out.println("5. Check if the queue is empty");
                System.out.println("6. Display the queue");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        try {
                            System.out.print("Enter key: ");
                            int key = scanner.nextInt();
                            System.out.print("Enter value: ");
                            String value = scanner.next();
                            pq.insert(key, value);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scanner.nextLine();
                        }
                        break;
                    case 2:
                        try {
                            if (pq.isEmpty()) {
                                System.out.println("Queue is empty");
                            } else {
                                PositionalPriorityQueueEntry<Integer, String> minEntry = pq.min();
                                System.out.println("Min is (" + minEntry.getKey() + ", " + minEntry.getValue() + ")");
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            if (pq.isEmpty()) {
                                System.out.println("Queue is empty");
                            } else {
                                PositionalPriorityQueueEntry<Integer, String> minEntry = pq.removeMin();
                                System.out.println("Removed (" + minEntry.getKey() + ", " + minEntry.getValue() + ")");
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Size is " + pq.size());
                        break;
                    case 5:
                        if(pq.isEmpty()){
                            System.out.println("Queue is empty");
                        }
                        else {
                            System.out.println("Queue not empty");
                        }
                        break;
                    case 6:
                        if (pq.isEmpty()) {
                            System.out.println("Queue is empty");
                        } else {
                            System.out.println("Displaying queue:");
                            pq.display();
                        }
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Invalid option. Please choose a valid option.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
