import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       LinearHashMap<String, Integer> hashMap = new LinearHashMap<>();

        int choice;
        do {
            System.out.println("\nHash Map Menu:");
            System.out.println("1. Put");
            System.out.println("2. Get");
            System.out.println("3. Remove");
            System.out.println("4. Display Size");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter key: ");
                    String key = scanner.nextLine();
                    System.out.print("Enter value: ");
                    int value = scanner.nextInt();
                    hashMap.put(key, value);
                    System.out.println("Key-Value pair added.");
                    break;

                case 2:
                    System.out.print("Enter key: ");
                    key = scanner.nextLine();
                    Integer result = hashMap.get(key);
                    System.out.println("Value for key '" + key + "': " + result);
                    break;

                case 3:
                    System.out.print("Enter key: ");
                    key = scanner.nextLine();
                    Integer removedValue = hashMap.remove(key);
                    if (removedValue != null) {
                        System.out.println("Value '" + removedValue + "' removed for key '" + key + "'.");
                    } else {
                        System.out.println("Key not found.");
                    }
                    break;

                case 4:
                    System.out.println("Size of the Hash Map: " + hashMap.size());
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (choice != 5);

        scanner.close();
        System.out.println();
    }

        
//        // Create a ChainHashMap with an initial capacity of 10
//        LinearHashMap<String, Integer> hashMap = new LinearHashMap<>(10);
//
//        // Put key-value pairs into the map
//        hashMap.put("One", 1);
//        hashMap.put("Two", 2);
//        hashMap.put("Three", 3);
//        hashMap.put("Four", 4);
//
//        // Print the size of the map
//        System.out.println("Size of the map: " + hashMap.size());
//
//        // Print values associated with keys
//        System.out.println("Value for key 'Two': " + hashMap.get("Two"));
//        System.out.println("Value for key 'Five': " + hashMap.get("Five"));
//
//        // Remove an entry
//        System.out.println("Removing key 'Three': " + hashMap.remove("Three"));
//
//        // Print the size after removal
//        System.out.println("Size of the map after removal: " + hashMap.size());
//
//        // Iterate through entries and print key-value pairs
//        System.out.println("Entries in the map:");
//        for ( Entry<String, Integer> entry : hashMap.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
    }

