public class Main {
    public static  Callcenter callcenter;
    private static String[]   employee_names = {"Anna", "Alexander", "Anouk", "Aurelia", "Annika", "Amelie", "Alina",
                                                "Anton", "Antonia", "Adrian"};
    private static String[]   caller_names   = {"Ben", "Benjamin", "Birgit", "Benedikt", "Bernd", "Barbara", "Bianca",
                                                "Bettina", "Bruno", "Bastian", "Bert", "Badger"};
    public static void main(String[] args) {
        final int QUEUE_SIZE = 5;
        final int CALLERS = 10;
        final int EMPLOYEES = 3;
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
        callcenter = Callcenter.getInstance(QUEUE_SIZE);
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
    }
}
