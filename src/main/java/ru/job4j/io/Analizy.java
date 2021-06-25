package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            PrintWriter out = new PrintWriter(new FileOutputStream(target));
            StringBuilder builder = new StringBuilder();
            while(in.readLine() != null) {
                String[] str = in.readLine().split(" ");
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
        try (PrintWriter out = new PrintWriter(new FileOutputStream("./src/main/resources/unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}