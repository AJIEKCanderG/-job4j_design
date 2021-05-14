package ru.job4j.collection;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() { // должен возвращать значение и удалять его из коллекции.
        return linked.deleteFirst();
    }

    public void push(T value) { //помещает значение в коллекцию.
        linked.addFirst(value);
    }

    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }
}