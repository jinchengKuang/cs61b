import java.lang.reflect.Array;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int oneMinus(int index) {
        return (index - 1) % items.length;
    }

    private int onePlus(int index) {
        return (index + 1) % items.length;
    }

    // @reference https://github.com/seriouszyx/cs61b/blob/master/proj1a/ArrayDeque.java
    private void resizeHelper(int capacity) {
        int start = onePlus(nextFirst);
        int end = oneMinus(nextLast);
        T[] temp = items;
        int originItemsSize = items.length;
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i = start; i != end; i = (i + 1) % originItemsSize) {
            items[nextLast] = temp[i];
            nextLast = onePlus(nextLast);
        }
        items[nextLast] = temp[end];
        nextLast = onePlus(nextLast);
    }

    private void resize(int currSize) {
        if (size == items.length) {
            resizeHelper(currSize * 4);
        }
        if (currSize * 1.0 / items.length < 0.25 && items.length >= 8) {
            resizeHelper(currSize / 2);
        }
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return items[(onePlus(nextFirst) + index) % items.length];
    }

    public void addFirst(T item) {
        resize(size);
        items[nextFirst] = item;
        nextFirst = oneMinus(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        resize(size);
        items[nextLast] = item;
        nextLast = onePlus(nextLast);
        size += 1;
    }

    public T removeFirst() {
        resize(size);
        T removed = items[onePlus(nextFirst)];
        nextFirst = onePlus(nextFirst);
        items[nextFirst] = null;
        size -= 1;
        return removed;
    }

    public T removeLast() {
        resize(size);
        T removed = items[oneMinus(nextLast)];
        nextLast = oneMinus(nextLast);
        items[nextLast] = null;
        size -= 1;
        return removed;
    }

    public void printDeque() {
        int i = onePlus(nextFirst);
        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i = onePlus(i);
        }
    }

}
