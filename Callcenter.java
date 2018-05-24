/*
 * Author: Nils von Nethen, 750115
 * Hochschule Darmstadt, Fachbereich Informatik
 * https://www.h-da.de/
 * 23.5.2018
 */
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * Die Klasse Callcenter simuliert das Hauptquartier des Callcenters, indem es die Warteschlange bereitstellt. <br>
 * Anrufer nutzen die Funktionen des Callcenters um sich selbst in die Warteschlange einzufügen. Mitarbeiter nutzen
 * die Funktionen der Klasse, um den nächsten Mitarbeiter aus der Warteschlange abzuarbeiten.
 */
class Callcenter extends ConcurrentLinkedQueue<Caller> {
    private static Callcenter instance;
    private        int        length;
    /**
     * Der Konstruktor der Klasse ist private, sodass die Methode als Singleton implementiert worden ist. <br>
     * Dadurch kann keine weitere Instanz des Callcenters erzeugt werden. Es gibt ja auch bloß ein einziges Callcenter.
     *
     * @param length die Länge der Warteschlange des Callcenters
     */
    private Callcenter(int length) {
        this.length = length;
    }
    /**
     * Diese Methode erzeugt bei Bedarf eine neue Callcenter-Instanz und gibt andernfalls die aktuelle Instanz des
     * Callcenters zurück.
     *
     * @param length die Länge der Warteschlange
     * @return die aktuelle Instanz des Callcenter-Objekts
     */
    static Callcenter getInstance(int length) {
        instance = (instance == null ? new Callcenter(length) : instance);
        return instance;
    }
    /**
     * Methode, um den jeweils ersten Anrufer aus der Warteschlange zu extrahieren, damit ein Mitarbeiter mit ihm
     * sprechen kann.
     *
     * @return den aktuellen Anrufer
     */
    synchronized Caller getCaller() {
        return poll();
    }
    /**
     * Methode, um einen Anrufer der Warteschlange hinzuzufügen.
     *
     * @param caller ist der Caller, der der Warteschlange hinzugefügt werden soll.
     */
    synchronized void putCaller(Caller caller) {
        if (size() < length) add(caller);
    }
    /**
     * Methode, die zurück-liefert ob die Warteschlange frei ist. Ist sie frei, können Anrufer sich in die
     * Warteschlange setzen.
     *
     * @return ob die Warteschlange frei ist.
     */
    boolean lineIsFree() {
        return size() < length;
    }
}
