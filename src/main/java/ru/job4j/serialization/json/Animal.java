package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Animal {
    private final boolean spotted ;
    private final int age;
    private final Cat cat;
    private final String[] names;

    public Animal(boolean spotted, int age, Cat cat, String... names) {
        this.spotted = spotted;
        this.age = age;
        this.cat = cat;
        this.names = names;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "spotted=" + spotted
                + ", age=" + age
                + ", cat=" + cat
                + ", names=" + Arrays.toString(names)
                + '}';
    }

    public static void main(String[] args) {
        final Animal animal = new Animal(false, 3, new Cat("meat"), "Barsik", "Murka", "Genry");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(animal));

        /* Модифицируем json-строку */
        final String animalJson =
                "{"
                        + "\"spotted\":false,"
                        + "\"age\":3,"
                        + "\"cat\":"
                        + "{"
                        + "\"food\":\"meat\""
                        + "},"
                        + "\"names\":"
                        + "[\"Barsik\",\"Murka\",\"Genry\"]"
                        + "}";
        final Animal animalMod = gson.fromJson(animalJson, Animal.class);
        System.out.println(animalMod);
    }
}
