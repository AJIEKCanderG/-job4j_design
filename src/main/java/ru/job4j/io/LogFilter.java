package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> strings = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("C:/projects/job4j_design/log.txt"))) {
            strings = in.lines().filter(x -> x.contains("404")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String logs : log) {
            System.out.println(logs);
        }

    }
}
