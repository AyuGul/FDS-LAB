package src;//public class Main {
//    public static void main(String[] args) {
//        PositionalList<Integer> list = new PositionalList<>();
//
//        list.addLast(10);
//        list.addLast(20);
//        list.addLast(30);
//        list.addLast(40);
//
//        Iterator<Position<Integer>> iterator = list.iterator();
//
//        System.out.println("List initialized to:");
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().getElement());
//        }
//        System.out.println("List Size: "+list.size());
//
//        System.out.println("First element of list: "+list.first().getElement());
//
//        System.out.println("Last element of list: "+list.last().getElement());
//
//        System.out.println("Second last element of list: "+list.before(list.last()).getElement());
//
//        System.out.println("Second element of list: "+list.after(list.first()).getElement());
//
//        list.addFirst(50);
//
//        System.out.println("New first element of list: "+list.first().getElement());
//
//        System.out.println("Setting first element to "+list.set(list.first(),100));
//
//        System.out.println("Removing the last element which is  "+list.remove(list.last()));
//
//        iterator = list.iterator();
//
//        System.out.println("List elements after operations:");
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().getElement());
//        }
//
//    }
//}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PositionalList<Integer> list = new PositionalList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Positional List Menu:");
            System.out.println("1. Add First");
            System.out.println("2. Add Last");
            System.out.println("3. Add Before");
            System.out.println("4. Add After");
            System.out.println("5. Set");
            System.out.println("6. Remove");
            System.out.println("7. Print List");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter an element to add at the beginning: ");
                    int element1 = scanner.nextInt();
                    list.addFirst(element1);
                    break;

                case 2:
                    System.out.print("Enter an element to add at the end: ");
                    int element2 = scanner.nextInt();
                    list.addLast(element2);
                    break;

                case 3:
                    System.out.print("Enter an element to add: ");
                    int element3 = scanner.nextInt();
                    System.out.print("Enter a position before which to add: ");
                    int position3 = scanner.nextInt();
                    list.addBefore(list.positions().iterator().next(), element3);
                    break;

                case 4:
                    System.out.print("Enter an element to add: ");
                    int element4 = scanner.nextInt();
                    System.out.print("Enter a position after which to add: ");
                    int position4 = scanner.nextInt();
                    list.addAfter(list.positions().iterator().next(), element4);
                    break;

                case 5:
                    System.out.print("Enter a new element: ");
                    int newElement = scanner.nextInt();
                    System.out.print("Enter a position to set: ");
                    int position5 = scanner.nextInt();
                    list.set(list.positions().iterator().next(), newElement);
                    break;

                case 6:
                    System.out.print("Enter a position to remove: ");
                    int position6 = scanner.nextInt();
                    list.remove(list.positions().iterator().next());
                    break;

                case 7:
                    System.out.print("Current List: ");
                    Iterator<Position<Integer>> iterator= list.iterator();
                    while (iterator.hasNext()) {
                        System.out.print(iterator.next().getElement() + " ");
                    }
                    System.out.println();
                    break;

                case 8:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}