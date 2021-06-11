package ru.job4j.collection;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
        head = node;
        }
        node.next = head;
        head = node;

    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T currentFirst = head.value;
        head = head.next;
        return currentFirst;
    }

 /*   public void revert() {
            Node<T> node = head;
            Node<T> nodeNext = head.next;
            head.next = null;
            Node<T> nodeNextNext;
            do {
                nodeNextNext = nodeNext.next;
                nodeNext.next = node;
                node = nodeNext;
                nodeNext = nodeNextNext;
                head = node;
            } while (nodeNextNext != null);
    }*/

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }


    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}