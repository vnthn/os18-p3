import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
public class Callcenter extends ConcurrentLinkedQueue<Caller> {
    private static Callcenter instance;
    private        int        length = 0;
    private Callcenter(int length) {
        this.length = length;
    }
    public static Callcenter getInstance(int length) {
        if (instance == null) {
            instance = new Callcenter(length);
        }
        return instance;
    }
    public Caller getCaller() {
        return this.poll();
    }
    public boolean putCaller(Caller caller) {
        if (this.size() < this.length) {
            this.add(caller);
            return true;
        }
        return false;
    }
}
