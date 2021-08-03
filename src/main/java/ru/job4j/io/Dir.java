package ru.job4j.io;

import java.io.File;
import java.util.Objects;

 /** Аргументы переданные в проект:
  * C:\projects\job4j_design
  * задана папка.
  * */

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("size : %s%n", file.getTotalSpace());
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (!subfile.isDirectory()) {
                System.out.println(subfile.getAbsoluteFile() + " (size = " + subfile.length() + " byte)");
            }
        }
    }
}
