package threading_advanced;

import java.util.LinkedList;
import java.util.Queue;

class ThreadSafeQueue<T> {
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void enqueue(T item) {
        queue.add(item);
        notifyAll(); // Notify waiting threads
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait until item is available
        }
        return queue.poll();
    }
}

public class ThreadSafeQueue1 {
    public static void main(String[] args) {
        ThreadSafeQueue<Integer> tsq = new ThreadSafeQueue<>();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                tsq.enqueue(i);
                System.out.println("Produced: " + i);
                try { Thread.sleep(300); } catch (InterruptedException e) {}
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    int val = tsq.dequeue();
                    System.out.println("Consumed: " + val);
                } catch (InterruptedException e) {}
            }
        }).start();
    }
}

