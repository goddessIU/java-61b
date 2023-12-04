package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;
    private int capacity() {
        return capacity;
    }
    private int fillCount() {
        return fillCount;
    }
    private boolean isEmpty() {
        return fillCount == 0;
    }
    private boolean isFull() {
        return fillCount == capacity;
    }
    private abstract T peek();
    private abstract T dequeue();
    private abstract void enqueue(T x);
}
