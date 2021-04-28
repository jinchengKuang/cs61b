public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    //@source https://www.youtube.com/watch?v=JNroRiEG7U4
    private LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i< other.size; i+=1){
            addLast((T) other.get(i));
        }
    }

    public T getRecursiveHelper(Node currNode, int index) {
        if (index == 0) {
            return currNode.item;
        }
        return getRecursiveHelper(currNode.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }


    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node currNode = sentinel;
        while (currNode.next != sentinel) {
            System.out.print(currNode.next.item + " ");
            currNode = currNode.next;
        }
    }

    public T removeFirst() {
        T removed = sentinel.next.item;
        if (removed == null) {
            return null;
        }
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removed;
    }

    public T removeLast() {
        T removed = sentinel.prev.item;
        if (removed == null) {
            return null;
        }
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        Node currNode = sentinel.next;
        while (index != 0) {
            currNode = currNode.next;
            index -= 1;
        }
        return currNode.item;
    }

}
