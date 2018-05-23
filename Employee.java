import java.util.logging.Level;
import java.util.logging.Logger;
public class Employee extends Thread {
    private Thread     myThread;
    private Callcenter callcenter;
    private String     name;
    public Employee(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        callcenter = Callcenter.getInstance(5);
        Caller nextCaller = callcenter.getCaller();
        Caller currentCaller;
        while ((currentCaller = callcenter.getCaller()) == null) {
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        synchronized (currentCaller) {
            currentCaller.notify();
        }
        System.out.println();
        try {
            currentCaller.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void start() {
        if (myThread == null) {
            myThread = new Thread(this);
            myThread.start();
            System.out.println("Employee " + name + " ist am Start!");
        }
    }
}
