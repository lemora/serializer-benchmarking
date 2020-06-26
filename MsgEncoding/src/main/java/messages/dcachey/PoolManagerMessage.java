package messages.dcachey;

import java.io.Serializable;

public class PoolManagerMessage extends Message implements Serializable {

    private static final long serialVersionUID = 4607229352454456613L;

    public PoolManagerMessage(){
        super();
    }
    public PoolManagerMessage(boolean replyNeeded){
        super(replyNeeded);
    }

}
