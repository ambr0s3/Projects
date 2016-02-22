package library;

import java.util.Iterator;

/**
 * Created by AS
 */
public class CircularLinkedList<T> implements IList<T> {
    private Node<T> tail = null;
    private int size = 0;

    public CircularLinkedList() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public T first() {
        if (isEmpty()) return null;

        return tail.getNext().getData();
    }

    @Override
    public T last() {
        if (isEmpty()) return null;

        return tail.getData();
    }

    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

    @Override
    public void addFirst(T e) {
        if (size == 0) {
            tail = new Node(e);
            tail.setNext(tail);
        } else {
            Node<T> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }

        size++;
    }

    @Override
    public void addLast(T e) {
        addFirst(e);
        tail = tail.getNext();
    }

    @Override
    public void insert(int position, T object) throws Exception {

    }

    @Override
    public T get(int index) throws Exception {
        if (index < 0 || index >= size)
            throw new Exception("index out of range.");

        if(index == 0)
            return tail.getData();

        Node<T> current = tail;
        int cnt = 0;
        while(cnt++ < index)
            current = current.getNext();

        return current.getData();
    }

    @Override
    public void remove(int position) throws Exception {

    }

    public T removeFirst() {
        if (isEmpty()) return null;
        Node<T> head = tail.getNext();
        if (head == tail) tail = null;
        else tail.setNext(head.getNext());
        size--;
        return head.getData();
    }

    @Override
    public int find(T object) {
        return 0;
    }

    @Override
    public void pop() throws Exception {
        remove(size-1);
    }

    @Override
    public Iterator<T> iterator() {
        ListIteractor<T> iteractor = new ListIteractor<>();
        return iteractor;
    }

    protected class ListIteractor<T> implements Iterator<T> {
        Node<T> cursor = (Node<T>) tail;

        @Override
        public boolean hasNext() {
                return cursor.hasNext();
        }

        @Override
        public T next() {
            T value = cursor.getData();
            cursor = cursor.getNext();
            return value;
        }

        @Override
        public void remove() {

        }
    }
}
