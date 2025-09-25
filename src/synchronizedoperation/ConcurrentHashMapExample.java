package synchronizedoperation;

import java.util.concurrent.*;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        Runnable writer = () -> {
            map.put("Java", "Language");
            map.put("Thread", "Execution Unit");
            System.out.println(Thread.currentThread().getName() + " wrote to map.");
        };

        Runnable reader = () -> {
            map.forEach((k, v) ->
                    System.out.println(Thread.currentThread().getName() + " -> " + k + ": " + v));
        };

        Thread t1 = new Thread(writer, "Writer");
        Thread t2 = new Thread(reader, "Reader-1");
        Thread t3 = new Thread(reader, "Reader-2");

        t1.start();
        t2.start();
        t3.start();
    }
}

