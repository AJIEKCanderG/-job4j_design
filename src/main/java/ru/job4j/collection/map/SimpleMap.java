package ru.job4j.collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (get(key) != null) {
            return false;
        }
        int i = indexOf(key);
        table[i] = new MapEntry<>(key, value);
        float load = (float) count / capacity;
        if (Float.compare(load, LOAD_FACTOR) >= 0) {
            expand();
        }
        modCount++;
        count++;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleMap)) {
            return false;
        }
        SimpleMap<?, ?> simpleMap = (SimpleMap<?, ?>) o;
        return capacity == simpleMap.capacity
                && count == simpleMap.count
                && modCount == simpleMap.modCount
                && Arrays.equals(table, simpleMap.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, count, modCount);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int indexOf(K key) {
        int h = key == null ? 0 : hash(key.hashCode());
        return indexFor(h);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] expandTable = new MapEntry[capacity];
        for (MapEntry<K, V> map : table) {
            if (map != null) {
                int newIndex = indexOf(map.key);
                expandTable[newIndex] = map;
            }
        }
        table = expandTable;
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        if (table[index] == null || !table[index].getKey().equals(key)) {
            return null;
        }
        return table[index].getValue();
    }

    @Override
    public boolean remove(K key) {
        int i = indexOf(key);
        boolean result = table[i] != null && key.equals(table[i].key);
        if (result) {
            table[i] = null;
            modCount++;
            count--;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int exModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity && table[point] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (exModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}