package synchronizedoperation;

import java.util.concurrent.*;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Runnable worker = () -> {
            System.out.println(Thread.currentThread().getName() + " started work.");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + " finished work.");
            latch.countDown();
        };

        new Thread(worker, "Worker-1").start();
        new Thread(worker, "Worker-2").start();
        new Thread(worker, "Worker-3").start();

        latch.await(); // main thread waits
        System.out.println("All workers finished. Proceeding...");
    }
}
