package threading;

import java.util.*;
import java.util.concurrent.Semaphore;

class SharedQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    private final Semaphore empty, full;
    private final Object lock = new Object();

    SharedQueue(int capacity) {
        this.capacity = capacity;
        empty = new Semaphore(capacity);
        full = new Semaphore(0);
    }

    public void produce(int item) throws InterruptedException {
        empty.acquire();
        synchronized (lock) {
            queue.add(item);
            System.out.println("Produced: " + item);
        }
        full.release();
    }

    public int consume() throws InterruptedException {
        full.acquire();
        int item;
        synchronized (lock) {
            item = queue.poll();
            System.out.println("Consumed: " + item);
        }
        empty.release();
        return item;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedQueue sq = new SharedQueue(5);

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try { sq.produce(i); Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try { sq.consume(); Thread.sleep(150); } catch (InterruptedException ignored) {}
            }
        });

        producer.start();
        consumer.start();
    }
}
