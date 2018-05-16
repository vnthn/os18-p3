import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Callcenter implements Runnable {
    private List<Thread> mitarbeiterList = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition besetzt = lock.newCondition();
    private final Condition frei = lock.newCondition();

    public Callcenter(int anzahlMitarbeiter) {
        for (int i = 0;  i < anzahlMitarbeiter; i++) {
            mitarbeiterList.add(new Thread(new Mitarbeiter(), Integer.toString(i)));
        }
    }
    @Override
    public void run() {
        System.out.println("Das " + Thread.currentThread().getName() + " ist geöffnet!");
        try {
            for (Thread mitarbeiter : mitarbeiterList) {
                mitarbeiter.start();
            }
            System.out.println("Es sind " + Integer.toString(mitarbeiterList.size()) + " Mitarbeiter eingestellt.");
        } catch (IllegalThreadStateException e) {
            System.err.println("Mitarbeiter konnten nicht eingestellt werden :(");
        }
    }
}
