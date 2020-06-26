package messages.realworld;

import messages.realworld.Msg;

import java.io.Serializable;

public class Msg_rw03 implements Serializable {

    private Msg _msg;
    private Msg_rw01 _msg_rw01_00;
    private Msg_rw01 _msg_rw01_01;

    public Msg_rw03() {
        _msg = new Msg();
        _msg_rw01_00 = new Msg_rw01();
        _msg_rw01_01 = _msg_rw01_00;
    }

    public Msg get_msg() {
        return _msg;
    }

    public void set_msg(Msg _msg) {
        this._msg = _msg;
    }

    public Msg_rw01 get_msg_rw01_00() {
        return _msg_rw01_00;
    }

    public void set_msg_rw01_00(Msg_rw01 _msg_rw01_00) {
        this._msg_rw01_00 = _msg_rw01_00;
    }

    public Msg_rw01 get_msg_rw01_01() {
        return _msg_rw01_01;
    }

    public void set_msg_rw01_01(Msg_rw01 _msg_rw01_01) {
        this._msg_rw01_01 = _msg_rw01_01;
    }
}
