package ru.job4j.serialization.json;

public class Cat {
    private String food;
    public Cat(String food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "food='" + food + '\''
                + '}';
    }
}
