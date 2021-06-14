package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount;

    public SimpleArray(int index) {
        this.container = new Object[index];
    }

    private void capacity(int nextSize) {
        int oldCapacity = container.length;
        if (nextSize == oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            Object[] newContainer = new Object[newCapacity];
            System.arraycopy(container, 0, newContainer, 0, size);
            container = newContainer;
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        return (T) container[index];
    }

    public void add(T model) {
        capacity(size + 1);
        modCount++;
        container[size++] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Object[] array = container;
            private final int arraySize = size;
            private int point = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < arraySize;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[point++];
            }
        };
    }
}