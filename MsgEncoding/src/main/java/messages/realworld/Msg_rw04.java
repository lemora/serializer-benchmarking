package messages.realworld;

import java.io.Serializable;
import java.util.*;

public class Msg_rw04 implements Serializable {

    private List<String> _stringList01;
    private Map<Integer, String> _intStrMap01;

    // parameterless constructor for kryo
    private Msg_rw04() {
    }

    public Msg_rw04(List<String> stringList, Map<Integer, String> intStringMap) {
        this._stringList01 = stringList;
        this._intStrMap01 = intStringMap;
    }

    public static Msg_rw04 createTestInstance() {
        List<String> stringList = new LinkedList<String>( Arrays.asList(new String[]{"We", "coding", "monkeys", "really", "like", "gobbledygook", "!"}) );

        Map<Integer, String> intStringMap = new HashMap<>();
        intStringMap.put(3, "hello");
        intStringMap.put(2, "Nothing really matters.");
        intStringMap.put(6, "some non-random, but meaningless string");

        Msg_rw04 msg = new Msg_rw04( stringList, intStringMap );
        return msg;
    }

    public List<String> get_stringList01() {
        return _stringList01;
    }

    public void set_stringList01(List<String> _stringList01) {
        this._stringList01 = _stringList01;
    }

    public Map<Integer, String> get_intStrMap01() {
        return _intStrMap01;
    }

    public void set_intStrMap01(Map<Integer, String> _intStrMap01) {
        this._intStrMap01 = _intStrMap01;
    }
}
