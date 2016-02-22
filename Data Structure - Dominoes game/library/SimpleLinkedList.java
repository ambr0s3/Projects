package library;

import java.util.Iterator;

/**
 * Created by AS
 */
public class SimpleLinkedList<T> implements IList<T>, Iterable<T> {

    private Node<T> mHead;

    public SimpleLinkedList() {
        mHead = null;
    }

    @Override
    public int size() {
        if (mHead == null)
            return 0;
        Node<T> current = mHead;
        int cnt = 0;
        while (current != null) {
            cnt++;
            current = current.getNext();
        }

        return cnt;
    }

    @Override
    public boolean isEmpty() {
        return mHead == null;
    }

    @Override
    public void clear() {
        mHead = null;
    }

    @Override
    public void addLast(T object) {
        if (isEmpty())
            addToFirst(object);
        else
            addToLast(object);
    }

    @Override
    public T get(int index) throws Exception {
        if (index < 0 || index >= size())
            throw new Exception("index out of range.");

        if(index == 0)
            return mHead.getData();

        Node<T> current = mHead;
        int cnt = 0;
        while(cnt++ < index)
            current = current.getNext();

        return current.getData();
    }

    @Override
    public void remove(int index) throws Exception {
        if (isEmpty())
            throw new Exception("List is empty.");

        if (index < 0 || index >= size())
            throw new Exception("index out of range.");

        if(index == 0){
            mHead = mHead.getNext();
        }
        else {
            Node<T> current = mHead;
            int cnt = 0;
            while (cnt++ < index - 1)
                current = current.getNext();

            current.next = current.next.next;

            //return current.getData();
        }
    }

    public void removeLast() throws Exception {
        remove(size() - 1);
    }

    @Override
    public int find(T object) {
        Node<T> current = mHead;

        int cnt = 0;
        while (current != null) {
            if (current.getData().equals(object))
                return cnt;

            cnt++;
            current = current.getNext();
        }

        return -1;
    }

    @Override
    public void insert(int index, T object) throws Exception {
        if (isEmpty())
            throw new Exception("List is empty.");

        if (index < 0 || index >= size())
            throw new Exception("index out of range.");

        Node<T> current = mHead;
        for (int i = 0; i < index - 1; i++)
            current = current.getNext();

        Node<T> temp = current.next;
        current.next = new Node(object);
        current.next.next = temp;

    }

    protected void addToFirst(T object) {
        if (mHead == null)
            mHead = new Node<>(object);
        else {
            Node<T> temp = mHead;
            mHead = new Node<T>(object);
            mHead.next = temp;
        }

    }

    @Override
    public String toString() {
        Node<T> current = mHead;
        String result = "";

        while(current != null){
            if(current.hasNext())
                result += current.getData() + ", ";
            else
                result += current.getData();

            current = current.getNext();
        }

        return result;
    }

    @Override
    public void addFirst(T object) {
        addToFirst(object);
    }

    protected void addToLast(T object) {
        Node<T> current = mHead;
        while (current.hasNext())
            current = current.getNext();

        current.next = new Node(object);
    }

    @Override
    public T first() {
        return mHead.getData();
    }

    @Override
    public T last() {
        try {
            return get(size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void pop() throws Exception {
        removeLast();
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        ListIteractor<T> iteractor = new ListIteractor<>();
        return iteractor;
    }

    protected class ListIteractor<T> implements Iterator<T> {
        Node<T> cursor = (Node<T>) mHead;

        @Override
        public boolean hasNext() {
            return (cursor != null);
            //return cursor.hasNext();
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
