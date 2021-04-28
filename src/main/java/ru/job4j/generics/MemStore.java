package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    private int indexById(String id) {
        int result = -1;
        for (int index = 0; index < mem.size(); index++) {
            if (mem.get(index).getId().equals(id)) {
                result = index;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = indexById(id);
        if (index != -1) {
            mem.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = indexById(id);
        if (index != -1) {
            mem.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        int index = indexById(id);
        if (index != -1) {
            return mem.get(index);
        }
        return null;
    }
}