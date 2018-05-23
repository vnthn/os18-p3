public class Caller extends Thread {
    private Thread myThread;
    private String name;
    public Caller(String name) {
        this.name = name;
    }
    @Override
    public void run() {
    }
    @Override
    public void start() {
        if (myThread == null) {
            myThread = new Thread(this, name);
            myThread.start();
            System.out.println("Caller " + name + " ist aufgewacht.");
        }
    }
}
