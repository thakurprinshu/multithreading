package threading;

public class Singleton {
    private static volatile Singleton instance;

    private Singleton() { System.out.println("Singleton created!"); }

    public static Singleton getInstance() {
        if (instance == null) { // first check
            synchronized (Singleton.class) {
                if (instance == null) { // second check
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton");
    }

    public static void main(String[] args) {
        Runnable task = () -> {
            Singleton s = Singleton.getInstance();
            s.showMessage();
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
    }
}
