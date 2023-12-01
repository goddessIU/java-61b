class Node<T> {
    private T val;

    private Node<T> next;
    private Node<T> prev;
    public Node(T item) {
        this.val = item;
        this.next = null;
        this.prev = null;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}

public class LinkedListDeque<T> {
    private int size;
    private Node<T> front;
    private Node<T> back;

    public LinkedListDeque() {
        this.size = 0;
        this.front = null;
        this.back = null;
    }

    public int size() {
        return this.size;
    }

    public T get(int index) {
        Node<T> tmp = front;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp.getVal();
    }

    public T removeFirst() {
        T ret = front.getVal();
        if (size == 1) {
            back = null;
            front = null;
            size--;
            return ret;
        }
        size--;
        front.getNext().setPrev(null);
        front = front.getNext();
        return ret;
    }

    public T removeLast() {
        T ret = back.getVal();
        if (size == 1) {
            back = null;
            front = null;
            size--;
            return ret;
        }
        size--;
        back.getPrev().setNext(null);
        back = back.getPrev();
        return ret;
    }

    public void addFirst(T item) {
        Node<T> node = new Node<>(item);

        if (isEmpty()) {
            front = node;
            back = node;
        } else {
            node.setNext(front);
            front.setPrev(node);
            front = node;
        }
        size++;
    }

    public void addLast(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty()) {
            front = node;
            back = node;
        } else {
            node.setPrev(back);
            back.setNext(node);
            back = node;
        }
        size++;

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private T getRecursiveHelper(int index, Node<T> item) {
        if (index == 0) {
            return item.getVal();
        }

        return getRecursiveHelper(index - 1, item.getNext());
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, front);
    }

    public void printDeque() {

    }
}
