import java.util.*;

public class TestApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        System.out.print("Please enter size of array: ");
        int size = sc.nextInt();
        MyLongArray3 arr = new MyLongArray3(size);//Creation of array
        System.out.println("Array created");

        while (loop) {//Menu for array
            int op;
            System.out.println("MENU FOR MY LONG ARRAY");
            System.out.println("Select the operation you want to perform by typing the respective number: ");
            System.out.println("1) Find an element by value");
            System.out.println("2) Insert an element at end");
            System.out.println("3) Get value of element by index");
            System.out.println("4) Delete an element by value");
            System.out.println("5) Display the array");
            System.out.println("6) Delete duplicate elements of a certain value");
            System.out.println("7) Insert element at a particular index");
            System.out.println("8) Delete element at a particular index");
            System.out.println("9) Exit");

            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Please enter value of element to be found: ");
                    long value = sc.nextLong();
                    int foundIndex = arr.find(value);
                    if (foundIndex != -1) {
                        System.out.println("Element found at index: " + foundIndex);
                    } else {
                        System.out.println("Element not found.");
                    }
                    break;
                case 2:
                    System.out.println("How many elements do you want to insert? ");
                    int n = sc.nextInt();
                    while (n-- > 0) {
                        System.out.print("Please insert an element: ");
                        value = sc.nextLong();
                        arr.insert(value);
                    }
                    System.out.println("Elements inserted");
                    break;
                case 3:
                    System.out.print("Please input index: ");
                    int index = sc.nextInt();
                    long element = arr.getElem(index);
                    if (element != -1) {
                        System.out.println("Value of element is: " + element);
                    }
                    break;
                case 4:
                    System.out.print("Please input value of element to delete: ");
                    value = sc.nextLong();
                    if (arr.delete(value)) {
                        System.out.println("Element deleted");
                    } else {
                        System.out.println("Element not found.");
                    }
                    break;
                case 5:
                    arr.display();
                    break;
                case 6:
                    System.out.print("Please input value of element to delete duplicates of: ");
                    value = sc.nextLong();
                    int deletedCount = arr.dupDelete(value);
                    System.out.println("Duplicates deleted: " + deletedCount);
                    break;
                case 7:
                    System.out.print("Please input index and value of element to be inserted: ");
                    index = sc.nextInt();
                    value = sc.nextLong();
                    arr.insert(index, value);
                    System.out.println("Element inserted");
                    break;
                case 8:
                    System.out.print("Please input index of element to be deleted: ");
                    index = sc.nextInt();
                    long deletedElement = arr.deleteAt(index);
                    if (deletedElement != -1) {
                        System.out.println("Element deleted: " + deletedElement);
                    }
                    break;
                case 9:
                    loop = false;
                    break;
                default:
                    System.out.println("Enter Appropriate Number");
                    break;
            }
        }
    }
}