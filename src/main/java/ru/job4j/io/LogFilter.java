package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> strings = new LinkedList<>();
                try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            strings = in.lines().filter(x -> x.contains("404")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String str: log) {
                out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("C:/projects/job4j_design/src/main/java/resources/log.txt");
        for (String logs : log) {
            System.out.println(logs);
        }
        save(log, "C:/projects/job4j_design/src/main/java/resources/404.txt");

    }
}
