package synchronizedoperation;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Java");
        list.add("Threads");

        Runnable writer = () -> {
            list.add("Concurrency");
            System.out.println(Thread.currentThread().getName() + " added element.");
        };

        Runnable reader = () -> {
            for (String item : list) {
                System.out.println(Thread.currentThread().getName() + " reads: " + item);
            }
        };

        new Thread(writer, "Writer").start();
        new Thread(reader, "Reader-1").start();
        new Thread(reader, "Reader-2").start();
    }
}

