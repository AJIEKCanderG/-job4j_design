package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int value = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        while (value < numbers.length && numbers[value] % 2 != 0) {
            value++;
        }
        return value < numbers.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[value++];
    }
}
