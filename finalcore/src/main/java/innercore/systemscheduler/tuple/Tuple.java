package innercore.systemscheduler.tuple;

/**
 * @author GongSunink
 */
public class Tuple<T> {
    private T tuple;

    public T getTuple() {
        return tuple;
    }

    public Tuple(T in) {
        this.tuple = in;
    }
}
