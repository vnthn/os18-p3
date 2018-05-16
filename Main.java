public class Main {
    public static void main(String[] args) {
        int mitarbeiter = 0;
        try {
            if (args.length == 1) {
                mitarbeiter = Integer.parseInt(args[0]);
                System.out.println(args[0] + " Mitarbeiter sind eingestellt!");
            } else {
                System.out.println("Bitte genau ein Argument (ganze Zahl) angeben!");

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Bitte genau ein Argument (ganze Zahl) angeben!");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Bitte eine ganze Zahl eingeben!");
            System.exit(1);
        }
        Thread callcenter = new Thread(new Callcenter());
        callcenter.start();
    }
}
