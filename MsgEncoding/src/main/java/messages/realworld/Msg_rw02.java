package messages.realworld;

import messages.realworld.Msg;

import java.io.Serializable;

public class Msg_rw02 implements Serializable {

    private Msg _msg;
    private Msg_rw01 _msg_rw01;

    public Msg_rw02() {
        _msg = new Msg();
        _msg_rw01 = new Msg_rw01();
    }

    public Msg get_msg() {
        return _msg;
    }

    public void set_msg(Msg _msg) {
        this._msg = _msg;
    }

    public Msg_rw01 get_msg_rw01() {
        return _msg_rw01;
    }

    public void set_msg_rw01(Msg_rw01 _msg_rw01) {
        this._msg_rw01 = _msg_rw01;
    }
}
