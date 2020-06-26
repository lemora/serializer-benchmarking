package benchmarking.runtime;

import frameworks.protobuf.generated.protos.ProtosTestobj;
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
import serializer.SerializerProtobuf;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class ProtoTestobjBenchmarker {

    private ProtosTestobj.Proto_Msg proto_msg_rw00;
    private byte[] smsg_rw00;

    private ProtosTestobj.Proto_Msg_rw01 proto_msg_rw01;
    private byte[] smsg_rw01;

    private ProtosTestobj.Proto_Msg_rw02 proto_msg_rw02;
    private byte[] smsg_rw02;

    private ProtosTestobj.Proto_Msg_rw03 proto_msg_rw03;
    private byte[] smsg_rw03;

    private ProtosTestobj.Proto_Msg_rw04 proto_msg_rw04;
    private byte[] smsg_rw04;

    private ProtosTestobj.Proto_Msg_rw05 proto_msg_rw05;
    private byte[] smsg_rw05;

    @Setup
    public void setUp() {
        // --- Msg ---
        proto_msg_rw00 = SerializerProtobuf.msgToProto_Msg(new Msg());
        smsg_rw00 = SerializerProtobuf.serProto_Msg(proto_msg_rw00);

        // --- Msg_rw01 ---
        proto_msg_rw01 = SerializerProtobuf.msgToProto_MsgRw01(new Msg_rw01());
        smsg_rw01 = SerializerProtobuf.serProto_MsgRw01(proto_msg_rw01);

        // --- Msg_rw02 ---
        proto_msg_rw02 = SerializerProtobuf.msgToProto_MsgRw02(new Msg_rw02());
        smsg_rw02 = SerializerProtobuf.serProto_MsgRw02(proto_msg_rw02);

        // --- Msg_rw03 ---
        proto_msg_rw03 = SerializerProtobuf.msgToProto_MsgRw03(new Msg_rw03());
        smsg_rw03 = SerializerProtobuf.serProto_MsgRw03(proto_msg_rw03);

        // --- Msg_rw04 ---
        proto_msg_rw04 = SerializerProtobuf.msgToProto_MsgRw04(Msg_rw04.createTestInstance());
        smsg_rw04 = SerializerProtobuf.serProto_MsgRw04(proto_msg_rw04);

        // --- Msg_rw05 ---
        proto_msg_rw05 = SerializerProtobuf.msgToProto_MsgRw05(new Msg_rw05());
        smsg_rw05 = SerializerProtobuf.serProto_MsgRw05(proto_msg_rw05);
    }

// ------ Msg ------

    @Benchmark
    public byte[] proto_rw00_ser() {
        return SerializerProtobuf.serProto_Msg(proto_msg_rw00);
    }

    @Benchmark
    public Object proto_rw00_deser() {
        return SerializerProtobuf.deserToProto_Msg(smsg_rw00);
    }

// ------ Msg_rw01 ------

    @Benchmark
    public byte[] proto_rw01_ser() {
        return SerializerProtobuf.serProto_MsgRw01(proto_msg_rw01);
    }

    @Benchmark
    public Object proto_rw01_deser() {
        return SerializerProtobuf.deserToProto_MsgRw01(smsg_rw01);
    }

// ------ Msg_rw02 ------

    @Benchmark
    public byte[] proto_rw02_ser() {
        return SerializerProtobuf.serProto_MsgRw02(proto_msg_rw02);
    }

    @Benchmark
    public Object proto_rw02_deser() {
        return SerializerProtobuf.deserToProto_MsgRw02(smsg_rw02);
    }

// ------ Msg_rw03 ------

    @Benchmark
    public byte[] proto_rw03_ser() {
        return SerializerProtobuf.serProto_MsgRw03(proto_msg_rw03);
    }

    @Benchmark
    public Object proto_rw03_deser() {
        return SerializerProtobuf.deserToProto_MsgRw03(smsg_rw03);
    }

// ------ Msg_rw04 ------

    @Benchmark
    public byte[] proto_rw04_ser() {
        return SerializerProtobuf.serProto_MsgRw04(proto_msg_rw04);
    }

    @Benchmark
    public Object proto_rw04_deser() {
        return SerializerProtobuf.deserToProto_MsgRw04(smsg_rw04);
    }

// ------ Msg_rw05 ------

    @Benchmark
    public byte[] proto_rw05_ser() {
        return SerializerProtobuf.serProto_MsgRw05(proto_msg_rw05);
    }

    @Benchmark
    public Object proto_rw05_deser() {
        return SerializerProtobuf.deserToProto_MsgRw05(smsg_rw05);
    }
}
