package messages.realworld;

import java.io.Serializable;

public class Msg_rw01 implements Serializable {

    private char _char01;
    private byte _byte01;
    private boolean _boolean01;

    private short _short01;
    private int _int01;
    private long _long01;

    private float _float01;
    private double _double01;

    public Msg_rw01() {
        _char01 = (char) 4;
        _byte01 = (byte) 6;
        _boolean01 = true;

        _short01 = 124;
        _int01 = 4657889;
        _long01 = 56623889;

        _float01 = 2465.5f;
        _double01 = 323698.5;
    }

    public char get_char01() {
        return _char01;
    }

    public void set_char01(char _char01) {
        this._char01 = _char01;
    }

    public byte get_byte01() {
        return _byte01;
    }

    public void set_byte01(byte _byte01) {
        this._byte01 = _byte01;
    }

    public boolean is_boolean01() {
        return _boolean01;
    }

    public void set_boolean01(boolean _boolean01) {
        this._boolean01 = _boolean01;
    }

    public short get_short01() {
        return _short01;
    }

    public void set_short01(short _short01) {
        this._short01 = _short01;
    }

    public int get_int01() {
        return _int01;
    }

    public void set_int01(int _int01) {
        this._int01 = _int01;
    }

    public long get_long01() {
        return _long01;
    }

    public void set_long01(long _long01) {
        this._long01 = _long01;
    }

    public float get_float01() {
        return _float01;
    }

    public void set_float01(float _float01) {
        this._float01 = _float01;
    }

    public double get_double01() {
        return _double01;
    }

    public void set_double01(double _double01) {
        this._double01 = _double01;
    }
}
