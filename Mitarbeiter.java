public class Mitarbeiter implements Runnable{
    @Override
    public void run() {
        System.out.println("Mitarbeiter Nr. " + Thread.currentThread().getName() + " ist dabei!");
    }
}
