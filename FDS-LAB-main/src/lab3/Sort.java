
import java.util.*;

public class Sort extends MyLongArray3 {
    private long[] c;

    /**
     * Initializes the array with random long values.
     */
    public int z;

    Sort(int size) {
        super(size);
        z = size;
    }

    public void initArray(MyLongArray3 m) {
        long[] c = new long[z];
        Random r = new Random();
        for (int i = 0; i < c.length; i++) {
            c[i] = r.nextLong();
        }
        for (long l : c) System.out.print(l + " ");
        for (int i = 0; i < c.length; i++) {
            m.seta(i, c[i]);
            m.setci(i + 1);
        }

        System.out.println();
    }

    /**
     * Sorts the elements in the array using the bubble sort algorithm.
     */
    public void bubbleSort(MyLongArray3 m) {
        c = new long[z];
        m.geta(c); // get values from MyLongArray3 object
        for (int i = 0; i < c.length - 1; i++) {
            for (int j = 1; j < c.length - i; j++) {
                if (c[j - 1] > c[j]) {
                    long temp = c[j - 1];
                    c[j - 1] = c[j];
                    c[j] = temp;
                }
            }
        }
        for (long l : c) {
            System.out.print(l + " ");
        }
    }

    /**
     * Sorts the elements in the array using the selection sort algorithm.
     */
    public void selectionSort(MyLongArray3 m) {
        c = new long[z];
        m.geta(c);
        for (int i = 0; i < c.length; i++) {
            int minIndex = i;
            for (int j = i; j < c.length; j++) {
                if (c[minIndex] > c[j]) {
                    minIndex = j;
                }
            }
            long temp = c[minIndex];
            c[minIndex] = c[i];
            c[i] = temp;
        }
        for (long l : c) {
            System.out.print(l + " ");
        }
    }

    /**
     * Sorts the elements in the array using the insertion sort algorithm.
     */
    public void insertionSort(MyLongArray3 m) {
        c = new long[z];
        m.geta(c);
        for (int i = 1; i < c.length; i++) {
            int j = i;
            while (j > 0 && c[j] < c[j - 1]) {
                long temp = c[j];

                c[j] = c[j - 1];
                c[j - 1] = temp;
                j--;
            }
        }
        for (long l : c) {
            System.out.print(l + " ");
        }
    }
}
