package synchronizedoperation;

import java.util.concurrent.*;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int parties = 3;

        CyclicBarrier barrier = new CyclicBarrier(parties, () -> {
            System.out.println("All threads reached the barrier. Proceeding together!");
        });

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is doing work...");
            try {
                Thread.sleep((long)(Math.random() * 2000));
                System.out.println(Thread.currentThread().getName() + " waiting at barrier.");
                barrier.await(); // wait for others
                System.out.println(Thread.currentThread().getName() + " continues after barrier.");
            } catch (Exception e) { e.printStackTrace(); }
        };

        for (int i = 1; i <= parties; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}

