package ru.job4j.io;


import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Аргументы переданные в проект
 * -d=C:\projects\job4j_design\ -e=class -o=job4j_design.zip
 */


public class Zip {

    private static boolean checkArgs(ArgsName args) {
        File checkFile = new File(args.get("d"));
        boolean result =
                checkFile.exists()
                        && checkFile.isDirectory()
                        && !args.get("o").isEmpty()
                        && !args.get("e").isEmpty();
        if (!result) {
            System.out.println("Invalid startup args!");
           }
        return result;
    }

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path src : sources) {
                zip.putNextEntry(new ZipEntry(src.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(src)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argNames = ArgsName.of(args);
        if (!checkArgs(argNames)) {
            return;
        }
        final String ext = argNames.get("e");
        File target = new File(argNames.get("o"));
        List<Path> sources = Search.search(
                Path.of(argNames.get("d")),
                p -> !p.toFile().getName().endsWith("." + ext)
        );
        packFiles(sources, target);
    }
}
