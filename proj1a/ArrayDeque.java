import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayDeque<T> {
    private T[] arr;
    private int items;
    private int nextFront;
    private int nextBack;
    private int size;
    private int capcity;
    public ArrayDeque() {
        this.arr = (T[])new Object[8];
        size = 0;
        items = 0;
        capcity = 8;
        nextFront = 0;
        nextBack = 0;
    }

    private void doubleSize() {
        T[] tmpArr = copyArr(capcity*2);
        capcity = capcity * 2;
        this.arr = tmpArr;
        items = 0;
        nextFront = 0;
        nextBack = this.size - 1;
    }
    public void addFirst(T item) {
        this.arr[nextFront] = item;
        items = nextFront;
        size++;
        nextFront = (nextFront - 1 + capcity) % capcity;
        if (nextBack == nextFront) {
            doubleSize();
        }
    }

    public void addLast(T item) {
        this.arr[nextBack] = item;
        size++;
        nextBack = (nextBack + 1) % capcity;
        if (nextBack == nextFront) {
            doubleSize();
        }
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {

    }

    private T[] copyArr(int cap) {
        T[] tmpArr = (T[])new Object[cap];
        int j = 0;
        for (int i = items; i < capcity; i++) {
            tmpArr[j++] = this.arr[i];
        }
        for (int i = 0; i < nextFront; i++) {
            tmpArr[j++] = this.arr[i];
        }
        return tmpArr;
    }

    private void shrinkSize() {
        T[] tmpArr = copyArr(capcity/4);
        capcity = capcity / 4;
        this.arr = tmpArr;
        items = 0;
        nextFront = 0;
        nextBack = this.size - 1;
    }
    public T removeFirst() {
        T ret = get(items);
        nextFront = (nextFront + 1) % capcity;
        items = (items + 1) % capcity;
        size--;

        if (size > 8 && size < (capcity / 4)) {
            shrinkSize();
        }

        return ret;
    }
    public T removeLast() {
        T ret = get((nextBack - 1 + capcity) % capcity);
        nextBack = (nextBack - 1 + capcity) % capcity;
        size--;

        if (size > 8 && size < (capcity / 4)) {
            shrinkSize();
        }
        return ret;
    }
    public T get(int index) {
        return arr[index];
    }
}
