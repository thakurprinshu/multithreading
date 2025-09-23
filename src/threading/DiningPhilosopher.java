package threading;

import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private final int id;
    private final Semaphore leftChopstick;
    private final Semaphore rightChopstick;

    Philosopher(int id, Semaphore left, Semaphore right) {
        this.id = id;
        this.leftChopstick = left;
        this.rightChopstick = right;
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep(500);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep(500);

                if (id % 2 == 0) {
                    rightChopstick.acquire();
                    leftChopstick.acquire();
                } else {
                    leftChopstick.acquire();
                    rightChopstick.acquire();
                }

                eat();

                leftChopstick.release();
                rightChopstick.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class DiningPhilosopher {
    public static void main(String[] args) {
        int n = 5;
        Semaphore[] chopsticks = new Semaphore[n];
        for (int i = 0; i < n; i++) chopsticks[i] = new Semaphore(1);

        Philosopher[] philosophers = new Philosopher[n];
        for (int i = 0; i < n; i++) {
            Semaphore left = chopsticks[i];
            Semaphore right = chopsticks[(i + 1) % n];
            philosophers[i] = new Philosopher(i, left, right);
            philosophers[i].start();
        }
    }
}
