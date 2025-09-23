package threading;

public class Livelock {
    static class Worker {
        private boolean active = true;
        public boolean isActive() { return active; }
        public void work() { active = false; }
    }

    public static void main(String[] args) {
        final Worker w1 = new Worker();
        final Worker w2 = new Worker();

        Thread t1 = new Thread(() -> {
            while (w1.isActive()) {
                if (w2.isActive()) {
                    System.out.println("Worker 1: yielding to Worker 2");
                    try { Thread.sleep(50); } catch (InterruptedException ignored) {}
                    continue;
                }
                w1.work();
                System.out.println("Worker 1: done");
            }
        });

        Thread t2 = new Thread(() -> {
            while (w2.isActive()) {
                if (w1.isActive()) {
                    System.out.println("Worker 2: yielding to Worker 1");
                    try { Thread.sleep(50); } catch (InterruptedException ignored) {}
                    continue;
                }
                w2.work();
                System.out.println("Worker 2: done");
            }
        });

        t1.start(); t2.start();
    }
}

