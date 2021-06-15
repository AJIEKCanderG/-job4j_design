package ru.job4j.collection.map;

import java.util.*;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return children == user.children &&
                name.equals(user.name) &&
                birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> user = new HashMap<>();
        Calendar birthday = new GregorianCalendar(1980, Calendar.APRIL, 19, 13, 13, 10);
        birthday.set(Calendar.MILLISECOND, 9);
        User userOne = new User("Pavel", 2, birthday);
        User userTwo = new User("Pavel", 2, birthday);
        user.put(userOne, new Object());
        System.out.println(user + "\n");
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
