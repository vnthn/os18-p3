import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int mitarbeiter = 0;
        try {
            if (args.length == 1) {
                mitarbeiter = Integer.parseInt(args[0]);
                System.out.println(args[0] + " Mitarbeiter wurden angefordert...");
            } else {
                System.out.println("Bitte genau ein Argument (ganze Zahl) angeben!");
                System.exit(1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Bitte genau ein Argument (ganze Zahl) angeben!");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Bitte eine ganze Zahl eingeben!");
            System.exit(1);
        }
        Thread callcenter = new Thread(new Callcenter(mitarbeiter), "Callcenter");
        callcenter.start();

        List<Thread> anruferList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            anruferList.add(new Thread(new Anrufer(), Integer.toString(i)));
        }
        for (Thread anrufer : anruferList) {
            anrufer.start();
        }
    }
}
