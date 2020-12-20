package innercore.systemscheduler.tuple;

/**
 * @author GongSunink
 */
public class DoubleTuple<T, V> extends Tuple<T> {

    private T first;
    private V second;

    public DoubleTuple(T first, V second) {
        super(first);
        this.second = second;
    }
}
