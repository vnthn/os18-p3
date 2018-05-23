import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Caller extends Thread {
    private       Thread     myThread;
    private       String     name;
    private       Callcenter callcenter;
    private       Random     rand                                = new Random();
    private final int        MAXIMUM_DURATION_OF_CALL            = 5000;
    private final int        MINIMUM_DURATION_OF_CALL            = 1000;
    private final int        CALLERS_TRYING_TO_CALL_EVERY_MILLIS = 2000;
    private int callDuration() {
        return rand.nextInt(MAXIMUM_DURATION_OF_CALL) + MINIMUM_DURATION_OF_CALL;
    }
    Caller(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        while (true) {
            while (!callcenter.lineIsFree()) {
                try {
                    sleep(CALLERS_TRYING_TO_CALL_EVERY_MILLIS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Caller.class.getName())
                          .log(Level.SEVERE, null, ex);
                }
            }
            if (callcenter.putCaller(this)) {
                System.out.println(name + " ist in der Warteschlange.");
            }
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
    @Override
    public void start() {
        if (myThread == null) {
            myThread = new Thread(this, name);
            myThread.start();
            System.out.println("Caller " + name + " ist aufgewacht.");
        }
        callcenter = Callcenter.getInstance(Main.QUEUE_SIZE);
    }
    public String hisName() {
        return this.name;
    }
}
