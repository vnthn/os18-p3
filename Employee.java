/*
 * Author: Nils von Nethen, 750115
 * Hochschule Darmstadt, Fachbereich Informatik
 * https://www.h-da.de/
 * 23.5.2018
 */
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Die Klasse Employee simuliert einen Mitarbeiter des Callcenters und erweitert die Klasse Thread, damit ein Thread
 * mit einer Instanz dieser Klasse erzeugt werden kann. <br>
 */
public class Employee extends Thread {
    private Thread     myThread;
    private Callcenter callcenter;
    private String     name;
    /**
     * Der Konstruktor erstellt eine neue Instanz eines Mitarbeiters und gibt diesem einen Namen.
     *
     * @param name ist der Name des Mitarbeiters.
     */
    Employee(String name) {
        this.name = name;
    }
    /**
     * Diese Methode simuliert die Arbeit eines Mitarbeiters des Callcenters. <br>
     * Durch den Aufruf von join() wartet der Mitarbeiter solange, bis der Anrufer seinen Call beendet hat.
     */
    @Override
    public void run() {
        callcenter = Callcenter.getInstance(Main.queueSize);
        Caller currentCaller;
        while (true) {
            while ((currentCaller = callcenter.getCaller()) == null) {
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Employee.class.getName())
                          .log(Level.SEVERE, null, ex);
                }
            }
            synchronized (currentCaller) {
                currentCaller.notify();
            }
            System.out.println(name + " spricht jetzt mit " + currentCaller.hisName());
            try {
                currentCaller.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName())
                      .log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Diese Methode startet den Thread der Instanz des Mitarbeiters. <br>
     * Nach dem erfolgreichen Start des Threads wird ein kurzer Text auf die Konsole geschrieben, dass der
     * Mitarbeiter nun am Start ist.
     */
    @Override
    public void start() {
        if (myThread == null) {
            myThread = new Thread(this);
            myThread.start();
            System.out.println("Employee " + name + " ist am Start!");
        }
    }
}
