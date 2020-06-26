package messages.dcachey;

import java.io.Serializable;
import java.util.Collection;

public interface Assumption extends Serializable {

    /**
     * Provides access to various load related information of a pool.
     */
    interface Pool
    {
        String name();

        PoolCostInfo.PoolSpaceInfo space();
        double moverCostFactor();

        PoolCostInfo.NamedPoolQueueInfo getMoverQueue(String name);

        Collection<PoolCostInfo.NamedPoolQueueInfo> movers();

        PoolCostInfo.PoolQueueInfo p2PClient();
        PoolCostInfo.PoolQueueInfo p2pServer();
        PoolCostInfo.PoolQueueInfo restore();
        PoolCostInfo.PoolQueueInfo store();
    }
}
