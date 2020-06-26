package messages.realworld;

import java.io.Serializable;

public class Msg_rw05 implements Serializable {

    private Byte _byte01;
    private Boolean _boolean01;

    private Short _short01;
    private Integer _int01;
    private Long _long01;

    private Float _float01;
    private Double _double01;

    private String _string01;

    public Msg_rw05() {
        _byte01 = new Byte((byte) 6);
        _boolean01 = new Boolean(false);

        _short01 = new Short((short) 124);
        _int01 = new Integer(4657889);
        _long01 = new Long(56623889);

        _float01 = new Float(2465.5f);
        _double01 = new Double(323698.5);

        _string01 = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, " +
                "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna " +
                "aliquyam erat, sed diam voluptua. At vero eos et accusam et " +
                "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea " +
                "takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor " +
                "sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor " +
                "invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. " +
                "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita " +
                "kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
    }

    public Byte get_byte01() {
        return _byte01;
    }

    public void set_byte01(Byte _byte01) {
        this._byte01 = _byte01;
    }

    public Boolean get_boolean01() {
        return _boolean01;
    }

    public void set_boolean01(Boolean _boolean01) {
        this._boolean01 = _boolean01;
    }

    public Short get_short01() {
        return _short01;
    }

    public void set_short01(Short _short01) {
        this._short01 = _short01;
    }

    public Integer get_int01() {
        return _int01;
    }

    public void set_int01(Integer _int01) {
        this._int01 = _int01;
    }

    public Long get_long01() {
        return _long01;
    }

    public void set_long01(Long _long01) {
        this._long01 = _long01;
    }

    public Float get_float01() {
        return _float01;
    }

    public void set_float01(Float _float01) {
        this._float01 = _float01;
    }

    public Double get_double01() {
        return _double01;
    }

    public void set_double01(Double _double01) {
        this._double01 = _double01;
    }

    public String get_string01() {
        return _string01;
    }

    public void set_string01(String _string01) {
        this._string01 = _string01;
    }
}
