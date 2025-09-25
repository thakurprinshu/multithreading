package threading_advanced;

public class ThreadLocalVariable {
    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Runnable task = () -> {
            int val = threadLocal.get();
            for (int i = 0; i < 3; i++) {
                val++;
                threadLocal.set(val);
                System.out.println(Thread.currentThread().getName() + " -> " + threadLocal.get());
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}

