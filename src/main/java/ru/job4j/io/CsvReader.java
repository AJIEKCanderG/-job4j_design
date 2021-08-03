package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 2. Создать класс CSVReader. Задача класса прочитать данные из CSV файла и вывести их.
 * В качестве входных данных задается путь к файлу path, разделитель delimiter, приемник данных out и фильтр
 * по столбцам filter.
 * Ключ -out имеет только два допустимых значения stdout или путь к файлу, куда нужно вывести.
 * Например, если есть файл CSV со столбцами name, age, birthDate, education, children и программа запускается таким образом:
 * java -jar target/csvReader.jar -path=file.txt -delimiter=";"  -out=stdout -filter=name,age
 * то программа должна прочитать файл по пути file.txt и вывести только столбцы name, age в консоль.
 * В качестве разделителя данных выступает ;
 * 3. Для чтения аргументов использовать класс Args и задания "5.1. Именованные аргументы".
 * 4. Программа должна запускаться с консоли в виде jar файла как показано в примере.
 * 5. Для чтения файла использовать класс Scanner.
 * 6. Добавить валидацию аргументов, как в классе Search.

 * Аргументы переданные в проект:
 * -path=./src/main/resources/inFile.csv -delimiter=";" -out=stdout -filter=name,age
 */


public class CsvReader {
    private String inFileName;
    private String outFileName;
    private String delimiter;
    private Set<String> filterColumns;
    private List<String> columns;
    private List<List<String>> data;

    public CsvReader(String[] args) {
        ArgsName nArgs = ArgsName.of(args);
        if (!checkArgs(nArgs)) {
            System.exit(2);
        }
    }


    public boolean checkArgs(ArgsName args) {
        if (args.size() != 4) {
            System.out.println("Wrong count args!");
            return false;
        }
        outFileName = args.get("out");
        if (!"stdout".equals(outFileName)) {
            Path checkPath = Path.of(outFileName);
            if (!Files.exists(checkPath.getParent()) || Files.isDirectory(checkPath)) {
                System.out.println("Wrong path outFileName!");
                return false;
            }
        }
        inFileName = args.get("path");
        Path checkPath = Path.of(inFileName);
        if (!Files.exists(checkPath) || Files.isDirectory(checkPath)) {
            System.out.println("Wrong path inFileName");
            return false;
        }
        delimiter = args.get("delimiter");
        if (delimiter == null) {
            System.out.println("Not found delimiter!");
            return false;
        }
        String filter = args.get("filter");
        filterColumns = filter != null ? Set.of(filter.split(",")) : null;
        return true;
    }



    public void read() {
        data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(inFileName)
        )) {
            Scanner sc = new Scanner(br.readLine()).useDelimiter(delimiter);
            columns = sc.tokens().collect(Collectors.toList());
            while (br.ready()) {
                List<String> line = new ArrayList<>();
                sc = new Scanner(br.readLine()).useDelimiter(delimiter);
                for (int i = 0; i < columns.size(); i++) {
                    if (!sc.hasNext()) {
                        break;
                    }
                    line.add(sc.next());
                }
                data.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Error read: " + ex);
        }
    }

    private void write() {
        List<String> buffer = new ArrayList<>();
        for (List<String> line : data) {
            StringBuilder cout = new StringBuilder();
            for (int i = 0; i < columns.size(); i++) {
                String colName = columns.get(i);
                if (filterColumns == null || filterColumns.contains(colName)) {
                    cout.append(String.format("%s; ", line.get(i)));
                }
            }
            buffer.add(cout.toString());
        }
        if ("stdout".equals(outFileName)) {
            buffer.forEach(System.out::println);
        } else {
            save(buffer);
        }
    }

    private void save(List<String> lines) {
        try (PrintWriter pw =
                     new PrintWriter(
                             new BufferedWriter(
                                     new FileWriter(outFileName)
                             ))) {
            lines.forEach(pw::println);
        } catch (IOException ex) {
            System.out.println("Error save: " + ex);
        }
    }


    public static void main(String[] args) throws IOException {
        CsvReader reader = new CsvReader(args);
        reader.read();
        reader.write();
        System.out.println("Done!");
    }
}

