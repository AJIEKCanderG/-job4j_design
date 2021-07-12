package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String logPath;
    private final String botAnswers;
    private List<String> log;

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String logPath, String botAnswers) {
        this.logPath = logPath;
        this.botAnswers = botAnswers;
        log = new ArrayList<>();
    }

    private List<String> readAnswers() {
        List<String> result = null;
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8)
        )) {
            result = br.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Ошибка открытия файла: " + ex.getMessage());
        }
        return result;
    }

    private void saveLog() {
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(logPath, StandardCharsets.UTF_8, true)
                ))) {
            log.forEach(pw::println);
        } catch (IOException ex) {
            System.out.println("Ошибка записи лога: " + ex.getMessage());
        }
    }

    public void run() {
        boolean finish = false;
        boolean active = true;
        List<String> answers = readAnswers();
        Scanner sc = new Scanner(System.in);
        while (!finish) {
            System.out.print("Вы: ");
            String line = sc.nextLine();
            active = !active && line.equals(CONTINUE)
                            || active && !line.equals(STOP);
            finish = line.equals(OUT);
            log.add("Вы: " + line);
            if (active && !finish) {
                int i = (int) (answers.size() * Math.random());
                System.out.println("Бот: " + answers.get(i));
                log.add("Бот: " + answers.get(i));
            }
        }
        saveLog();
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/resources/console_chat_log.txt",
                "./src/main/resources/console_chat_answers.txt");
        cc.run();
    }
}