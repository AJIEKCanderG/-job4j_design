package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder builder = new StringBuilder();
            String input;
            while ((input = in.readLine()) != null) {
                String[] str = input.split(" ");
                if (builder.length() == 0 && (str[0].equals("400") || str[0].equals("500"))) {
                    builder.append(str[1]).append(";");
                }
                if (builder.length() != 0 && (str[0].equals("200") || str[0].equals("300"))) {
                    builder.append(str[1]).append(";");
                    out.println(builder);
                    builder = new StringBuilder();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream in = new FileInputStream("unavailable.csv")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}