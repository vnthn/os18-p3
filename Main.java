public class Main {
    static final   int      QUEUE_SIZE     = 2;
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
    public static void main(String[] args) {
        int employees = 0;
        int callers = 0;
        try {
            if (args.length == 2) {
                employees = Integer.parseInt(args[0]);
                callers = Integer.parseInt(args[1]);
            } else {
                System.out.println("Bitte genau zwei Argumente (ganze Zahlen) angeben!"
                                   + System.lineSeparator()
                                   + "{Anzahl der Mitarbeiter (max "
                                   + employee_names.length
                                   + ")} {Anzahl der Anrufer (max "
                                   + caller_names.length
                                   + ")}");
                System.exit(1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Bitte genau ein Argument (ganze Zahl) angeben!");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Bitte eine ganze Zahl eingeben!");
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
        Callcenter callcenter = Callcenter.getInstance(QUEUE_SIZE);
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
