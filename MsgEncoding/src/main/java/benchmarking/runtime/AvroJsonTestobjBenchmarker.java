package benchmarking.runtime;

import frameworks.avro.generated.randobjects.Avro_Msg;
import frameworks.avro.generated.randobjects.Avro_Msg_rw01;
import frameworks.avro.generated.randobjects.Avro_Msg_rw02;
import frameworks.avro.generated.randobjects.Avro_Msg_rw03;
import frameworks.avro.generated.randobjects.Avro_Msg_rw04;
import frameworks.avro.generated.randobjects.Avro_Msg_rw05;
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

import messages.realworld.Msg;
import messages.realworld.Msg_rw01;
import messages.realworld.Msg_rw02;
import messages.realworld.Msg_rw03;
import messages.realworld.Msg_rw04;
import messages.realworld.Msg_rw05;
import serializer.SerializerAvro;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class AvroJsonTestobjBenchmarker {

    private Avro_Msg avro_msg_rw00;
    private byte[] smsg_json_rw00;

    private Avro_Msg_rw01 avro_msg_rw01;
    private byte[] smsg_json_rw01;

    private Avro_Msg_rw02 avro_msg_rw02;
    private byte[] smsg_json_rw02;

    private Avro_Msg_rw03 avro_msg_rw03;
    private byte[] smsg_json_rw03;

    private Avro_Msg_rw04 avro_msg_rw04;
    private byte[] smsg_json_rw04;

    private Avro_Msg_rw05 avro_msg_rw05;
    private byte[] smsg_json_rw05;

    @Setup
    public void setUp() {
        // --- Msg ---
        avro_msg_rw00 = SerializerAvro.msgToAvro_Msg(new Msg());
        smsg_json_rw00 = SerializerAvro.serAvro_json_Msg(avro_msg_rw00);

        // --- Msg_rw01 ---
        avro_msg_rw01 = SerializerAvro.msgToAvro_MsgRw01(new Msg_rw01());
        smsg_json_rw01 = SerializerAvro.serAvro_json_MsgRw01(avro_msg_rw01);

        // --- Msg_rw02 ---
        avro_msg_rw02 = SerializerAvro.msgToAvro_MsgRw02(new Msg_rw02());
        smsg_json_rw02 = SerializerAvro.serAvro_json_MsgRw02(avro_msg_rw02);

        // --- Msg_rw03 ---
        avro_msg_rw03 = SerializerAvro.msgToAvro_MsgRw03(new Msg_rw03());
        smsg_json_rw03 = SerializerAvro.serAvro_json_MsgRw03(avro_msg_rw03);

        // --- Msg_rw04 ---
        avro_msg_rw04 = SerializerAvro.msgToAvro_MsgRw04(Msg_rw04.createTestInstance());
        smsg_json_rw04 = SerializerAvro.serAvro_json_MsgRw04(avro_msg_rw04);

        // --- Msg_rw05 ---
        avro_msg_rw05 = SerializerAvro.msgToAvro_MsgRw05(new Msg_rw05());
        smsg_json_rw05 = SerializerAvro.serAvro_json_MsgRw05(avro_msg_rw05);
    }

// ------ Msg ------

    @Benchmark
    public byte[] avro_rw00_ser() {
        return SerializerAvro.serAvro_json_Msg(avro_msg_rw00);
    }

    @Benchmark
    public Object avro_rw00_deser() {
        try {
            return SerializerAvro.deserToAvro_json_Msg(smsg_json_rw00);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

// ------ Msg_rw01 ------

    @Benchmark
    public byte[] avro_rw01_ser() {
        return SerializerAvro.serAvro_json_MsgRw01(avro_msg_rw01);
    }

    @Benchmark
    public Object avro_rw01_deser() {
        try {
            return SerializerAvro.deserToAvro_json_MsgRw01(smsg_json_rw01);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

// ------ Msg_rw02 ------

    @Benchmark
    public byte[] avro_rw02_ser() {
        return SerializerAvro.serAvro_json_MsgRw02(avro_msg_rw02);
    }

    @Benchmark
    public Object avro_rw02_deser() {
        try {
            return SerializerAvro.deserToAvro_json_MsgRw02(smsg_json_rw02);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

// ------ Msg_rw03 ------

    @Benchmark
    public byte[] avro_rw03_ser() {
        return SerializerAvro.serAvro_json_MsgRw03(avro_msg_rw03);
    }

    @Benchmark
    public Object avro_rw03_deser() {
        try {
            return SerializerAvro.deserToAvro_json_MsgRw03(smsg_json_rw03);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

// ------ Msg_rw04 ------

    @Benchmark
    public byte[] avro_rw04_ser() {
        return SerializerAvro.serAvro_json_MsgRw04(avro_msg_rw04);
    }

    @Benchmark
    public Object avro_rw04_deser() {
        try {
            return SerializerAvro.deserToAvro_json_MsgRw04(smsg_json_rw04);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

// ------ Msg_rw05 ------

    @Benchmark
    public byte[] avro_rw05_ser() {
        return SerializerAvro.serAvro_json_MsgRw05(avro_msg_rw05);
    }

    @Benchmark
    public Object avro_rw05_deser() {
        try {
            return SerializerAvro.deserToAvro_json_MsgRw05(smsg_json_rw05);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
