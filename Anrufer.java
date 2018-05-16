public class Anrufer implements Runnable {
    @Override
    public void run() {
        System.out.println("Anrufer " + Thread.currentThread().getName() +  " ist am Start!");

    }
    private void call() {
        Thread.currentThread().notify();
    }
}
