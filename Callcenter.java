import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
class Callcenter extends ConcurrentLinkedQueue<Caller> {
    private static Callcenter instance;
    private        int        length;
    private Callcenter(int length) {
        this.length = length;
    }
    static Callcenter getInstance(int length) {
        instance = (instance == null ? new Callcenter(length) : instance);
        return instance;
    }
    Caller getCaller() {
        return poll();
    }
    boolean putCaller(Caller caller) {
        if (size() < length) {
            add(caller);
            return true;
        }
        return false;
    }
    boolean lineIsFree() {
        return size() < length;
    }
}
