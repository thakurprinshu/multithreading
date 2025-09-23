package threading;

public class Deadlock {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding Lock1...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (Lock2) {
                    System.out.println("Thread 1: Acquired Lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding Lock2...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (Lock1) {
                    System.out.println("Thread 2: Acquired Lock1");
                }
            }
        });

        t1.start(); t2.start();
    }
}

