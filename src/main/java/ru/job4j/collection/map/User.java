package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
            Map<User, Object> user = new HashMap<>();
            User userOne = new User("Pavel", 2, new GregorianCalendar(1980, Calendar.APRIL, 19));
            User userTwo = new User("Pavel", 2, new GregorianCalendar(1980, Calendar.APRIL, 19));
            user.put(userOne, new Object());
            user.put(userTwo, new Object());
            System.out.println(user + "\n");

            System.out.println("userOne hashCode = " + userOne.hashCode());
            int h1 = userOne.hashCode() ^ userOne.hashCode() >>> 16;
            System.out.println("userOne hash = " + h1);
            int i1 = (16 - 1) & h1;
            System.out.println("Индекс бакета userOne = " + i1 + "\n");

            System.out.println("userTwo hashCode = " + userTwo.hashCode());
            int h2 = userTwo.hashCode() ^ userTwo.hashCode() >>> 16;
            System.out.println("userTwo hash = " + h2);
            int i2 = (16 - 1) & h2;
            System.out.println("Индекс бакета userTwo = " + i2);
    }
}
