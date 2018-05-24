/*
 * Author: Nils von Nethen, 750115
 * Hochschule Darmstadt, Fachbereich Informatik
 * https://www.h-da.de/
 * 23.5.2018
 */
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Caller simuliert einen Anrufer, der das Callcenter anruft. <br>
 * Die Klasse erbt von der Klasse Thread, um als Thread ausgeführt werden zu können. Durch verschiedene Parameter
 * innerhalb der Klasse können die maximale sowie die minimale Dauer eines Anrufs eingestellt werden. Außerdem kann
 * festgelegt werden, wie lange ein Anrufer wartet, bis er erneut versucht das Callcenter anzurufen.
 */
public class Caller extends Thread {
    private       Thread     myThread;
    private       String     name;
    private       Callcenter callcenter;
    private       Random     rand                                = new Random();
    private final int        MAXIMUM_DURATION_OF_CALL            = 5000;
    private final int        MINIMUM_DURATION_OF_CALL            = 1000;
    private final int        CALLERS_TRYING_TO_CALL_EVERY_MILLIS = 2000;
    /**
     * Die Dauer eines Anrufs wird zufällig gewählt, wobei die Dauer des Anrufs zwischen dem vorher festgelegten
     * Maximum und dem Minimum liegt.
     *
     * @return Dauer des Anrufs in Millisekunden
     */
    private int callDuration() {
        return rand.nextInt(MAXIMUM_DURATION_OF_CALL) + MINIMUM_DURATION_OF_CALL;
    }
    Caller(String name) {
        this.name = name;
    }
    /**
     * Die Methode run() legt das Verhalten einer Caller-Instanz fest. <br>
     * Solange die Warteschlange nicht frei ist, schläft der Anrufer eine festgelegte Zeit. Ist die Warteschlange
     * frei, fügt sich der Anrufer selbstständig (also durch einen Anruf) in die Warteschlange des Callcenters
     * ein. Diese Aktion wird auf der Konsole ausgegeben. <br>
     * Danach spricht der Anrufer eine vorher zufällig festgelegte Zeit mit einem Mitarbeiter, bis der Anrufer den
     * Anruf beendet, indem er aufhört zu schlafen.
     */
    @Override
    public void run() {
        while (true) {
            callcenter = Callcenter.getInstance(Main.queueSize);
            while (!callcenter.lineIsFree()) {
                try {
                    sleep(CALLERS_TRYING_TO_CALL_EVERY_MILLIS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Caller.class.getName())
                          .log(Level.SEVERE, null, ex);
                }
            }
            callcenter.putCaller(this);
            System.out.println(name + " ist in der Warteschlange.");
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Caller.class.getName())
                      .log(Level.SEVERE, null, ex);
            }
            try {
                sleep(callDuration());
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName())
                      .log(Level.SEVERE, null, ex);
            }
            System.out.println(name + " hat aufgelegt.");
        }
    }
    /**
     * Diese Methode startet den Thread des Anrufers und gibt eine kurze Ausgabe auf die Konsole, dass der Anrufer
     * jetzt aktiv ist.
     */
    @Override
    public void start() {
        if (myThread == null) {
            myThread = new Thread(this, name);
            myThread.start();
            System.out.println("Caller " + name + " ist aufgewacht.");
        }
    }
    /**
     * Diese Methode gibt den Namen des Anrufers zurück.
     *
     * @return Name des Anrufers.
     */
    String hisName() {
        return this.name;
    }
}
