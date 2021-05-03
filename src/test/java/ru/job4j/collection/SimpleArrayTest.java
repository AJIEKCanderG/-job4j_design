package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {
//Добавьте самостоятельно тесты проверяющее расширение массива
// (например создали список длинной 2 добавили 3 элемента,
// проверили что все 3 есть в списке)

    @Test
    public void whenAddThreeInTwoSize() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        array.add("two");
        array.add("three");
        String rslFirst = array.get(0);
        String rslTwo = array.get(1);
        String rslThree = array.get(2);
        assertThat(rslFirst, is("first"));
        assertThat(rslTwo, is("two"));
        assertThat(rslThree, is("three"));
    }


    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}