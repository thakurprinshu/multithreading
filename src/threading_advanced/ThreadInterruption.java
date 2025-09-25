package threading_advanced;

public class ThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Working...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted! Exiting...");
                    break;
                }
            }
        });

        worker.start();
        Thread.sleep(2000);
        worker.interrupt();
    }
}

