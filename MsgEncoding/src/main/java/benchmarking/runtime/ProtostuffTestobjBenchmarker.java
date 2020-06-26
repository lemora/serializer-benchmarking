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

import java.util.concurrent.TimeUnit;

import messages.realworld.Msg;
import messages.realworld.Msg_rw01;
import messages.realworld.Msg_rw02;
import messages.realworld.Msg_rw03;
import messages.realworld.Msg_rw04;
import messages.realworld.Msg_rw05;
import serializer.SerializerProtostuff;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class ProtostuffTestobjBenchmarker {

    private Msg msg_rw00;
    private byte[] smsg_rw00;

    private Msg_rw01 msg_rw01;
    private byte[] smsg_rw01;

    private Msg_rw02 msg_rw02;
    private byte[] smsg_rw02;

    private Msg_rw03 msg_rw03;
    private byte[] smsg_rw03;

    private Msg_rw04 msg_rw04;
    private byte[] smsg_rw04;

    private Msg_rw05 msg_rw05;
    private byte[] smsg_rw05;

    @Setup
    public void setUp() {
        // --- Msg ---
        msg_rw00 = new Msg();
        smsg_rw00 = SerializerProtostuff.ser_Msg(msg_rw00);

        // --- Msg_rw01 ---
        msg_rw01 = new Msg_rw01();
        smsg_rw01 = SerializerProtostuff.ser_MsgRw01(msg_rw01);

        // --- Msg_rw02 ---
        msg_rw02 = new Msg_rw02();
        smsg_rw02 = SerializerProtostuff.ser_MsgRw02(msg_rw02);

        // --- Msg_rw03 ---
        msg_rw03 = new Msg_rw03();
        smsg_rw03 = SerializerProtostuff.ser_MsgRw03(msg_rw03);

        // --- Msg_rw04 ---
        msg_rw04 = Msg_rw04.createTestInstance();
        smsg_rw04 = SerializerProtostuff.ser_MsgRw04(msg_rw04);

        // --- Msg_rw05 ---
        msg_rw05 = new Msg_rw05();
        smsg_rw05 = SerializerProtostuff.ser_MsgRw05(msg_rw05);
    }

// ------ Msg ------

    @Benchmark
    public byte[] proto_rw00_ser() {
        return SerializerProtostuff.ser_Msg(msg_rw00);
    }

    @Benchmark
    public Object proto_rw00_deser() {
        return SerializerProtostuff.deser_Msg(smsg_rw00);
    }

// ------ Msg_rw01 ------

    @Benchmark
    public byte[] proto_rw01_ser() {
        return SerializerProtostuff.ser_MsgRw01(msg_rw01);
    }

    @Benchmark
    public Object proto_rw01_deser() {
        return SerializerProtostuff.deser_MsgRw01(smsg_rw01);
    }

// ------ Msg_rw02 ------

    @Benchmark
    public byte[] proto_rw02_ser() {
        return SerializerProtostuff.ser_MsgRw02(msg_rw02);
    }

    @Benchmark
    public Object proto_rw02_deser() {
        return SerializerProtostuff.deser_MsgRw02(smsg_rw02);
    }

// ------ Msg_rw03 ------

    @Benchmark
    public byte[] proto_rw03_ser() {
        return SerializerProtostuff.ser_MsgRw03(msg_rw03);
    }

    @Benchmark
    public Object proto_rw03_deser() {
        return SerializerProtostuff.deser_MsgRw03(smsg_rw03);
    }

// ------ Msg_rw04 ------

    @Benchmark
    public byte[] proto_rw04_ser() {
        return SerializerProtostuff.ser_MsgRw04(msg_rw04);
    }

    @Benchmark
    public Object proto_rw04_deser() {
        return SerializerProtostuff.deser_MsgRw04(smsg_rw04);
    }

// ------ Msg_rw05 ------

    @Benchmark
    public byte[] proto_rw05_ser() {
        return SerializerProtostuff.ser_MsgRw05(msg_rw05);
    }

    @Benchmark
    public Object proto_rw05_deser() {
        return SerializerProtostuff.deser_MsgRw05(smsg_rw05);
    }
}
