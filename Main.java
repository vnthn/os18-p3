/*
 * Author: Nils von Nethen, 750115
 * Hochschule Darmstadt, Fachbereich Informatik
 * https://www.h-da.de/
 * 23.5.2018
 */
/**
 * Dieses Programm simuliert ein Callcenter.<br>
 * Es werden mithilfe von Threads verschiedene Mitarbeiter sowie Anrufer simuliert, die das Callcenter anrufen. Die
 * Anrufer werden durch eine Warteschlange verwaltet.
 *
 * @author Nils von Nethen
 * @version 1.0
 */
public class Main {
    public static  int      queueSize;
    private static String[] employee_names = {"Anna", "Alexander", "Anouk", "Aurelia", "Annika", "Amelie", "Alina",
                                              "Anton", "Antonia", "Adrian", "Angela", "Alfred", "Adam", "August",
                                              "Aurelius", "Ann"};
    private static String[] caller_names   = {"Ben", "Benjamin", "Birgit", "Benedikt", "Bernd", "Barbara", "Bianca",
                                              "Bettina", "Bruno", "Bastian", "Bert", "Badger", "Brutus", "Benno", "Bob",
                                              "Bendix", "Bene", "Bengt", "Benk", "Benjie", "Bennet", "Bernard",
                                              "Berthold", "Bertrand", "Balthasar", "Barack", "Barnabas", "Barney",
                                              "Barron", "Bart", "Bartholomew", "Baxter", "Beaufort", "Bill", "Billy",
                                              "Burkhard", "Bjoern", "Bodo", "Bonifatis", "Bono", "Borchard", "Brad",
                                              "Brock", "Bruce", "Brian", "Buddy", "Basilia", "Bastiane", "Bastienne",
                                              "Baerbel", "Beate", "Beatrice", "Becca", "Belinda", "Berit", "Bernadette",
                                              "Bernhardine", "Bertha", "Bessie", "Betty", "Bibiane", "Birthe", "Bjoerk",
                                              "Bonita", "Brenda", "Brigitta", "Britney", "Brooklyn", "Brunhilde",
                                              "Burglinde", "Burglinde"};
    /**
     * Die Kommandozeilen-Argumente bestimmen die Anzahl der Mitarbeiter sowie die Anzahl der Anrufer.
     *
     * @param args Kommandozeilen-Argumente, die ausgelesen werden.
     */
    public static void main(String[] args) {
        int employees = 0;
        int callers = 0;
        queueSize = 0;
        try {
            if (args.length == 3) {
                employees = Integer.parseInt(args[0]);
                callers = Integer.parseInt(args[1]);
                queueSize = Integer.parseInt(args[2]);
            } else {
                System.out.println("Bitte genau drei Argumente (ganze Zahlen) angeben!"
                                   + System.lineSeparator()
                                   + "{Anzahl der Mitarbeiter (max "
                                   + employee_names.length
                                   + ")} {Anzahl der Anrufer (max "
                                   + caller_names.length + ")} " + "{Groesse der Warteschlange}");
                System.exit(1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Bitte genau drei Argumente (ganze Zahl) angeben!");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Bitte ganze Zahlen eingeben!");
            System.exit(1);
        }
        final int CALLERS = callers;
        final int EMPLOYEES = employees;
        System.out.println("Hausaufgabe im Praktikum 3 zu Betriebssysteme"
                           + System.lineSeparator()
                           + "Es wird "
                           + "ein Callcenter simuliert"
                           + System.lineSeparator()
                           + "Alle beteiligten "
                           + "Personen der Simulation haben Namen."
                           + System.lineSeparator()
                           + "Namen der Mitarbeiter "
                           + "beginnen mit A."
                           + System.lineSeparator()
                           + "Namen der Anrufer beginnen mit B."
                           + System.lineSeparator()
                           + "--- go! ---"
                           + System.lineSeparator());
        Callcenter callcenter = Callcenter.getInstance(queueSize);
        System.out.println("Mitarbeiter gehen zur Arbeit..." + System.lineSeparator());
        for (int i = 0; i < EMPLOYEES; i++) {
            Employee employee = new Employee(employee_names[i]);
            employee.start();
        }
        System.out.println(System.lineSeparator() + "Anrufer wachen auf..." + System.lineSeparator());
        for (int i = 0; i < CALLERS; i++) {
            Caller caller = new Caller(caller_names[i]);
            caller.start();
        }
        System.out.println(System.lineSeparator() + "Anrufer fangen an anzurufen." + System.lineSeparator());
    }
}
