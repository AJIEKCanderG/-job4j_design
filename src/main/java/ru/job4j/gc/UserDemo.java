package ru.job4j.gc;

/**
 * @author Ajiekcander
 */
public class UserDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();  //TODO возвращает количество свободной памяти в байтах
        final long totalMemory = ENVIRONMENT.totalMemory(); //TODO возвращает общее количество памяти которое может быть использовано
        final long maxMemory = ENVIRONMENT.maxMemory(); // TODO  возвращает максимальное количество памяти которое может быть использовано
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500_000_000; i++) {
            new User(i, "Name" + i);
        }
        System.gc(); //TODO вызывает сборщик мусора
        info();
    }
}
