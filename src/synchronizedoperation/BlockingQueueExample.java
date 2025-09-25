package synchronizedoperation;

import java.util.concurrent.*;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i); // waits if full
                    System.out.println("Produced: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    int val = queue.take(); // waits if empty
                    System.out.println("Consumed: " + val);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        }).start();
    }
}

