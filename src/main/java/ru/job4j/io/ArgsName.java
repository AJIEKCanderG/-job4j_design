package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values;

    public ArgsName() {
        values = new HashMap<>();
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] str = arg.split("=");
            if (
                    str.length != 2
                            || str[0].isEmpty()
                            || str[1].isEmpty()
                            || !str[0].startsWith("-")
            ) {
                throw new IllegalArgumentException();
            }
            values.put(str[0].substring(1), str[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public int size() {
        return values.size();
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}