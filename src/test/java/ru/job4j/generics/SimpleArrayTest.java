package ru.job4j.generics;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleArrayTest {

    @Test
    public void whenAdd() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(2);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(1);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(3));
    }

    @Test
    public void whenIterator() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        Iterator<Integer> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenSet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(null);
        simpleArray.set(1, 5);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(5));
        assertThat(simpleArray.get(2), is(3));
        assertThat(simpleArray.get(3), is(nullValue()));
    }
}