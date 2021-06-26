package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void whenOnePeriod() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.cvs");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 13:42:01");
            out.println("400 13:52:13");
            out.println("300 13:55:27");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(joiner::add);
        }
        assertThat(joiner.toString(), is("13:52:13;13:55:27;"));
    }

    @Test
    public void whenMoreOnePeriods() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.cvs");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 15:01:30");
            out.println("200 15:02:32");
            out.println("300 15:05:02");
            out.println("200 15:06:02");
            out.println("400 15:10:30");
            out.println("500 16:04:01");
            out.println("300 23:12:32");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("15:01:30;15:02:32;15:10:30;23:12:32;"));
    }


    @Test
    public void analizyTest() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("source.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("300 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        List<String> expected = List.of(
                "10:58:01;10:59:01;",
                "11:01:02;11:02:02;"
        );
        assertThat(rsl, is(expected));
    }
}