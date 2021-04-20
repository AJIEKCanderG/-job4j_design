package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] models;
    private int size;

    /**
     * @param size размер массива.
     */
    public SimpleArray(int size) {
        this.models = new Object[size];
        this.size = 0;
    }

    /**
     * Метод add добавляет указанный элемент (model) в первую свободную ячейку.
     * @param model элемент которые добавляем.
     */
    public void add(T model) {
        models[size] = model;
        size++;
    }

    /**
     * Метод set заменяет указанным элементом (model) элемент, находящийся по индексу index;
     * @param index индекс заменяемого элемента.
     * @param model элемент которым заменяем элемент по индексу.
     */
    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        models[index] = model;
    }

    /**
     * Метод remove удаляет элемент по указанному индексу.
     * Все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево.
     * В середине массива не должно быть пустых ячеек.
     * @param index удаляемого элемента.
     */
    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(models, index + 1, models, index, size - index - 1);
        size--;
    }

    /**
     * Метод  get возвращает элемент, расположенный по указанному индексу;
     * @param index индекс элемента, который возвращаем.
     * @return содержит возвращаемый элемент по указанному индексу.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) models[index];
    }

    /**
     * Метод iterator() возвращает итератор, предназначенный для обхода данной структуры.
     * @return  возвращаемый элемент.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Object[] array = models;
            private final int size = SimpleArray.this.size;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
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