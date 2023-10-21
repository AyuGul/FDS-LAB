/**
 * Represents an array of type long with various functionalities.
 */
public class MyLongArray {

    private final long[] a; // this is an array of type long, which holds elements of the array in this class
    private int currentIndex; // The current number of elements in the array

    /**
     * Constructs an instance of MyLongArray with the specified size.
     *
     * @param size The size of the array.
     */
    public MyLongArray(int size) {
        a = new long[size];
        currentIndex = 0;
    }

    /**
     * Finds the index of an element in the array by its value.
     *
     * @param searchKey The value of the element to be found.
     * @return The index of the element if found, otherwise -1.
     */
    public int find(long searchKey) {
        for (int i = 0; i < currentIndex; i++) {
            if (a[i] == searchKey) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Inserts a long value at the end of the array.
     *
     * @param value The value of the element to be inserted.
     */
    public void insert(long value) {
        try {
            a[currentIndex++] = value;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves the value of an element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The value of the element if found, otherwise -1.
     */
    public long getElem(int index) {
        if (index >= 0 && index < currentIndex) {
            return a[index];
        } else {
            System.out.println("Index out of bounds of array");
            return -1;
        }
    }

    /**
     * Deletes an element by its value.
     *
     * @param value The value of the element to be deleted.
     * @return true if the element was deleted, false if not found.
     */
    public boolean delete(long value) {
        int index = find(value);
        if (index != -1) {
            for (int i = index; i < currentIndex - 1; i++) {
                a[i] = a[i + 1];
            }
            currentIndex--;
            return true;
        }
        return false;
    }

    /**
     * Displays the elements in the array.
     */
    public void display() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * Deletes duplicate elements using their values.
     *
     * @param value The value of elements of which duplicates are to be deleted.
     * @return The number of elements deleted.
     */
    public int dupDelete(long value) {
        int count = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (a[i] == value) {
                count++;
                if (count > 1) {
                    deleteAt(i);
                    i--;
                }
            }
        }
        return count - 1;
    }

    /**
     * Inserts an element at the specified index.
     *
     * @param index The index at which the element is to be inserted.
     * @param value The value of the element to be inserted.
     */
    public void insert(int index, long value) {
        if (index < 0 || index > a.length  ) {
            System.out.println("Index out of bounds of array");
            return;
        }
        if (currentIndex == a.length) {
            System.out.println("Array is full");
            return;
        }
        if(index >= currentIndex){
            a[currentIndex]= value;
            currentIndex++;
            System.out.println("Index greater than currentIndex value inserted at end");
            return;
        }

        for (int i = currentIndex; i > index; i--) {
            a[i] = a[i - 1];
        }
        a[index] = value;
        currentIndex++;
    }

    /**
     * Deletes an element at the specified index.
     *
     * @param index The index of the element to be deleted.
     * @return The value of the deleted element or -1 if the index is out of bounds.
     */
    public long deleteAt(int index) {
        if (index >= 0 && index < currentIndex) {
            long temp = a[index];
            for (int i = index; i < currentIndex - 1; i++) {
                a[i] = a[i + 1];
            }
            currentIndex--;
            return temp;
        }
        else {
            System.out.println("Index out of bounds of array");
            return -1;
        }
    }


}
