package benchmarking.runtime;

import messages.typelists.DoubleList;
import messages.typelists.IntList;
import messages.typelists.StringList;
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
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import serializer.Helper;
import serializer.SerializerFst;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 4, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class FstContainerBenchmarker {

    @State(Scope.Thread)
    public static class IntListObjects {

        @Param({"10", "100", "500", "1000", "10000", "100000"})
        public int valueCount;

        public IntList intList;
        public byte[] smsg_intList;

        @Setup(Level.Trial)
        public void doSetup() throws IOException {
            intList = new IntList(Helper.getIntListFromCSV(valueCount));
            smsg_intList = SerializerFst.serialize(intList);
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
            smsg_doubleList = SerializerFst.serialize(doubleList);
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
            smsg_stringList = SerializerFst.serialize(stringList);
        }
    }

    @Setup
    public void setUp() {
        SerializerFst.registerClass(LinkedList.class);
        SerializerFst.registerClass(DoubleList.class);
        SerializerFst.registerClass(IntList.class);
        SerializerFst.registerClass(StringList.class);
    }

// ------ StringList ------

    @Benchmark
    public byte[] fst_StringList_ser(StringListObjects stringList) {
        return SerializerFst.serialize(stringList.stringList);
    }

    @Benchmark
    public Object fst_StringList_deser(StringListObjects stringList) {
        return SerializerFst.deserialize(stringList.smsg_stringList);
    }

// ------ DoubleList ------

    @Benchmark
    public byte[] fst_DoubleList_ser(DoubleListObjects doubleList) {
        return SerializerFst.serialize(doubleList.doubleList);
    }

    @Benchmark
    public Object fst_DoubleList_deser(DoubleListObjects doubleList) {
        return SerializerFst.deserialize(doubleList.smsg_doubleList);
    }

// ------ IntList ------

    @Benchmark
    public byte[] fst_IntList_ser(IntListObjects intList) {
        return SerializerFst.serialize(intList.intList);
    }

    @Benchmark
    public Object fst_IntList_deser(IntListObjects intList) {
        return SerializerFst.deserialize(intList.smsg_intList);
    }

}
