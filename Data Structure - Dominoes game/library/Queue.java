package library;

/**
 * Created by AS
 */
public class Queue<T> implements IQueue<T> {

    Node<T> head;
    Node<T> tail;

    public Queue() {
        head = tail = null;
    }

    @Override
    public T dequeue() throws Exception {
        if(head == null)
            throw new Exception("Empty Queue");
        T data = head.getData();
        head = head.getNext();
        return data;
    }

    @Override
    public void enqueue(T object) {
        Node<T> newNode = new Node<T>(object);
        if(head == null){
            head = tail = newNode;
        }else{
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    @Override
    public T front() throws Exception {
        if(head == null)
            throw new Exception("Empty Queue");
        return head.getData();
    }

    @Override
    public T back() throws Exception {
        if(tail == null)
            throw new Exception("Empty Queue");
        return tail.getData();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
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
}
