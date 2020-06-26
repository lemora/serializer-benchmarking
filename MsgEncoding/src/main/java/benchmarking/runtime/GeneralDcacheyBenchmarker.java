package benchmarking.runtime;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import messages.dcachey.PoolCostInfo;
import messages.dcachey.PoolManagerPoolUpMessage;
import messages.dcachey.PoolV2Mode;
import serializer.SerializerFst;
import serializer.SerializerHessian;
import serializer.SerializerJava;
import serializer.SerializerKryo;
import serializer.SerializerProtostuff;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Benchmarker for serializing and deserializing the PoolManagerPoolUpMessage
 * from the dCache project with every serialization framework.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class GeneralDcacheyBenchmarker {

    private PoolManagerPoolUpMessage msg_pmpum;
    private byte[] smsg_java_pmpum;
    private byte[] smsg_fst_pmpum;
    private byte[] smsg_kryo_pmpum;
    private byte[] smsg_hessian_pmpum;
    private byte[] smsg_protostuff_pmpum;

    @Setup
    public void setUp() {
        // Kryo
        SerializerKryo.registerClass( PoolManagerPoolUpMessage.class );
        SerializerKryo.registerClass( PoolV2Mode.class );
        SerializerKryo.registerClass( PoolCostInfo.class );
        SerializerKryo.registerClass( PoolCostInfo.PoolSpaceInfo.class );
        SerializerKryo.registerClass( javax.security.auth.Subject.class );
        SerializerKryo.registerClass( java.util.HashSet.class );
        SerializerKryo.registerClass( HashMap.class );

        // Fst -- optional for size/speed improvements
        SerializerFst.registerClass( HashMap.class );
        SerializerFst.registerClass( PoolManagerPoolUpMessage.class );
        SerializerFst.registerClass( PoolV2Mode.class );
        SerializerFst.registerClass( PoolCostInfo.class );
        SerializerFst.registerClass( PoolCostInfo.PoolSpaceInfo.class );

        // --- serializing ---
        msg_pmpum = PoolManagerPoolUpMessage.createTestInstance();
        smsg_java_pmpum = SerializerJava.serialize( msg_pmpum );
        smsg_fst_pmpum = SerializerFst.serialize( msg_pmpum );
        smsg_kryo_pmpum = SerializerKryo.serialize( msg_pmpum );
        smsg_hessian_pmpum = SerializerHessian.serialize( msg_pmpum );
        smsg_protostuff_pmpum = SerializerProtostuff.ser_PMPUM( msg_pmpum );
    }


// ------ Java ------

    @Benchmark
    public byte[] java_PMPUM_ser() {
        return SerializerJava.serialize( msg_pmpum );
    }

    @Benchmark
    public Object java_PMPUM_deser() {
        return SerializerJava.deserialize( smsg_java_pmpum );
    }

// ------ Fst ------

    @Benchmark
    public byte[] fst_PMPUM_ser() {
        return SerializerFst.serialize( msg_pmpum );
    }

    @Benchmark
    public Object fst_PMPUM_deser() {
        return SerializerFst.deserialize( smsg_fst_pmpum );
    }

// ------ Kryo ------

    @Benchmark
    public byte[] kryo_PMPUM_ser() {
        return SerializerKryo.serialize( msg_pmpum );
    }

    @Benchmark
    public Object kryo_PMPUM_deser() {
        return SerializerKryo.deserialize( smsg_kryo_pmpum, PoolManagerPoolUpMessage.class );
    }

// ------ Hessian ------

    @Benchmark
    public byte[] hessian_PMPUM_ser() {
        return SerializerHessian.serialize( msg_pmpum );
    }

    @Benchmark
    public Object hessian_PMPUM_deser() {
        return SerializerHessian.deserialize( smsg_hessian_pmpum );
    }

// ------ Protostuff ------

    @Benchmark
    public byte[] protostuff_PMPUM_ser() {
        return SerializerProtostuff.ser_PMPUM( msg_pmpum );
    }

    @Benchmark
    public Object protostuff_PMPUM_deser() {
        return SerializerProtostuff.deser_PMPUM( smsg_protostuff_pmpum );
    }

}
