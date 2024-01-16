package struct;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    int size;
    Node<T> head;
    Node<T> tail;


    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void addAll(Iterable<T> elements) {
        for (T element : elements) {
            add(element);
        }
    }

    private boolean isEmpty() {
        return head == null;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node<T> current;
        private Node<T> lastReturned;

        public LinkedListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T data = current.data;
            lastReturned = current;
            current = current.next;
            return data;
        }

        @Override
        public void remove() {
            if (lastReturned == head) {
                head = head.next;
                if (head != null) {
                    head.previous = null;
                }
                return;
            }
            if (lastReturned == tail) {
                tail.previous.next = null;
                tail = tail.previous;
                return;
            }

            lastReturned.previous.next = lastReturned.next;
            size--;
        }
    }

    static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data) {
            this.data = data;
        }
    }
}
