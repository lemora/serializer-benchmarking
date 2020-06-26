package messages.dcachey;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PoolManagerPoolUpMessage extends PoolManagerMessage implements Serializable {

    private final String    _poolName ;
    private final long      _serialId ;
    private final PoolCostInfo _poolCostInfo ;
    private final PoolV2Mode _mode;

    private Map<String, String> _tagMap;
    private Set<String> _hsmInstances;
    private String    _message                 = "";
    private int       _code;


    private static final long serialVersionUID = -8421133630068493665L;

    public PoolManagerPoolUpMessage(String poolName, long serialId,
                                    PoolV2Mode mode)
    {
        this(poolName, serialId, mode, null);
    }

    public PoolManagerPoolUpMessage(String poolName, long serialId,
                                    PoolV2Mode mode, PoolCostInfo costInfo)
    {
        assert mode != null;

        _poolName = poolName;
        _serialId = serialId;
        _mode = mode;
        _poolCostInfo = costInfo;
        setReplyRequired(false);
    }

    // parameterless constructor for kryo
    private PoolManagerPoolUpMessage() {
        _poolName = "testPoolName";
        _serialId = 333666999;
        _mode = new PoolV2Mode();
        _poolCostInfo = new PoolCostInfo("testPoolName", PoolCostInfo.DEFAULT_QUEUE);
    }

    public static PoolManagerPoolUpMessage createTestInstance()
    {
        String poolName = "testPoolName";
        long serialId = 333666999;
        PoolV2Mode mode = new PoolV2Mode();
        PoolCostInfo poolCostInfo = new PoolCostInfo("testPoolName", PoolCostInfo.DEFAULT_QUEUE);
        PoolManagerPoolUpMessage inst = new PoolManagerPoolUpMessage(poolName, serialId, mode, poolCostInfo);

        inst.setReplyRequired(false);
        Map<String, String> tagmap = new HashMap<>();
        tagmap.put("zone", "desy");
        tagmap.put("group", "it");
        inst.setTagMap( tagmap );

        Set<String> hsmInstances = new HashSet<>(Arrays.asList( new String[]{"hsm1", "hsm2", "hsm3"} ) );
        inst.setHsmInstances( hsmInstances );

        inst.setMessage( "This is the test PoolManagerPoolUpMessage." );
        inst.setCode( 13 );
        return inst;
    }

    public PoolCostInfo getPoolCostInfo(){ return _poolCostInfo ; }
    public String getPoolName(){
        return _poolName;
    }
    public long getSerialId(){ return _serialId ; }
    public void setTagMap( Map<String, String> map ){ _tagMap = map ; }
    public Map<String, String>  getTagMap(){ return _tagMap ; }

    /**
     * Sets the human readable status message of the pool.
     */
    public void setMessage(String msg)
    {
        assert msg != null;

        _message = msg;
    }

    /**
     * Returns the human readable status message of the pool. May be
     * null.
     */
    public String getMessage()
    {
        return _message;
    }

    /**
     * Sets the machine interpretable status code of the pool.
     */
    public void setCode(int code)
    {
        _code = code;
    }

    /**
     * Returns the machine interpretable status code of the
     * pool. Returns 0 if the status code has not been set.
     */
    public int getCode()
    {
        return _code;
    }

    /**
     * Returns the mode of the pool. The mode indicates which
     * operations are currently supported by the pool.
     */
    public PoolV2Mode getPoolMode()
    {
        return _mode;
    }

    /** Returns the names of attached HSM instances. */
    public Set<String> getHsmInstances()
    {
        return _hsmInstances;
    }

    /**
     * Sets the set of names of attached HSM instances.
     *
     * @param value Set of HSM instance names. Must implement Serializable.
     */
    public void setHsmInstances(Set<String> value)
    {
        assert value instanceof Serializable;
        _hsmInstances = value;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("serial id: " + this.getSerialId() + "\n");
//        sb.append("pool name: " + this._poolName + "\n");
//        sb.append("code: " + this.getCode() + "\n");
//        sb.append("message: " + this.getMessage() + "\n");
//        sb.append("mode: " + this.getPoolMode() + "\n");
//        sb.append("cost info: " + this.getPoolCostInfo() + "\n");
//        sb.append("tag map: " + this.getTagMap() + "\n");
//        sb.append("hsm instances: " + this.getHsmInstances());
//        return sb.toString();
//    }
}
