package benchmarking.runtime;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;
import serializer.Helper;
import serializer.SerializerKryo;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class KryoContainerBenchmarker {

    @State(Scope.Thread)
    public static class IntListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public IntList intList;
        public byte[] smsg_intList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            intList = new IntList(Helper.getIntListFromCSV(valueCount));
            smsg_intList = SerializerKryo.serialize(intList);
        }
    }

    @State(Scope.Thread)
    public static class DoubleListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public DoubleList doubleList;
        public byte[] smsg_doubleList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            doubleList = new DoubleList(Helper.getDoubleListFromCSV(valueCount));
            smsg_doubleList = SerializerKryo.serialize(doubleList);
        }
    }

    @State(Scope.Thread)
    public static class StringListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public StringList stringList;
        public byte[] smsg_stringList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            stringList = new StringList(Helper.getStringListFromCSV(valueCount));
            smsg_stringList = SerializerKryo.serialize(stringList);
        }
    }

    @Setup
    public void setUp() {
        SerializerKryo.registerClass(java.util.List.class);
        SerializerKryo.registerClass(java.util.LinkedList.class);
        SerializerKryo.registerClass(DoubleList.class);
        SerializerKryo.registerClass(IntList.class);
        SerializerKryo.registerClass(StringList.class);
    }

// ------ StringList ------

    @Benchmark
    public byte[] kryo_StringList_ser(StringListObjects stringList) {
        return SerializerKryo.serialize(stringList.stringList);
    }

    @Benchmark
    public Object kryo_StringList_deser(StringListObjects stringList) {
        return SerializerKryo.deserialize(stringList.smsg_stringList, StringList.class);
    }

// ------ DoubleList ------

    @Benchmark
    public byte[] kryo_DoubleList_ser(DoubleListObjects doubleList) {
        return SerializerKryo.serialize(doubleList.doubleList);
    }

    @Benchmark
    public Object kryo_DoubleList_deser(DoubleListObjects doubleList) {
        return SerializerKryo.deserialize(doubleList.smsg_doubleList, DoubleList.class);
    }

// ------ IntList ------

    @Benchmark
    public byte[] kryo_IntList_ser(IntListObjects intList) {
        return SerializerKryo.serialize(intList.intList);
    }

    @Benchmark
    public Object kryo_IntList_deser(IntListObjects intList) {
        return SerializerKryo.deserialize(intList.smsg_intList, IntList.class);
    }

}
