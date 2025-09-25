package threading_advanced;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeDictionaryExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> dictionary = new ConcurrentHashMap<>();

        Runnable writer = () -> {
            dictionary.put("Java", "OOP Language");
            dictionary.put("Thread", "Lightweight Process");
            System.out.println("Writer added entries.");
        };

        Runnable reader = () -> {
            dictionary.forEach((key, value) -> 
                System.out.println(Thread.currentThread().getName() + " -> " + key + ": " + value));
        };

        Thread t1 = new Thread(writer);
        Thread t2 = new Thread(reader);
        Thread t3 = new Thread(reader);

        t1.start();
        t2.start();
        t3.start();
    }
}
